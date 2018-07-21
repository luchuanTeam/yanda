package com.yanda.entity.generated;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_paper_info")
public class PaperInfo implements Serializable {
    /**
     * 试题id
     */
    @Id
    private Integer id;

    /**
     * 试题名称
     */
    @Column(name = "paper_name")
    private String paperName;

    /**
     * 试题描述
     */
    @Column(name = "paper_desc")
    private String paperDesc;

    /**
     * 附件ID
     */
    @Column(name = "appendix_id")
    private Long appendixId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public PaperInfo(Integer id, String paperName, String paperDesc, Long appendixId, Date createTime, Date updateTime) {
        this.id = id;
        this.paperName = paperName;
        this.paperDesc = paperDesc;
        this.appendixId = appendixId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public PaperInfo() {
        super();
    }

    /**
     * 获取试题id
     *
     * @return id - 试题id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置试题id
     *
     * @param id 试题id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取试题名称
     *
     * @return paper_name - 试题名称
     */
    public String getPaperName() {
        return paperName;
    }

    /**
     * 设置试题名称
     *
     * @param paperName 试题名称
     */
    public void setPaperName(String paperName) {
        this.paperName = paperName == null ? null : paperName.trim();
    }

    /**
     * 获取试题描述
     *
     * @return paper_desc - 试题描述
     */
    public String getPaperDesc() {
        return paperDesc;
    }

    /**
     * 设置试题描述
     *
     * @param paperDesc 试题描述
     */
    public void setPaperDesc(String paperDesc) {
        this.paperDesc = paperDesc == null ? null : paperDesc.trim();
    }

    /**
     * 获取附件ID
     *
     * @return appendix_id - 附件ID
     */
    public Long getAppendixId() {
        return appendixId;
    }

    /**
     * 设置附件ID
     *
     * @param appendixId 附件ID
     */
    public void setAppendixId(Long appendixId) {
        this.appendixId = appendixId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public enum Col {
        id("id"),
        paperName("paper_name"),
        paperDesc("paper_desc"),
        appendixId("appendix_id"),
        createTime("create_time"),
        updateTime("update_time");

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