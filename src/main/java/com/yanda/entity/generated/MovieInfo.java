package com.yanda.entity.generated;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_mv_info")
public class MovieInfo implements Serializable {
    /**
     * 视频id
     */
    @Id
    @Column(name = "mv_id")
    private Long mvId;

    /**
     * 视频名称
     */
    @Column(name = "mv_name")
    private String mvName;

    /**
     * 视频简介
     */
    @Column(name = "mv_intro")
    private String mvIntro;

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
     * 分类ID
     */
    @Column(name = "classify_id")
    private Integer classifyId;

    @Column(name = "classify_name")
    private String classifyName;

    /**
     * 图片附件ID
     */
    @Column(name = "img_appendix_id")
    private Long imgAppendixId;

    /**
     * 视频附件ID
     */
    @Column(name = "mv_appendix_id")
    private Long mvAppendixId;

    /**
     * 视频集数
     */
    @Column(name = "episode_count")
    private Integer episodeCount;

    /**
     * 存放视频的文件夹路径
     */
    @Column(name = "mv_path")
    private String mvPath;

    private static final long serialVersionUID = 1L;

    public MovieInfo(Long mvId, String mvName, String mvIntro, Date createTime, Date updateTime, Integer classifyId, String classifyName, Long imgAppendixId, Long mvAppendixId, Integer episodeCount, String mvPath) {
        this.mvId = mvId;
        this.mvName = mvName;
        this.mvIntro = mvIntro;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.classifyId = classifyId;
        this.classifyName = classifyName;
        this.imgAppendixId = imgAppendixId;
        this.mvAppendixId = mvAppendixId;
        this.episodeCount = episodeCount;
        this.mvPath = mvPath;
    }

    public MovieInfo() {
        super();
    }

    /**
     * 获取视频id
     *
     * @return mv_id - 视频id
     */
    public Long getMvId() {
        return mvId;
    }

    /**
     * 设置视频id
     *
     * @param mvId 视频id
     */
    public void setMvId(Long mvId) {
        this.mvId = mvId;
    }

    /**
     * 获取视频名称
     *
     * @return mv_name - 视频名称
     */
    public String getMvName() {
        return mvName;
    }

    /**
     * 设置视频名称
     *
     * @param mvName 视频名称
     */
    public void setMvName(String mvName) {
        this.mvName = mvName == null ? null : mvName.trim();
    }

    /**
     * 获取视频简介
     *
     * @return mv_intro - 视频简介
     */
    public String getMvIntro() {
        return mvIntro;
    }

    /**
     * 设置视频简介
     *
     * @param mvIntro 视频简介
     */
    public void setMvIntro(String mvIntro) {
        this.mvIntro = mvIntro == null ? null : mvIntro.trim();
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
     * @return classify_name
     */
    public String getClassifyName() {
        return classifyName;
    }

    /**
     * @param classifyName
     */
    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName == null ? null : classifyName.trim();
    }

    /**
     * 获取图片附件ID
     *
     * @return img_appendix_id - 图片附件ID
     */
    public Long getImgAppendixId() {
        return imgAppendixId;
    }

    /**
     * 设置图片附件ID
     *
     * @param imgAppendixId 图片附件ID
     */
    public void setImgAppendixId(Long imgAppendixId) {
        this.imgAppendixId = imgAppendixId;
    }

    /**
     * 获取视频附件ID
     *
     * @return mv_appendix_id - 视频附件ID
     */
    public Long getMvAppendixId() {
        return mvAppendixId;
    }

    /**
     * 设置视频附件ID
     *
     * @param mvAppendixId 视频附件ID
     */
    public void setMvAppendixId(Long mvAppendixId) {
        this.mvAppendixId = mvAppendixId;
    }

    /**
     * 获取视频集数
     *
     * @return episode_count - 视频集数
     */
    public Integer getEpisodeCount() {
        return episodeCount;
    }

    /**
     * 设置视频集数
     *
     * @param episodeCount 视频集数
     */
    public void setEpisodeCount(Integer episodeCount) {
        this.episodeCount = episodeCount;
    }

    /**
     * 获取存放视频的文件夹路径
     *
     * @return mv_path - 存放视频的文件夹路径
     */
    public String getMvPath() {
        return mvPath;
    }

    /**
     * 设置存放视频的文件夹路径
     *
     * @param mvPath 存放视频的文件夹路径
     */
    public void setMvPath(String mvPath) {
        this.mvPath = mvPath == null ? null : mvPath.trim();
    }
}