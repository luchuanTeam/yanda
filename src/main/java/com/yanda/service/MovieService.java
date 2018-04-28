package com.yanda.service;

import java.util.List;

import com.yanda.entity.MovieDetailInfo;
import com.yanda.entity.PageResult;
import com.yanda.entity.WebClassifyInfo;
import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.entity.generated.MovieInfo;
import com.yanda.exception.DOPException;

public interface MovieService extends BaseService<MovieInfo, Long> {
	/**
	 * 通过分类id获取视频列表  -- 废除，获取列表请使用getPubMovies
	 * @param classifyId 分类id
	 * @param searchKey 用于搜索的字段名
	 * @param searchVal 用于搜索的字段值
	 * @param order 排序字段
	 * @param pageNum 分页页数
	 * @param pageSize 分页大小
	 * @return
	 */
	@Deprecated
	PageResult<MovieInfo> findMovieListByClassify(int classifyId, String searchKey, String searchVal, String order,
			int pageNum, int pageSize);
	
	/**
	 * 根据分类id获取电影分类的全路径名
	 * @param classifyId
	 * @return
	 */
	String findMovieClassifyFullName(int classifyId);
	
	/**
	 * 获取视频列表 可根据视频名称搜索
	 * @param pageNum
	 * @param pageSize
	 * @param searchVal 视频名称
	 * @return
	 */
	PageResult<MovieDetailInfo> list(int pageNum, int pageSize, String searchVal);
	
	/**
	 * 添加一条视频
	 * 保存图片附件、新增视频两个操作为事务性操作，其中一个报错都需要回滚
	 * 将两个实体传入是为了在一个方法里进行数据库操作，形成一个事务
	 * @param movieInfo 视频实体记录
	 * @param attachmentInfo 图片附件实体记录
	 * @throws DOPException
	 */
	void addMovie(MovieInfo movieInfo, AttachmentInfo attachmentInfo) throws DOPException;
	
	/**
	 * 更新一条视频
	 * 保存图片附件、新增视频两个操作为事务性操作，其中一个报错都需要回滚
	 * 将两个实体传入是为了在一个方法里进行数据库操作，形成一个事务
	 * @param movieInfo 视频实体记录
	 * @param attachmentInfo 图片附件实体记录
	 * @throws DOPException
	 */
	void updateMovie(MovieInfo movieInfo, AttachmentInfo attachmentInfo) throws DOPException;
	
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
	
	/**
	 * 对已有的视频列表获取其分类全路径并赋值
	 * @param mvList
	 * @return 返回包含分类全路径的视频列表
	 */
	List<MovieDetailInfo> getMovieDetailInfos(List<MovieInfo> mvList);
	
	/**
	 * 获取视频集大于0的视频列表, 可通过分类ID查询该分类下的视频 当分类ID=0时查询全部分类的视频
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageResult<MovieDetailInfo> getPubMovies(int classifyId, int pageNum, int pageSize);
	
	/**
	 * 根据视频名称或者视频简介查询视频
	 * @param searchVal
	 * @return
	 */
	List<MovieInfo> findMovieByNameOrIntro(String searchVal);
}
