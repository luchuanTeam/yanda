package com.yanda.entity.generated;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_mv_episode")
public class EpisodeInfo implements Serializable {
    /**
     * 集数ID
     */
    @Id
    @Column(name = "episode_id")
    private Long episodeId;

    /**
     * 当前集名字
     */
    @Column(name = "episode_name")
    private String episodeName;

    /**
     * 动画简介
     */
    @Column(name = "episode_intro")
    private String episodeIntro;

    /**
     * 当前集数
     */
    @Column(name = "episode_num")
    private Integer episodeNum;

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
     * 主视频ID
     */
    @Column(name = "mv_id")
    private Long mvId;

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
     * 视频类型： 1-视频 2-音频
     */
    private Integer type;

    private static final long serialVersionUID = 1L;

    public EpisodeInfo(Long episodeId, String episodeName, String episodeIntro, Integer episodeNum, Long imgAppendixId, Long mvAppendixId, Long mvId, Date createTime, Date updateTime, Integer type) {
        this.episodeId = episodeId;
        this.episodeName = episodeName;
        this.episodeIntro = episodeIntro;
        this.episodeNum = episodeNum;
        this.imgAppendixId = imgAppendixId;
        this.mvAppendixId = mvAppendixId;
        this.mvId = mvId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.type = type;
    }

    public EpisodeInfo() {
        super();
    }

    /**
     * 获取集数ID
     *
     * @return episode_id - 集数ID
     */
    public Long getEpisodeId() {
        return episodeId;
    }

    /**
     * 设置集数ID
     *
     * @param episodeId 集数ID
     */
    public void setEpisodeId(Long episodeId) {
        this.episodeId = episodeId;
    }

    /**
     * 获取当前集名字
     *
     * @return episode_name - 当前集名字
     */
    public String getEpisodeName() {
        return episodeName;
    }

    /**
     * 设置当前集名字
     *
     * @param episodeName 当前集名字
     */
    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName == null ? null : episodeName.trim();
    }

    /**
     * 获取动画简介
     *
     * @return episode_intro - 动画简介
     */
    public String getEpisodeIntro() {
        return episodeIntro;
    }

    /**
     * 设置动画简介
     *
     * @param episodeIntro 动画简介
     */
    public void setEpisodeIntro(String episodeIntro) {
        this.episodeIntro = episodeIntro == null ? null : episodeIntro.trim();
    }

    /**
     * 获取当前集数
     *
     * @return episode_num - 当前集数
     */
    public Integer getEpisodeNum() {
        return episodeNum;
    }

    /**
     * 设置当前集数
     *
     * @param episodeNum 当前集数
     */
    public void setEpisodeNum(Integer episodeNum) {
        this.episodeNum = episodeNum;
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
     * 获取主视频ID
     *
     * @return mv_id - 主视频ID
     */
    public Long getMvId() {
        return mvId;
    }

    /**
     * 设置主视频ID
     *
     * @param mvId 主视频ID
     */
    public void setMvId(Long mvId) {
        this.mvId = mvId;
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
     * 获取视频类型： 1-视频 2-音频
     *
     * @return type - 视频类型： 1-视频 2-音频
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置视频类型： 1-视频 2-音频
     *
     * @param type 视频类型： 1-视频 2-音频
     */
    public void setType(Integer type) {
        this.type = type;
    }

    public enum Col {
        episodeId("episode_id"),
        episodeName("episode_name"),
        episodeIntro("episode_intro"),
        episodeNum("episode_num"),
        imgAppendixId("img_appendix_id"),
        mvAppendixId("mv_appendix_id"),
        mvId("mv_id"),
        createTime("create_time"),
        updateTime("update_time"),
        type("type");

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