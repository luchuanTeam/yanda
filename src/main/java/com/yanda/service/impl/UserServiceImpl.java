package com.yanda.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.yanda.entity.generated.UserInfo;
import com.yanda.entity.generated.UserInfoExample;
import com.yanda.exception.DOPException;
import com.yanda.mapper.generated.UserInfoMapper;
import com.yanda.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserInfoMapper, UserInfo, Long> implements UserService {

	@Override
	public UserInfo login(String userName, String password) {
		UserInfoExample example = new UserInfoExample();
		example.or().andUserNameEqualTo(userName).andPasswordEqualTo(password);
		example.or().andMobileEqualTo(userName).andPasswordEqualTo(password);
		UserInfo userInfo = mapper.selectOneByExample(example);
		return userInfo;
		
	}

	@Override
	public void register(String userName, String password) throws DOPException {
		UserInfo userInfo = new UserInfo();
		userInfo.setCreateTime(new Date());
		userInfo.setPassword(password);
		userInfo.setUpdateTime(new Date());
		userInfo.setUserName(userName);
		userInfo.setAvatar("");
		this.save(userInfo);
	}

	@Override
	public boolean findUserNameIsExist(String userName) {
		UserInfoExample example = new UserInfoExample();
		example.createCriteria().andUserNameEqualTo(userName);
		UserInfo userInfo = mapper.selectOneByExample(example);
		if(userInfo == null) {
			return false;
		}
		return true;
	}

}
