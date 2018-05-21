package com.yanda.entity.generated;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_log_info")
public class LogInfo implements Serializable {
    /**
     * 日志ID
     */
    @Id
    @Column(name = "log_id")
    private Integer logId;

    /**
     * 请求url
     */
    @Column(name = "request_url")
    private String requestUrl;

    /**
     * 请求方法类型
     */
    private String method;

    /**
     * 请求ip
     */
    private String ip;

    /**
     * 方法全路径
     */
    @Column(name = "class_method")
    private String classMethod;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 失败消息
     */
    private String message;

    /**
     * 堆栈日志
     */
    private String error;

    private static final long serialVersionUID = 1L;

    public LogInfo(Integer logId, String requestUrl, String method, String ip, String classMethod, String params, Date createTime, String message, String error) {
        this.logId = logId;
        this.requestUrl = requestUrl;
        this.method = method;
        this.ip = ip;
        this.classMethod = classMethod;
        this.params = params;
        this.createTime = createTime;
        this.message = message;
        this.error = error;
    }

    public LogInfo() {
        super();
    }

    /**
     * 获取日志ID
     *
     * @return log_id - 日志ID
     */
    public Integer getLogId() {
        return logId;
    }

    /**
     * 设置日志ID
     *
     * @param logId 日志ID
     */
    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    /**
     * 获取请求url
     *
     * @return request_url - 请求url
     */
    public String getRequestUrl() {
        return requestUrl;
    }

    /**
     * 设置请求url
     *
     * @param requestUrl 请求url
     */
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl == null ? null : requestUrl.trim();
    }

    /**
     * 获取请求方法类型
     *
     * @return method - 请求方法类型
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置请求方法类型
     *
     * @param method 请求方法类型
     */
    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    /**
     * 获取请求ip
     *
     * @return ip - 请求ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置请求ip
     *
     * @param ip 请求ip
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * 获取方法全路径
     *
     * @return class_method - 方法全路径
     */
    public String getClassMethod() {
        return classMethod;
    }

    /**
     * 设置方法全路径
     *
     * @param classMethod 方法全路径
     */
    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod == null ? null : classMethod.trim();
    }

    /**
     * 获取请求参数
     *
     * @return params - 请求参数
     */
    public String getParams() {
        return params;
    }

    /**
     * 设置请求参数
     *
     * @param params 请求参数
     */
    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
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
     * 获取失败消息
     *
     * @return message - 失败消息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置失败消息
     *
     * @param message 失败消息
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    /**
     * 获取堆栈日志
     *
     * @return error - 堆栈日志
     */
    public String getError() {
        return error;
    }

    /**
     * 设置堆栈日志
     *
     * @param error 堆栈日志
     */
    public void setError(String error) {
        this.error = error == null ? null : error.trim();
    }

    public enum Col {
        logId("log_id"),
        requestUrl("request_url"),
        method("method"),
        ip("ip"),
        classMethod("class_method"),
        params("params"),
        createTime("create_time"),
        message("message"),
        error("error");

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