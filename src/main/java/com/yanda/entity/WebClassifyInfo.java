package com.yanda.entity;

import java.util.LinkedList;
import java.util.List;

public class WebClassifyInfo {
	
	private Integer id;
	
	private String label;
	
	private String desc;
	
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

	public List<WebClassifyInfo> getClassify() {
		return classify;
	}

	public void setClassify(List<WebClassifyInfo> classify) {
		this.classify = classify;
	}
	
	
	
}
