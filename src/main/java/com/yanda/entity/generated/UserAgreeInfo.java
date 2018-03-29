package com.yanda.entity.generated;

import java.io.Serializable;
import javax.persistence.*;

/**
 * 记录用户对某一评论是否点赞过的表
 * @author weipeng
 *
 */
@Table(name = "t_user_agree")
public class UserAgreeInfo implements Serializable {
    @Id
    private Long id;

    @Column(name = "userId")
    private Long userid;

    @Column(name = "commentId")
    private Long commentid;

    @Column(name = "hasAgree")
    private Integer hasagree;

    private static final long serialVersionUID = 1L;

    public UserAgreeInfo(Long id, Long userid, Long commentid, Integer hasagree) {
        this.id = id;
        this.userid = userid;
        this.commentid = commentid;
        this.hasagree = hasagree;
    }

    public UserAgreeInfo(Long userid, Long commentid, Integer hasagree) {
		super();
		this.userid = userid;
		this.commentid = commentid;
		this.hasagree = hasagree;
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
     * @return userId
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * @return commentId
     */
    public Long getCommentid() {
        return commentid;
    }

    /**
     * @param commentid
     */
    public void setCommentid(Long commentid) {
        this.commentid = commentid;
    }

    /**
     * @return hasAgree
     */
    public Integer getHasagree() {
        return hasagree;
    }

    /**
     * @param hasagree
     */
    public void setHasagree(Integer hasagree) {
        this.hasagree = hasagree;
    }
}