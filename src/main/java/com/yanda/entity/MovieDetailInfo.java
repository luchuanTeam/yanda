package com.yanda.entity;

import com.yanda.entity.generated.MovieInfo;

public class MovieDetailInfo extends MovieInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2891014268571652990L;

	public MovieDetailInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MovieDetailInfo(MovieInfo movieInfo) {
		super(movieInfo.getMvId(), movieInfo.getMvName(), movieInfo.getMvIntro(), movieInfo.getCreateTime(),
				movieInfo.getUpdateTime(), movieInfo.getClassifyId(), movieInfo.getImgAppendixId(), movieInfo.getMvAppendixId(), movieInfo.getEpisodeCount(), movieInfo.getMvPath());
		// TODO Auto-generated constructor stub
	}

	/**
	 * 分类名字
	 */
	private String classifyName;

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

}
