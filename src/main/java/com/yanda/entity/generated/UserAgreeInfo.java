package com.yanda.entity.generated;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "t_user_agree")
public class UserAgreeInfo implements Serializable {
    @Id
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "episode_id")
    private Long episodeId;

    @Column(name = "has_agree")
    private Integer hasAgree;

    private static final long serialVersionUID = 1L;

    public UserAgreeInfo(Long id, Long userId, Long commentId, Long episodeId, Integer hasAgree) {
        this.id = id;
        this.userId = userId;
        this.commentId = commentId;
        this.episodeId = episodeId;
        this.hasAgree = hasAgree;
    }
    
    public UserAgreeInfo(Long userId, Long commentId, Long episodeId, Integer hasAgree) {
		super();
		this.userId = userId;
		this.commentId = commentId;
		this.episodeId = episodeId;
		this.hasAgree = hasAgree;
	}



	public UserAgreeInfo() {
        super();
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
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
     * @return comment_id
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * @param commentId
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
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
     * @return has_agree
     */
    public Integer getHasAgree() {
        return hasAgree;
    }

    /**
     * @param hasAgree
     */
    public void setHasAgree(Integer hasAgree) {
        this.hasAgree = hasAgree;
    }
}