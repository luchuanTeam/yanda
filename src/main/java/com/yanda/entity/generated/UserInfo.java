package com.yanda.entity.generated;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user_info")
public class UserInfo implements Serializable {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    private String password;

    @Column(name = "nick_name")
    private String nickName;

    private String mobile;

    /**
     * 用户性别 1：男  2：女  3：未知
     */
    private Integer sex;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    private String avatar;

    /**
     * 账号状态  0：已停用  1：启用
     */
    private Integer status;

    /**
     * 是否微信用户
     */
    @Column(name = "is_wechat")
    private Boolean isWechat;

    /**
     * 用户绑定的微信openid
     */
    @Column(name = "open_id")
    private String openId;

    private static final long serialVersionUID = 1L;

    public UserInfo(Integer userId, String userName, String password, String nickName, String mobile, Integer sex, Date createTime, Date updateTime, String avatar, Integer status, Boolean isWechat, String openId) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.mobile = mobile;
        this.sex = sex;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.avatar = avatar;
        this.status = status;
        this.isWechat = isWechat;
        this.openId = openId;
    }

    public UserInfo() {
        super();
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
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * @return nick_name
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取用户性别 1：男  2：女  3：未知
     *
     * @return sex - 用户性别 1：男  2：女  3：未知
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置用户性别 1：男  2：女  3：未知
     *
     * @param sex 用户性别 1：男  2：女  3：未知
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * 获取账号状态  0：已停用  1：启用
     *
     * @return status - 账号状态  0：已停用  1：启用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置账号状态  0：已停用  1：启用
     *
     * @param status 账号状态  0：已停用  1：启用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取是否微信用户
     *
     * @return is_wechat - 是否微信用户
     */
    public Boolean getIsWechat() {
        return isWechat;
    }

    /**
     * 设置是否微信用户
     *
     * @param isWechat 是否微信用户
     */
    public void setIsWechat(Boolean isWechat) {
        this.isWechat = isWechat;
    }

    /**
     * 获取用户绑定的微信openid
     *
     * @return open_id - 用户绑定的微信openid
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置用户绑定的微信openid
     *
     * @param openId 用户绑定的微信openid
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }
}