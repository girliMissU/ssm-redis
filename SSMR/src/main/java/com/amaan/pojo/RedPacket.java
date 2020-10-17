package com.amaan.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 佛祖保佑，永无BUG
 *
 * @author AMAAN
 * SSMR
 * 2020-08-30 19:51
 */
public class RedPacket implements Serializable {
    /**
     * 红包编号
     * primary key
     */
    private Long id;
    /**
     * 发红包用户，不是抢红包的
     */
    private Long userId;
    /**
     * 总金额
     */
    private Double amount;
    /**
     * 发红包时间，时间戳
     * 对于创建日期等不会再修改的时间，推荐使用DateTime
     */
    private Timestamp sendDate;
    /**
     * 小红包总数
     */
    private Integer total;
    /**
     * 每个小红包的金额
     */
    private Double unitAmount;
    /**
     * 剩余小红包个数，即库存
     */
    private Integer stock;
    /**
     * 用于乐观锁的版本号
     */
    private Integer version;
    /**
     * 备注
     */
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Timestamp getSendDate() {
        return sendDate;
    }

    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Double getUnitAmount() {
        return unitAmount;
    }

    public void setUnitAmount(Double unitAmount) {
        this.unitAmount = unitAmount;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "RedPacket{" +
                "id=" + id +
                ", userId=" + userId +
                ", amount=" + amount +
                ", sendDate=" + sendDate +
                ", total=" + total +
                ", unitAmount=" + unitAmount +
                ", stock=" + stock +
                ", version=" + version +
                ", note='" + note + '\'' +
                '}';
    }
}
