package com.yanda.entity.generated;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "t_user_search")
public class UserSearchInfoKey implements Serializable {
    /**
     * 搜索关键词
     */
    @Id
    private String keyword;

    /**
     * 用户ID
     */
    @Id
    @Column(name = "user_id")
    private Integer userId;

    private static final long serialVersionUID = 1L;

    public UserSearchInfoKey(String keyword, Integer userId) {
        this.keyword = keyword;
        this.userId = userId;
    }

    public UserSearchInfoKey() {
        super();
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

    public enum Col {
        keyword("keyword"),
        userId("user_id"),
        addTime("add_time"),
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