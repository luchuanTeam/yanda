package com.yanda.service;

import java.util.List;

import com.yanda.entity.MovieDetailInfo;
import com.yanda.entity.PageResult;
import com.yanda.entity.WebClassifyInfo;
import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.entity.generated.MovieInfo;
import com.yanda.exception.DOPException;

public interface MovieService extends BaseService<MovieInfo, Long> {

	PageResult<MovieInfo> findMovieListByClassify(int classifyId, String searchKey, String searchVal, String order,
			int pageNum, int pageSize);
	
	String findMovieClassifyFullName(int classifyId);
	
	PageResult<MovieDetailInfo> list(int pageNum, int pageSize, String searchVal);
	
	void addMovie(MovieInfo movieInfo, AttachmentInfo attachmentInfo) throws DOPException;
	/**
	 * 获取一级分类列表
	 * @return
	 */
	List<WebClassifyInfo> findOneLevelClassifyList();
	/**
	 * 通过父分类ID获取子分类列表
	 * @param parentId
	 * @return
	 */
	List<WebClassifyInfo> findClassifyList(int parentId);
}
