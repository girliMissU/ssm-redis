package com.amaan.dao;

import com.amaan.pojo.UserRedPacket;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * 佛祖保佑，永无BUG
 *
 * @author AMAAN
 * SSMR
 * 2020-08-30 20:27
 */
@Repository
public interface UserRedPacketDao {

    /**
     * 插入抢红包信息
     * @param userRedPacket 抢红包信息
     * @return 影响记录条数
     */
    @Insert("")
    public int grabRedPacket(UserRedPacket userRedPacket);

}
