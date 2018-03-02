package com.yanda.entity.generated;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_banner_info")
public class BannerInfo implements Serializable {
    @Id
    @Column(name = "banner_id")
    private Long bannerId;

    @Column(name = "banner_desc")
    private String bannerDesc;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 轮播图类型 1-电影 2-广告 3-其他
     */
    private Integer type;

    /**
     * 附件ID
     */
    @Column(name = "appendix_id")
    private Long appendixId;

    /**
     * 动画ID
     */
    @Column(name = "mv_id")
    private Long mvId;

    private static final long serialVersionUID = 1L;

    public BannerInfo(Long bannerId, String bannerDesc, Date createTime, Date updateTime, Integer type, Long appendixId, Long mvId) {
        this.bannerId = bannerId;
        this.bannerDesc = bannerDesc;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.type = type;
        this.appendixId = appendixId;
        this.mvId = mvId;
    }

    public BannerInfo() {
        super();
    }

    /**
     * @return banner_id
     */
    public Long getBannerId() {
        return bannerId;
    }

    /**
     * @param bannerId
     */
    public void setBannerId(Long bannerId) {
        this.bannerId = bannerId;
    }

    /**
     * @return banner_desc
     */
    public String getBannerDesc() {
        return bannerDesc;
    }

    /**
     * @param bannerDesc
     */
    public void setBannerDesc(String bannerDesc) {
        this.bannerDesc = bannerDesc == null ? null : bannerDesc.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取轮播图类型 1-电影 2-广告 3-其他
     *
     * @return type - 轮播图类型 1-电影 2-广告 3-其他
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置轮播图类型 1-电影 2-广告 3-其他
     *
     * @param type 轮播图类型 1-电影 2-广告 3-其他
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取附件ID
     *
     * @return appendix_id - 附件ID
     */
    public Long getAppendixId() {
        return appendixId;
    }

    /**
     * 设置附件ID
     *
     * @param appendixId 附件ID
     */
    public void setAppendixId(Long appendixId) {
        this.appendixId = appendixId;
    }

    /**
     * 获取动画ID
     *
     * @return mv_id - 动画ID
     */
    public Long getMvId() {
        return mvId;
    }

    /**
     * 设置动画ID
     *
     * @param mvId 动画ID
     */
    public void setMvId(Long mvId) {
        this.mvId = mvId;
    }
}