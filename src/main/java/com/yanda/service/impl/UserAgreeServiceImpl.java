package com.yanda.service.impl;


import org.springframework.stereotype.Service;

import com.yanda.entity.generated.UserAgreeInfo;
import com.yanda.entity.generated.UserAgreeInfoExample;
import com.yanda.mapper.generated.UserAgreeInfoMapper;
import com.yanda.service.UserAgreeService;

@Service
public class UserAgreeServiceImpl extends BaseServiceImpl<UserAgreeInfoMapper, UserAgreeInfo, Long> implements UserAgreeService {

	
	@Override
	public UserAgreeInfo selectByCommentIdAndUserId(Long commentId, Long userId) {
		// TODO Auto-generated method stub
		UserAgreeInfoExample example = new UserAgreeInfoExample();
		example.createCriteria().andCommentidEqualTo(commentId).andUseridEqualTo(userId);
		UserAgreeInfo userAgreeInfo = mapper.selectOneByExample(example);
		return userAgreeInfo;
	}

}
