package com.yanda.entity.generated;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_pay_info")
public class PayInfo implements Serializable {
    @Id
    @Column(name = "pay_id")
    private Integer payId;

    @Column(name = "pay_amount")
    private Float payAmount;

    @Column(name = "pay_time")
    private Date payTime;

    @Column(name = "pay_status")
    private Integer payStatus;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "trade_no")
    private String tradeNo;

    private static final long serialVersionUID = 1L;

    public PayInfo(Integer payId, Float payAmount, Date payTime, Integer payStatus, Integer userId, String tradeNo) {
        this.payId = payId;
        this.payAmount = payAmount;
        this.payTime = payTime;
        this.payStatus = payStatus;
        this.userId = userId;
        this.tradeNo = tradeNo;
    }

    public PayInfo() {
        super();
    }

    /**
     * @return pay_id
     */
    public Integer getPayId() {
        return payId;
    }

    /**
     * @param payId
     */
    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    /**
     * @return pay_amount
     */
    public Float getPayAmount() {
        return payAmount;
    }

    /**
     * @param payAmount
     */
    public void setPayAmount(Float payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * @return pay_time
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * @param payTime
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * @return pay_status
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * @param payStatus
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return trade_no
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * @param tradeNo
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public enum Col {
        payId("pay_id"),
        payAmount("pay_amount"),
        payTime("pay_time"),
        payStatus("pay_status"),
        userId("user_id"),
        tradeNo("trade_no");

        private final String column;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        Col(String column) {
            this.column = column;
        }

        public String desc() {
            return this.column + " DESC";
        }

        public String asc() {
            return this.column + " ASC";
        }
    }
}