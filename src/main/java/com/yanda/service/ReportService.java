package com.yanda.service;

import com.yanda.entity.ReportInfo;

public interface ReportService {
	
	/**
	 * 获取一级分类下各个分类的视频数量（若某分类下无视频将不在map中出现）
	 * @return
	 */
	ReportInfo getClassifyMvCount();
	
}
