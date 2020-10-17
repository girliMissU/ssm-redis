package com.amaan.service;

import com.amaan.pojo.RedPacket;

/**
 * 佛祖保佑，永无BUG
 *
 * @author AMAAN
 * SSMR
 * 2020-08-30 20:19
 */
public interface RedPacketService {
    /**
     * 获取红包信息
     * @param id 红包id
     * @return 红包具体信息
     */
    RedPacket getRedPacket(Long id);
    /**
     * 扣减红包数
     * @param id 红包id
     * @return 更新记录条数
     */
    int decreaseRedPacket(Long id);

}
