package com.yanda.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanda.entity.UserCollectDetailInfo;
import com.yanda.entity.generated.UserCollectInfo;
import com.yanda.mapper.UserCustomMapper;
import com.yanda.mapper.generated.UserCollectInfoMapper;
import com.yanda.service.UserCollectService;

@Service
public class UserCollectServiceImpl extends BaseServiceImpl<UserCollectInfoMapper, UserCollectInfo, Long> implements UserCollectService {

	@Autowired
	private UserCustomMapper userCostomMapper;

	@Override
	public UserCollectDetailInfo findUserCollectByUserId(Long userId) {
		return userCostomMapper.findUserCollectDetailByUserId(userId);
	}

}
