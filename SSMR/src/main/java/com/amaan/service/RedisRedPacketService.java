package com.amaan.service;

/**
 * 佛祖保佑，永无BUG
 *
 * @author AMAAN
 * SSMR
 * 2020-08-31 17:04
 */
public interface RedisRedPacketService {
    /**
     * 保存redis抢红包列表
     * @param redPacketId 抢红包编号
     * @param unitAmount 红包金额
     */
    public void saveUserRedPacketByRedis(Long redPacketId, Double unitAmount);
}
