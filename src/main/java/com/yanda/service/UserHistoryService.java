package com.yanda.service;

import java.util.List;

import com.yanda.entity.PageResult;
import com.yanda.entity.UserHistoryDetailInfo;
import com.yanda.entity.generated.UserHistoryInfo;
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
	
	/**
	 * 更新历史记录
	 * @throws DOPException
	 */
	void upsertUserHistoryInfo(UserHistoryInfo userHistoryInfo) throws DOPException;
	
	/**
	 * 根据 userId 和 episodeId 查找历史信息
	 * @param userId
	 * @param historyId
	 * @return
	 */
	UserHistoryInfo findByUserIdAndEpisodeId(Long userId, Long episodeId);
	
	
	/**
	 * 根据传入的数组 id 批量删除历史记录
	 * @param ids
	 * @throws DOPException
	 */
	void deleteByIdList(List<Long> ids) throws DOPException;
}
