package com.yanda.entity;

import com.yanda.entity.generated.ClassifyInfo;

public class ClassifyDetailInfo extends ClassifyInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2529498722662727313L;
	
	/**
	 * 父分类信息
	 */
	private ClassifyInfo parent;

	public ClassifyInfo getParent() {
		return parent;
	}

	public void setParent(ClassifyInfo parent) {
		this.parent = parent;
	}

	
}
