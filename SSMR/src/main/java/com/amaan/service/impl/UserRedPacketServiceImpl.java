package com.amaan.service.impl;

import com.amaan.dao.RedPacketDao;
import com.amaan.dao.UserRedPacketDao;
import com.amaan.pojo.RedPacket;
import com.amaan.pojo.UserRedPacket;
import com.amaan.service.RedisRedPacketService;
import com.amaan.service.UserRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.Objects;

/**
 * 佛祖保佑，永无BUG
 *
 * @author AMAAN
 * SSMR
 * 2020-08-30 20:38
 */
@Service("userRedPacketService")
public class UserRedPacketServiceImpl implements UserRedPacketService {

    @Autowired
    private UserRedPacketDao userRedPacketDao;

    @Autowired
    private RedPacketDao redPacketDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisRedPacketService redisRedPacketService;

    //Lua脚本
    private String script = "local listKey = 'red_packet_'..KEYS[1] \n"
            +"local redPacket = 'red_packet_'..KEYS[1] \n"
            +"local stock = tonumber(redis.call('hget', redPacket, 'stock')) \n"
            +"if stock <= 0 then return 0 end \n"
            +"stock = stock -1 \n"
            +"redis.call('hset', redPacket, 'stock', toString(stock)) \n"
            +"redis.call('rpush', listKey, ARGV[1]) \n"
            +"if stock == 0 then return 2 end \n"
            +"return 1 \n";

    /* 缓存Lua脚本后，使用该变量保存Redist返回的32位SHA1编码，用它去执行缓存的Lua脚本 */
    private String sha1 = null;

    //失败
    private static final int FAILED = 0;

    /**
     * 保存抢红包信息
     * 不用所得情况下，高并发场景会产生超发现象，即数据不一致问题
     * @param redPacketId 红包编号
     * @param userId      抢红包用户编号
     * @return 影响记录条数
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public int grabRedPacket(Long redPacketId, Long userId) {
        //获取红包信息
        RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
        //当前小库存大于零
        if (redPacket.getStock()>0){
            redPacketDao.decreaseRedPacket(redPacketId);
            //生成抢红包信息
            UserRedPacket userRedPacket = new UserRedPacket();
            userRedPacket.setRedPacketId(redPacketId);
            userRedPacket.setUserId(userId);
            userRedPacket.setAmount(redPacket.getUnitAmount());
            userRedPacket.setNote("抢红包"+redPacketId);
            //插入抢红包信息
            int result = userRedPacketDao.grabRedPacket(userRedPacket);
            return result;
        }
        return FAILED;
    }

    /**
     * 使用乐观锁保存抢红包信息
     * 使用时间戳提高成功率，不稳定，会随系统空闲或繁忙导致重试次数不一
     * 使用重试次数，for(int i=0; i<3; i++)
     * @param redPacketId 红包编号
     * @param userId      抢红包用户编号
     * @return 影响记录条数
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public int grabRedPacketForVersion(Long redPacketId, Long userId) {
        long start = System.currentTimeMillis();
        while(true) {
            long end = System.currentTimeMillis();
            //设置重入限制100毫秒
            if(end-start>100){
                return FAILED;
            }
            //获取红包信息
            RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
            //当前小库存大于零
            if (redPacket.getStock() > 0) {
                //传入线程保存的version旧值给SQL判断，判断是否有其他线程修改过
                int update = redPacketDao.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());
                //没有数据更新，说明有其他线程已经修改过，本次抢红包失败
                if (update == 0) {
                    return FAILED;
                }
                //生成抢红包信息
                UserRedPacket userRedPacket = new UserRedPacket();
                userRedPacket.setRedPacketId(redPacketId);
                userRedPacket.setUserId(userId);
                userRedPacket.setAmount(redPacket.getUnitAmount());
                userRedPacket.setNote("抢红包" + redPacketId);
                //插入抢红包信息
                int result = userRedPacketDao.grabRedPacket(userRedPacket);
                return result;
            }
            //一旦没有库存立即返回，不再重入
            return FAILED;
        }
    }

    /**
     * 通过redis抢红包
     * 待修改
     * @param redPacketId
     * @param userId
     * @return 0-没有库存，失败；1-成功，且不是最后一个红包；2-成功，且是最后一个红包
     */
    @Override
    public Long grabRedPacketByRedis(Long redPacketId, Long userId) {
//        System.out.println("service");
        //当前抢红包用户和日期信息
        String args = userId+"-"+System.currentTimeMillis();
        Long result = null;
        //获取底层Redis操作对象
        try(Jedis jedis = (Jedis) Objects.requireNonNull(redisTemplate.getConnectionFactory()).getConnection().getNativeConnection()) {
            //如果脚本没有被加载过，就加载，返回一个SHA1编码
            if(sha1==null){
                sha1 = jedis.scriptLoad(script);
            }
            //执行脚本，返回结果
            Object res = jedis.eval(sha1,1,redPacketId+"",args);
            result = (Long) res;
            //返回2，需要将链表中的数据保存到数据库中
            if (result==2){
                String unitAmountStr = jedis.hget("red_packet_"+redPacketId,"unit_amount");
                Double unitAmount = Double.parseDouble(unitAmountStr);
                System.err.println("thread_name = "+Thread.currentThread().getName());
                redisRedPacketService.saveUserRedPacketByRedis(redPacketId,unitAmount);
            }
        }
        return result;
    }
}
