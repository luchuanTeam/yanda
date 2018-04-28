package com.yanda.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.MapKey;

public interface ReportMapper {
	
	/**
	 * 获取一级分类下各个分类的视频数量（若某分类下无视频将不在map中出现）
	 * @return
	 */
	@MapKey("parent_id")
	Map<Integer, Map<Integer,Long>> getClassifyMvCount();
	
}
