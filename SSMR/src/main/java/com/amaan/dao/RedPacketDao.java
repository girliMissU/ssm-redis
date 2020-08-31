package com.amaan.dao;

import com.amaan.pojo.RedPacket;
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
     *
     * @param id 红包id
     * @return 红包具体信息
     */
    @Select("select id, user_id as userId, amount, send_date as sendDate, total, unit_amount as unitAmount, stock, version, note from T_RED_PACKET where id=#{id}")
    public RedPacket getRedPacket(Long id);

    /**
     * 扣减红包数
     * @param id 红包id
     * @return 更新记录条数
     */
    @Update("update T_RED_PACKET set stock=stock-1 where id = #{id}")
    public int decreaseRedPacket(Long id);
}
