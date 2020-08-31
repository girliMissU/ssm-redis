package com.amaan.service;

/**
 * 佛祖保佑，永无BUG
 *
 * @author AMAAN
 * SSMR
 * 2020-08-30 20:36
 */
public interface UserRedPacketService {
    /**
     * 保存抢红包信息
     * @param redPacketId 红包编号
     * @param userId 抢红包用户编号
     * @return 影响记录条数
     */
    public int grabRedPacket(Long redPacketId, Long userId);

    /**
     * 使用乐观锁保存抢红包信息
     * @param redPacketId 红包编号
     * @param userId      抢红包用户编号
     * @return 影响记录条数
     */
    public int grabRedPacketForVersion(Long redPacketId, Long userId);

    /**
     * 通过redis抢红包
     * @param redPacketId
     * @param userId
     * @return 0-没有库存，失败；1-成功，且不是最后一个红包；2-成功，且是最后一个红包
     */
    public Long grabRedPacketByRedis(Long redPacketId, Long userId);
}
