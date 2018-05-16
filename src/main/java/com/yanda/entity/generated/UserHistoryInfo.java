package com.yanda.entity.generated;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user_history")
public class UserHistoryInfo implements Serializable {
    @Id
    @Column(name = "history_id")
    private Long historyId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "episode_id")
    private Long episodeId;

    @Column(name = "watch_time")
    private Date watchTime;

    private Integer progress;

    private static final long serialVersionUID = 1L;

    public UserHistoryInfo(Long historyId, Long userId, Long episodeId, Date watchTime, Integer progress) {
        this.historyId = historyId;
        this.userId = userId;
        this.episodeId = episodeId;
        this.watchTime = watchTime;
        this.progress = progress;
    }

    public UserHistoryInfo() {
        super();
    }

    /**
     * @return history_id
     */
    public Long getHistoryId() {
        return historyId;
    }

    /**
     * @param historyId
     */
    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return episode_id
     */
    public Long getEpisodeId() {
        return episodeId;
    }

    /**
     * @param episodeId
     */
    public void setEpisodeId(Long episodeId) {
        this.episodeId = episodeId;
    }

    /**
     * @return watch_time
     */
    public Date getWatchTime() {
        return watchTime;
    }

    /**
     * @param watchTime
     */
    public void setWatchTime(Date watchTime) {
        this.watchTime = watchTime;
    }

    /**
     * @return progress
     */
    public Integer getProgress() {
        return progress;
    }

    /**
     * @param progress
     */
    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public enum Col {
        historyId("history_id"),
        userId("user_id"),
        episodeId("episode_id"),
        watchTime("watch_time"),
        progress("progress");

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