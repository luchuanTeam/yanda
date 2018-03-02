package com.yanda.entity.generated;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_movie_info")
public class MovieInfo implements Serializable {
    /**
     * 动画id
     */
    @Id
    @Column(name = "mv_id")
    private Long mvId;

    @Column(name = "mv_title")
    private String mvTitle;

    @Column(name = "mv_desc")
    private String mvDesc;

    /**
     * 电影时长  hh:mm:ss
     */
    @Column(name = "mv_duration")
    private String mvDuration;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 分类ID
     */
    @Column(name = "classify_id")
    private Integer classifyId;

    /**
     * 动画图片附件ID
     */
    @Column(name = "img_appendix_id")
    private Long imgAppendixId;

    /**
     * 动画视频附件ID
     */
    @Column(name = "mv_appendix_id")
    private Long mvAppendixId;

    private static final long serialVersionUID = 1L;

    public MovieInfo(Long mvId, String mvTitle, String mvDesc, String mvDuration, Date createTime, Date updateTime, Integer classifyId, Long imgAppendixId, Long mvAppendixId) {
        this.mvId = mvId;
        this.mvTitle = mvTitle;
        this.mvDesc = mvDesc;
        this.mvDuration = mvDuration;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.classifyId = classifyId;
        this.imgAppendixId = imgAppendixId;
        this.mvAppendixId = mvAppendixId;
    }

    public MovieInfo() {
        super();
    }

    /**
     * 获取动画id
     *
     * @return mv_id - 动画id
     */
    public Long getMvId() {
        return mvId;
    }

    /**
     * 设置动画id
     *
     * @param mvId 动画id
     */
    public void setMvId(Long mvId) {
        this.mvId = mvId;
    }

    /**
     * @return mv_title
     */
    public String getMvTitle() {
        return mvTitle;
    }

    /**
     * @param mvTitle
     */
    public void setMvTitle(String mvTitle) {
        this.mvTitle = mvTitle == null ? null : mvTitle.trim();
    }

    /**
     * @return mv_desc
     */
    public String getMvDesc() {
        return mvDesc;
    }

    /**
     * @param mvDesc
     */
    public void setMvDesc(String mvDesc) {
        this.mvDesc = mvDesc == null ? null : mvDesc.trim();
    }

    /**
     * 获取电影时长  hh:mm:ss
     *
     * @return mv_duration - 电影时长  hh:mm:ss
     */
    public String getMvDuration() {
        return mvDuration;
    }

    /**
     * 设置电影时长  hh:mm:ss
     *
     * @param mvDuration 电影时长  hh:mm:ss
     */
    public void setMvDuration(String mvDuration) {
        this.mvDuration = mvDuration == null ? null : mvDuration.trim();
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
     * 获取分类ID
     *
     * @return classify_id - 分类ID
     */
    public Integer getClassifyId() {
        return classifyId;
    }

    /**
     * 设置分类ID
     *
     * @param classifyId 分类ID
     */
    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    /**
     * 获取动画图片附件ID
     *
     * @return img_appendix_id - 动画图片附件ID
     */
    public Long getImgAppendixId() {
        return imgAppendixId;
    }

    /**
     * 设置动画图片附件ID
     *
     * @param imgAppendixId 动画图片附件ID
     */
    public void setImgAppendixId(Long imgAppendixId) {
        this.imgAppendixId = imgAppendixId;
    }

    /**
     * 获取动画视频附件ID
     *
     * @return mv_appendix_id - 动画视频附件ID
     */
    public Long getMvAppendixId() {
        return mvAppendixId;
    }

    /**
     * 设置动画视频附件ID
     *
     * @param mvAppendixId 动画视频附件ID
     */
    public void setMvAppendixId(Long mvAppendixId) {
        this.mvAppendixId = mvAppendixId;
    }
}