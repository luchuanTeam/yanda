package com.yanda.service.impl;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yanda.entity.PageResult;
import com.yanda.entity.UserCollectDetailInfo;
import com.yanda.entity.generated.UserCollectInfo;
import com.yanda.exception.DOPException;
import com.yanda.mapper.UserCustomMapper;
import com.yanda.mapper.generated.UserCollectInfoMapper;
import com.yanda.service.UserCollectService;

@Service
public class UserCollectServiceImpl extends BaseServiceImpl<UserCollectInfoMapper, UserCollectInfo, Long> implements UserCollectService {

	@Autowired
	private UserCustomMapper userCostomMapper;

	@Override
	public PageResult<UserCollectDetailInfo> findUserCollectsByUserId(Long userId, int pageNum, int pageSize) {
		Page<UserCollectDetailInfo> pageInfo = PageHelper.startPage(pageNum, pageSize);
		userCostomMapper.findUserCollectDetailByUserId(userId);
		PageResult<UserCollectDetailInfo> pageResult = new PageResult<>(pageInfo.getTotal(), pageInfo.getPageNum(), 
				pageInfo.getPageSize(), pageInfo.getResult());
		return pageResult;
	}

	@Override
	public void deleteByCollectId(Long collectId) throws DOPException {
		this.deleteById(collectId);
	}

	@Override
	public void addByUIdAndEpisodeId(Long userId, Long episodeId) throws DOPException {
		UserCollectInfo userCollectInfo = new UserCollectInfo();
		userCollectInfo.setUserId(userId);
		userCollectInfo.setEpisodeId(episodeId);
		userCollectInfo.setCollectTime(new Date());
		this.save(userCollectInfo);
	}
	
	

}
