package com.yanda.service;

import com.yanda.entity.generated.UserCollectInfo;


import com.yanda.entity.UserCollectDetailInfo;


public interface UserCollectService extends BaseService<UserCollectInfo, Long> {

	/**
	 * 根据用户id 查询我的收藏
	 * @param userId
	 * @return
	 */
	UserCollectDetailInfo findUserCollectByUserId(Long userId);
}
