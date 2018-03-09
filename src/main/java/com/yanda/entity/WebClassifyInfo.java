package com.yanda.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * 简化版的分类实体，用于返回app使用
 * WebClassifyInfo.java
 * @author chenli
 * @time 2018年3月8日 上午9:59:04
 */
public class WebClassifyInfo {
	/**
	 * 分类id
	 */
	private Integer id;
	
	/**
	 * 分类名
	 */
	private String label;
	
	/**
	 * 分类描述
	 */
	private String desc;
	
	/**
	 * 分类图标地址
	 */
	private String iconUrl;
	
	/**
	 * 二级分类
	 */
	private List<WebClassifyInfo> classify = new LinkedList<WebClassifyInfo>();

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public List<WebClassifyInfo> getClassify() {
		return classify;
	}

	public void setClassify(List<WebClassifyInfo> classify) {
		this.classify = classify;
	}
	
	
	
}
