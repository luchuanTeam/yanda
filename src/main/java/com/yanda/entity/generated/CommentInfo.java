package com.yanda.entity.generated;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_comment_info")
public class CommentInfo implements Serializable {
    @Id
    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "episode_id")
    private Long episodeId;

    @Column(name = "agree_count")
    private Integer agreeCount;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "comment_count")
    private Integer commentCount;

    @Column(name = "user_name")
    private String userName;

    private String avatar;

    private static final long serialVersionUID = 1L;

    public CommentInfo(Long commentId, String commentContent, Date createTime, Long userId, Long episodeId, Integer agreeCount, Long parentId, Integer commentCount, String userName, String avatar) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.createTime = createTime;
        this.userId = userId;
        this.episodeId = episodeId;
        this.agreeCount = agreeCount;
        this.parentId = parentId;
        this.commentCount = commentCount;
        this.userName = userName;
        this.avatar = avatar;
    }

    public CommentInfo() {
        super();
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
     * @return comment_content
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * @param commentContent
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
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
     * @return agree_count
     */
    public Integer getAgreeCount() {
        return agreeCount;
    }

    /**
     * @param agreeCount
     */
    public void setAgreeCount(Integer agreeCount) {
        this.agreeCount = agreeCount;
    }

    /**
     * @return parent_id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * @return comment_count
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * @param commentCount
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
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
}