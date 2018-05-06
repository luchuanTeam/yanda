package com.yanda.entity.generated;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user_search")
public class UserSearchInfo extends UserSearchInfoKey implements Serializable {
    /**
     * 搜索时间
     */
    @Column(name = "add_time")
    private Date addTime;

    /**
     * 是否已删除
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    private static final long serialVersionUID = 1L;

    public UserSearchInfo(String keyword, Integer userId, Date addTime, Boolean isDeleted) {
        super(keyword, userId);
        this.addTime = addTime;
        this.isDeleted = isDeleted;
    }

    public UserSearchInfo() {
        super();
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