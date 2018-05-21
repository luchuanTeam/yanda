package com.yanda.entity.generated;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_attachment_info")
public class AttachmentInfo implements Serializable {
    /**
     * 附件ID
     */
    @Id
    @Column(name = "appendix_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appendixId;

    /**
     * 附件上传时的名字
     */
    @Column(name = "old_filename")
    private String oldFilename;

    /**
     * 新生成的附件名
     */
    @Column(name = "new_filename")
    private String newFilename;

    /**
     * 附件后缀
     */
    @Column(name = "file_ext")
    private String fileExt;

    /**
     * 附件文件夹路径
     */
    @Column(name = "file_path")
    private String filePath;

    /**
     * 附件类型 10.图片 20.视频 30.其他
     */
    @Column(name = "file_type")
    private Integer fileType;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 音视频附件的总时长
     */
    private Long duration;

    private static final long serialVersionUID = 1L;

    public AttachmentInfo(Long appendixId, String oldFilename, String newFilename, String fileExt, String filePath, Integer fileType, Date createTime, Long duration) {
        this.appendixId = appendixId;
        this.oldFilename = oldFilename;
        this.newFilename = newFilename;
        this.fileExt = fileExt;
        this.filePath = filePath;
        this.fileType = fileType;
        this.createTime = createTime;
        this.duration = duration;
    }

    public AttachmentInfo() {
        super();
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
     * 获取附件上传时的名字
     *
     * @return old_filename - 附件上传时的名字
     */
    public String getOldFilename() {
        return oldFilename;
    }

    /**
     * 设置附件上传时的名字
     *
     * @param oldFilename 附件上传时的名字
     */
    public void setOldFilename(String oldFilename) {
        this.oldFilename = oldFilename == null ? null : oldFilename.trim();
    }

    /**
     * 获取新生成的附件名
     *
     * @return new_filename - 新生成的附件名
     */
    public String getNewFilename() {
        return newFilename;
    }

    /**
     * 设置新生成的附件名
     *
     * @param newFilename 新生成的附件名
     */
    public void setNewFilename(String newFilename) {
        this.newFilename = newFilename == null ? null : newFilename.trim();
    }

    /**
     * 获取附件后缀
     *
     * @return file_ext - 附件后缀
     */
    public String getFileExt() {
        return fileExt;
    }

    /**
     * 设置附件后缀
     *
     * @param fileExt 附件后缀
     */
    public void setFileExt(String fileExt) {
        this.fileExt = fileExt == null ? null : fileExt.trim();
    }

    /**
     * 获取附件文件夹路径
     *
     * @return file_path - 附件文件夹路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置附件文件夹路径
     *
     * @param filePath 附件文件夹路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    /**
     * 获取附件类型 10.图片 20.视频 30.其他
     *
     * @return file_type - 附件类型 10.图片 20.视频 30.其他
     */
    public Integer getFileType() {
        return fileType;
    }

    /**
     * 设置附件类型 10.图片 20.视频 30.其他
     *
     * @param fileType 附件类型 10.图片 20.视频 30.其他
     */
    public void setFileType(Integer fileType) {
        this.fileType = fileType;
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
     * 获取音视频附件的总时长
     *
     * @return duration - 音视频附件的总时长
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * 设置音视频附件的总时长
     *
     * @param duration 音视频附件的总时长
     */
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public enum Col {
        appendixId("appendix_id"),
        oldFilename("old_filename"),
        newFilename("new_filename"),
        fileExt("file_ext"),
        filePath("file_path"),
        fileType("file_type"),
        createTime("create_time"),
        duration("duration");

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