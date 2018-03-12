package com.yanda.service;

import com.yanda.entity.PageResult;
import com.yanda.entity.generated.CommentInfo;
import com.yanda.exception.DOPException;

public interface CommentService extends BaseService<CommentInfo, Long> {

	/**
	 * 根据视频id获取对应的评论列表
	 * @param pageNum
	 * @param pageSize
	 * @param episodeId	视频Id
	 * @param criteria 评论展示的排序条件,暂时允许3种情况 '1'代表默认排序(时间顺序) '2'代表最新发表 '3'代表根据点赞数排序
	 * @return
	 */
	PageResult<CommentInfo> list(int pageNum, int pageSize, Long episodeId, String criteria);
	
	/**
	 * 增加评论点赞数
	 * @param commentId
	 * @throws DOPException 
	 */
	void addAgreeCount(Long commentId) throws DOPException;
}
