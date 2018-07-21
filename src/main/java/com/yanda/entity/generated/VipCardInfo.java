package com.yanda.entity.generated;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_vip_card_info")
public class VipCardInfo implements Serializable {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "card_id")
    private Integer cardId;

    /**
     * 会员卡号
     */
    @Column(name = "card_num")
    private String cardNum;

    /**
     * 卡号密码
     */
    @Column(name = "card_password")
    private String cardPassword;

    /**
     * 购买天数
     */
    @Column(name = "purchase_days")
    private Integer purchaseDays;

    /**
     * 会员卡创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 会员卡更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 会员卡到期时间
     */
    @Column(name = "exp_time")
    private Date expTime;

    /**
     * 持有该会员卡的用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 持有人昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 是否永久会员
     */
    @Column(name = "is_forever")
    private Boolean isForever;

    private static final long serialVersionUID = 1L;

    public VipCardInfo(Integer cardId, String cardNum, String cardPassword, Integer purchaseDays, Date createTime, Date updateTime, Date expTime, Integer userId, String nickName, Boolean isForever) {
        this.cardId = cardId;
        this.cardNum = cardNum;
        this.cardPassword = cardPassword;
        this.purchaseDays = purchaseDays;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.expTime = expTime;
        this.userId = userId;
        this.nickName = nickName;
        this.isForever = isForever;
    }

    public VipCardInfo() {
        super();
    }

    /**
     * 获取主键ID
     *
     * @return card_id - 主键ID
     */
    public Integer getCardId() {
        return cardId;
    }

    /**
     * 设置主键ID
     *
     * @param cardId 主键ID
     */
    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    /**
     * 获取会员卡号
     *
     * @return card_num - 会员卡号
     */
    public String getCardNum() {
        return cardNum;
    }

    /**
     * 设置会员卡号
     *
     * @param cardNum 会员卡号
     */
    public void setCardNum(String cardNum) {
        this.cardNum = cardNum == null ? null : cardNum.trim();
    }

    /**
     * 获取卡号密码
     *
     * @return card_password - 卡号密码
     */
    public String getCardPassword() {
        return cardPassword;
    }

    /**
     * 设置卡号密码
     *
     * @param cardPassword 卡号密码
     */
    public void setCardPassword(String cardPassword) {
        this.cardPassword = cardPassword == null ? null : cardPassword.trim();
    }

    /**
     * 获取购买天数
     *
     * @return purchase_days - 购买天数
     */
    public Integer getPurchaseDays() {
        return purchaseDays;
    }

    /**
     * 设置购买天数
     *
     * @param purchaseDays 购买天数
     */
    public void setPurchaseDays(Integer purchaseDays) {
        this.purchaseDays = purchaseDays;
    }

    /**
     * 获取会员卡创建时间
     *
     * @return create_time - 会员卡创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置会员卡创建时间
     *
     * @param createTime 会员卡创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取会员卡更新时间
     *
     * @return update_time - 会员卡更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置会员卡更新时间
     *
     * @param updateTime 会员卡更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取会员卡到期时间
     *
     * @return exp_time - 会员卡到期时间
     */
    public Date getExpTime() {
        return expTime;
    }

    /**
     * 设置会员卡到期时间
     *
     * @param expTime 会员卡到期时间
     */
    public void setExpTime(Date expTime) {
        this.expTime = expTime;
    }

    /**
     * 获取持有该会员卡的用户ID
     *
     * @return user_id - 持有该会员卡的用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置持有该会员卡的用户ID
     *
     * @param userId 持有该会员卡的用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取持有人昵称
     *
     * @return nick_name - 持有人昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置持有人昵称
     *
     * @param nickName 持有人昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * 获取是否永久会员
     *
     * @return is_forever - 是否永久会员
     */
    public Boolean getIsForever() {
        return isForever;
    }

    /**
     * 设置是否永久会员
     *
     * @param isForever 是否永久会员
     */
    public void setIsForever(Boolean isForever) {
        this.isForever = isForever;
    }

    public enum Col {
        cardId("card_id"),
        cardNum("card_num"),
        cardPassword("card_password"),
        purchaseDays("purchase_days"),
        createTime("create_time"),
        updateTime("update_time"),
        expTime("exp_time"),
        userId("user_id"),
        nickName("nick_name"),
        isForever("is_forever");

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