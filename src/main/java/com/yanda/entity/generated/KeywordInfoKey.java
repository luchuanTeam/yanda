package com.yanda.entity.generated;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "t_search_keywords")
public class KeywordInfoKey implements Serializable {
    /**
     * 关键词
     */
    @Id
    private String keyword;

    /**
     * 主键id
     */
    @Id
    private Integer id;

    private static final long serialVersionUID = 1L;

    public KeywordInfoKey(String keyword, Integer id) {
        this.keyword = keyword;
        this.id = id;
    }

    public KeywordInfoKey() {
        super();
    }

    /**
     * 获取关键词
     *
     * @return keyword - 关键词
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * 设置关键词
     *
     * @param keyword 关键词
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
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

    public enum Col {
        keyword("keyword"),
        id("id"),
        isHot("is_hot"),
        isDefault("is_default"),
        isDeleted("is_deleted"),
        sortOrder("sort_order");

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