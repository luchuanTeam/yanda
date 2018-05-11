package com.yanda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yanda.entity.PageResult;
import com.yanda.entity.UserHistoryDetailInfo;
import com.yanda.entity.generated.UserHistoryInfo;
import com.yanda.exception.DOPException;
import com.yanda.mapper.UserCustomMapper;
import com.yanda.mapper.generated.UserHistoryInfoMapper;
import com.yanda.service.UserHistoryService;

@Service
public class UserHistoryServiceImpl extends BaseServiceImpl<UserHistoryInfoMapper, UserHistoryInfo, Long> implements UserHistoryService {

	@Autowired
	private UserCustomMapper userCostomMapper;
	@Override
	public PageResult<UserHistoryDetailInfo> findUserHistoriesByUserId(Long userId, int pageNum, int pageSize) {
		Page<UserHistoryDetailInfo> pageInfo = PageHelper.startPage(pageNum, pageSize);
		userCostomMapper.findUserHistoryDetailByUserId(userId);
		PageResult<UserHistoryDetailInfo> pageResult = new PageResult<>(pageInfo.getTotal(), pageInfo.getPageNum(), 
				pageInfo.getPageSize(), pageInfo.getResult());
		return pageResult;
	}
	
	@Override
	public void deleteByHistoryId(Long historyId) throws DOPException {
		this.deleteById(historyId);
		
	}

}
