package com.yanda.entity.generated;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_classify_info")
public class ClassifyInfo implements Serializable {
    @Id
    @Column(name = "classify_id")
    private Integer classifyId;

    @Column(name = "classify_name")
    private String classifyName;

    @Column(name = "classify_desc")
    private String classifyDesc;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "classify_order")
    private String classifyOrder;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "icon_url")
    private String iconUrl;

    private static final long serialVersionUID = 1L;

    public ClassifyInfo(Integer classifyId, String classifyName, String classifyDesc, Integer parentId, String classifyOrder, Date createTime, Date updateTime, String iconUrl) {
        this.classifyId = classifyId;
        this.classifyName = classifyName;
        this.classifyDesc = classifyDesc;
        this.parentId = parentId;
        this.classifyOrder = classifyOrder;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.iconUrl = iconUrl;
    }

    public ClassifyInfo() {
        super();
    }

    /**
     * @return classify_id
     */
    public Integer getClassifyId() {
        return classifyId;
    }

    /**
     * @param classifyId
     */
    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    /**
     * @return classify_name
     */
    public String getClassifyName() {
        return classifyName;
    }

    /**
     * @param classifyName
     */
    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName == null ? null : classifyName.trim();
    }

    /**
     * @return classify_desc
     */
    public String getClassifyDesc() {
        return classifyDesc;
    }

    /**
     * @param classifyDesc
     */
    public void setClassifyDesc(String classifyDesc) {
        this.classifyDesc = classifyDesc == null ? null : classifyDesc.trim();
    }

    /**
     * @return parent_id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return classify_order
     */
    public String getClassifyOrder() {
        return classifyOrder;
    }

    /**
     * @param classifyOrder
     */
    public void setClassifyOrder(String classifyOrder) {
        this.classifyOrder = classifyOrder == null ? null : classifyOrder.trim();
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
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return icon_url
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * @param iconUrl
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl == null ? null : iconUrl.trim();
    }
}