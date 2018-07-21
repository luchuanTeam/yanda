package com.yanda.component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "file")
@Component
public class FileConfig {
	/**
	 * 临时上传目录
	 */
	private String tempPath;
	
	/**
	 * 文件上传一级路径
	 */
	private String uploadPath;
	
	/**
	 * 普通附件上传二级目录
	 */
	private String baseDir;
	
	/**
	 * 图片上传二级目录
	 */
	private String baseImgDir;
	
	/**
	 * 音视频上传二级目录
	 */
	private String baseVideoDir;
	
	/**
	 * 域名
	 */
	private String domain;

	public String getTempPath() {
		return tempPath;
	}

	public void setTempPath(String tempPath) {
		this.tempPath = tempPath;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	public String getBaseImgDir() {
		return baseImgDir;
	}

	public void setBaseImgDir(String baseImgDir) {
		this.baseImgDir = baseImgDir;
	}

	public String getBaseVideoDir() {
		return baseVideoDir;
	}

	public void setBaseVideoDir(String baseVideoDir) {
		this.baseVideoDir = baseVideoDir;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	
}
