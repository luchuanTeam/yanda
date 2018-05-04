package com.yanda.entity.generated;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user_search")
public class UserSearchInfo implements Serializable {
    /**
     * 主键id
     */
    @Id
    private Integer id;

    /**
     * 搜索关键词
     */
    private String keyword;

    /**
     * 搜索时间
     */
    @Column(name = "add_time")
    private Date addTime;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 是否已删除
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    private static final long serialVersionUID = 1L;

    public UserSearchInfo(Integer id, String keyword, Date addTime, Integer userId, Boolean isDeleted) {
        this.id = id;
        this.keyword = keyword;
        this.addTime = addTime;
        this.userId = userId;
        this.isDeleted = isDeleted;
    }

    public UserSearchInfo() {
        super();
    }

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取搜索关键词
     *
     * @return keyword - 搜索关键词
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * 设置搜索关键词
     *
     * @param keyword 搜索关键词
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    /**
     * 获取搜索时间
     *
     * @return add_time - 搜索时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置搜索时间
     *
     * @param addTime 搜索时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取是否已删除
     *
     * @return is_deleted - 是否已删除
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否已删除
     *
     * @param isDeleted 是否已删除
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public enum Col {
        id("id"),
        keyword("keyword"),
        addTime("add_time"),
        userId("user_id"),
        isDeleted("is_deleted");

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