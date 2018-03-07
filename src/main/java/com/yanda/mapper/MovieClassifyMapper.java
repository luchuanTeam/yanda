package com.yanda.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yanda.entity.ClassifyDetailInfo;
import com.yanda.entity.WebClassifyInfo;
import com.yanda.entity.generated.MovieInfo;

/**
 * 视频分类相关接口mapper
 * MovieClassifyMapper.java
 * @author chenli
 * @time 2018年3月7日 下午11:30:26
 */
public interface MovieClassifyMapper {
	/**
	 * 通过分类id获取视频列表
	 * @param classifyId 分类id
	 * @param searchKey 用于搜索的字段名
	 * @param searchVal 用于搜索的字段值
	 * @param order 排序字段
	 * @param pageNum 分页页数
	 * @param pageSize 分页大小
	 * @return
	 */
	List<MovieInfo> findMovieListByClassify(@Param("classifyId")int classifyId, @Param("searchKey")String searchKey,
			@Param("searchVal")String searchVal, @Param("order")String order);
	
	/**
	 * 根据分类id获取该分类的信息以及其父分类信息
	 * @param classifyId
	 * @return
	 */
	ClassifyDetailInfo findClassifyInfo(@Param("classifyId")int classifyId);
	
	/**
	 * 根据app字段需求，获取一级分类列表
	 * @return
	 */
	List<WebClassifyInfo> findOneLevelClassifyList();
	
	/**
	 * 根据app字段需求，通过父级分类id获取子级分类列表
	 * @param parentId
	 * @return
	 */
	List<WebClassifyInfo> findClassifyList(@Param("parentId")int parentId);
	
}
