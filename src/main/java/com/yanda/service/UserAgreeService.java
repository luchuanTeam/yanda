package com.yanda.service;


import com.yanda.entity.PageResult;
import com.yanda.entity.generated.UserAgreeInfo;

public interface UserAgreeService extends BaseService<UserAgreeInfo, Long> {
	
	/**
	 * 根据评论Id 和 用户Id 获取用户对该评论是否点赞过的信息
	 * @param commentId
	 * @param userId
	 * @return
	 */
	UserAgreeInfo selectByCommentIdAndUserId(Long commentId, Long userId);
	
	/**
	 * 根据用户id 和 视频id 获取用户在该视频下所有点赞过的评论
	 * @param pageNum
	 * @param userId
	 * @param episodeId
	 * @return
	 */
	PageResult<UserAgreeInfo> list(Long userId, Long episodeId);
}
