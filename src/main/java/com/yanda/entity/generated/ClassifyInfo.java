package com.yanda.entity.generated;

import java.util.Date;

public class ClassifyInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_classify_info.classify_id
     *
     * @mbggenerated
     */
    private Integer classifyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_classify_info.classify_name
     *
     * @mbggenerated
     */
    private String classifyName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_classify_info.classify_desc
     *
     * @mbggenerated
     */
    private String classifyDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_classify_info.classify_order
     *
     * @mbggenerated
     */
    private String classifyOrder;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_classify_info.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_classify_info.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_classify_info
     *
     * @mbggenerated
     */
    public ClassifyInfo(Integer classifyId, String classifyName, String classifyDesc, String classifyOrder, Date createTime, Date updateTime) {
        this.classifyId = classifyId;
        this.classifyName = classifyName;
        this.classifyDesc = classifyDesc;
        this.classifyOrder = classifyOrder;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_classify_info
     *
     * @mbggenerated
     */
    public ClassifyInfo() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_classify_info.classify_id
     *
     * @return the value of t_classify_info.classify_id
     *
     * @mbggenerated
     */
    public Integer getClassifyId() {
        return classifyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_classify_info.classify_id
     *
     * @param classifyId the value for t_classify_info.classify_id
     *
     * @mbggenerated
     */
    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_classify_info.classify_name
     *
     * @return the value of t_classify_info.classify_name
     *
     * @mbggenerated
     */
    public String getClassifyName() {
        return classifyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_classify_info.classify_name
     *
     * @param classifyName the value for t_classify_info.classify_name
     *
     * @mbggenerated
     */
    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName == null ? null : classifyName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_classify_info.classify_desc
     *
     * @return the value of t_classify_info.classify_desc
     *
     * @mbggenerated
     */
    public String getClassifyDesc() {
        return classifyDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_classify_info.classify_desc
     *
     * @param classifyDesc the value for t_classify_info.classify_desc
     *
     * @mbggenerated
     */
    public void setClassifyDesc(String classifyDesc) {
        this.classifyDesc = classifyDesc == null ? null : classifyDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_classify_info.classify_order
     *
     * @return the value of t_classify_info.classify_order
     *
     * @mbggenerated
     */
    public String getClassifyOrder() {
        return classifyOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_classify_info.classify_order
     *
     * @param classifyOrder the value for t_classify_info.classify_order
     *
     * @mbggenerated
     */
    public void setClassifyOrder(String classifyOrder) {
        this.classifyOrder = classifyOrder == null ? null : classifyOrder.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_classify_info.create_time
     *
     * @return the value of t_classify_info.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_classify_info.create_time
     *
     * @param createTime the value for t_classify_info.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_classify_info.update_time
     *
     * @return the value of t_classify_info.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_classify_info.update_time
     *
     * @param updateTime the value for t_classify_info.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}