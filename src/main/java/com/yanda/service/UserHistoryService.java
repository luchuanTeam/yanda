package com.yanda.service;

import com.yanda.entity.PageResult;
import com.yanda.entity.UserHistoryDetailInfo;
import com.yanda.exception.DOPException;

public interface UserHistoryService {
	
	
	/**
	 * 根据用户id查询历史记录
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageResult<UserHistoryDetailInfo> findUserHistoriesByUserId(Long userId, int pageNum, int pageSize);
	
	/**
	 * 根据historyId 删除历史记录
	 * @param historyId
	 */
	void deleteByHistoryId(Long historyId) throws DOPException;
}
