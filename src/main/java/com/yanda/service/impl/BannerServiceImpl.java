package com.yanda.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.yanda.entity.generated.BannerInfo;
import com.yanda.entity.generated.BannerInfoExample;
import com.yanda.mapper.generated.BannerInfoMapper;
import com.yanda.service.IBaseService;

@Service
public class BannerServiceImpl implements IBaseService<BannerInfo> {
	
	@Autowired
	private BannerInfoMapper bannerInfoMapper;
	
	@Override
	public int addRecord(BannerInfo record) {
		return bannerInfoMapper.insertSelective(record);
	}

	@Override
	public int deleteRecord(int recordId) {
		return bannerInfoMapper.deleteByPrimaryKey(recordId);
	}

	@Override
	public int updateRecord(BannerInfo record) {
		return bannerInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<BannerInfo> list(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		BannerInfoExample bannerInfoExample = new BannerInfoExample();
		bannerInfoExample.setOrderByClause("update_time desc");
		return bannerInfoMapper.selectByExample(bannerInfoExample);
	}

}
