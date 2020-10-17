package com.amaan.dao;

import com.amaan.pojo.RedPacket;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * 佛祖保佑，永无BUG
 *
 * @author AMAAN
 * SSMR
 * 2020-08-30 20:03
 */
@Repository
public interface RedPacketDao {
    /**
     * 获取红包信息
     * select ... from ... where ... for update 悲观锁，主键查询->行锁，非主键查询->表锁，数据库内部提供
     * @param id 红包id
     * @return 红包具体信息
     */
    @Select("select id, user_id as userId, amount, send_date as sendDate, total, unit_amount as unitAmount, stock, version, note from T_RED_PACKET where id=#{id}")
    RedPacket getRedPacket(@Param("id") Long id);

    /**
     * 扣减红包数
     * 由于设置每个红包金额相同，故不需要扣减红包金额
     * 如果是拼手气，抢到的金额是在拆红包式才生成，需要更新剩余金额
     * @param id 红包id
     * @return 更新记录条数
     */
    @Update("update T_RED_PACKET set stock=stock-1 where id = #{id}")
    int decreaseRedPacket(@Param("id") Long id);

    /**
     * 通过版本号扣减抢红包，每更新一次，版本增1，并对版本号进行判断
     * @param id 红包id
     * @param version 更新前版本号
     * @return 更新记录条数
     */
    @Update("update T_RED_PACKET set stock=stock-1, version=version+1 where id = #{id} and version = #{version}")
    int decreaseRedPacketForVersion(@Param("id") Long id, @Param("version") int version);
}
