package com.yanda.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanda.entity.generated.RoleInfo;
import com.yanda.entity.generated.RoleInfoExample;
import com.yanda.entity.generated.UserRoleInfo;
import com.yanda.entity.generated.UserRoleInfoExample;
import com.yanda.mapper.UserCustomMapper;
import com.yanda.mapper.generated.RoleInfoMapper;
import com.yanda.mapper.generated.UserRoleInfoMapper;
import com.yanda.service.UserRoleService;
import com.yanda.util.StringUtil;


@Service
public class UserRoleServiceImpl extends BaseServiceImpl<RoleInfoMapper ,RoleInfo, Integer> implements UserRoleService {
	
	@Autowired
	private UserCustomMapper userCustomMapper;
	@Autowired
	private UserRoleInfoMapper userRoleInfoMapper;
	
	@Override
	public List<RoleInfo> findUserRoleByUserId(int userId) {
		return userCustomMapper.findUserRoleByUserId(userId);
	}
	
	@Cacheable(value = "roleList")
	@Override
	public List<RoleInfo> list() {
		return this.mapper.selectByExample(new RoleInfoExample());
	}
	
	@Transactional
	@Override
	public void updateUserRoles(int userId, String roleIds) {
		UserRoleInfoExample example = new UserRoleInfoExample();
		example.createCriteria().andUserIdEqualTo(userId);
		userRoleInfoMapper.deleteByExample(example);
		if (StringUtil.isEmpty(roleIds))
			return;
		String[] roleIdArr =  roleIds.split(",");
		for (String roleId: roleIdArr) {
			UserRoleInfo userRole = new UserRoleInfo();
			userRole.setUserId(userId);
			userRole.setRoleId(Integer.valueOf(roleId));
			userRoleInfoMapper.insertSelective(userRole);
		}
		
	}
	
}
