package com.yanda.entity.generated;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user_collect")
public class UserCollectInfo implements Serializable {
    @Id
    @Column(name = "collect_id")
    private Long collectId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "episode_id")
    private Long episodeId;

    @Column(name = "collect_time")
    private Date collectTime;

    private static final long serialVersionUID = 1L;

    public UserCollectInfo(Long collectId, Long userId, Long episodeId, Date collectTime) {
        this.collectId = collectId;
        this.userId = userId;
        this.episodeId = episodeId;
        this.collectTime = collectTime;
    }

    public UserCollectInfo() {
        super();
    }

    /**
     * @return collect_id
     */
    public Long getCollectId() {
        return collectId;
    }

    /**
     * @param collectId
     */
    public void setCollectId(Long collectId) {
        this.collectId = collectId;
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
     * @return collect_time
     */
    public Date getCollectTime() {
        return collectTime;
    }

    /**
     * @param collectTime
     */
    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public enum Col {
        collectId("collect_id"),
        userId("user_id"),
        episodeId("episode_id"),
        collectTime("collect_time");

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