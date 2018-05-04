package com.yanda.entity.generated;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "t_search_keywords")
public class KeywordInfo implements Serializable {
    /**
     * 主键id
     */
    @Id
    private Integer id;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 是否热门
     */
    @Column(name = "is_hot")
    private Boolean isHot;

    /**
     * 是否默认关键词
     */
    @Column(name = "is_default")
    private Boolean isDefault;

    /**
     * 是否已删除
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * 排序级别
     */
    @Column(name = "sort_order")
    private Integer sortOrder;

    private static final long serialVersionUID = 1L;

    public KeywordInfo(Integer id, String keyword, Boolean isHot, Boolean isDefault, Boolean isDeleted, Integer sortOrder) {
        this.id = id;
        this.keyword = keyword;
        this.isHot = isHot;
        this.isDefault = isDefault;
        this.isDeleted = isDeleted;
        this.sortOrder = sortOrder;
    }

    public KeywordInfo() {
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
     * 获取是否热门
     *
     * @return is_hot - 是否热门
     */
    public Boolean getIsHot() {
        return isHot;
    }

    /**
     * 设置是否热门
     *
     * @param isHot 是否热门
     */
    public void setIsHot(Boolean isHot) {
        this.isHot = isHot;
    }

    /**
     * 获取是否默认关键词
     *
     * @return is_default - 是否默认关键词
     */
    public Boolean getIsDefault() {
        return isDefault;
    }

    /**
     * 设置是否默认关键词
     *
     * @param isDefault 是否默认关键词
     */
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
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

    /**
     * 获取排序级别
     *
     * @return sort_order - 排序级别
     */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     * 设置排序级别
     *
     * @param sortOrder 排序级别
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public enum Col {
        id("id"),
        keyword("keyword"),
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