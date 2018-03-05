package com.yanda.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yanda.entity.ClassifyDetailInfo;
import com.yanda.entity.WebClassifyInfo;
import com.yanda.entity.generated.MovieInfo;

public interface MovieClassifyMapper {

	List<MovieInfo> findMovieListByClassify(@Param("classifyId")int classifyId, @Param("searchKey")String searchKey,
			@Param("searchVal")String searchVal, @Param("order")String order);
	
	ClassifyDetailInfo findClassifyInfo(@Param("classifyId")int classifyId);
	
	List<WebClassifyInfo> findOneLevelClassifyList();
	
	List<WebClassifyInfo> findClassifyList(@Param("parentId")int parentId);
	
}
