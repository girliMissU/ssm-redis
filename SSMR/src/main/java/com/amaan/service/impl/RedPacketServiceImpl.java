package com.amaan.service.impl;

import com.amaan.dao.RedPacketDao;
import com.amaan.pojo.RedPacket;
import com.amaan.service.RedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 佛祖保佑，永无BUG
 *
 * @author AMAAN
 * SSMR
 * 2020-08-30 20:21
 */
@Service("redPacketService")
public class RedPacketServiceImpl implements RedPacketService {

    @Autowired
    private RedPacketDao redPacketDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public RedPacket getRedPacket(Long id) {
        return redPacketDao.getRedPacket(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public int decreaseRedPacket(Long id) {
        return redPacketDao.decreaseRedPacket(id);
    }
}
