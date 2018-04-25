package com.yanda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yanda.entity.PageResult;
import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.entity.generated.BannerInfo;
import com.yanda.entity.generated.BannerInfoExample;
import com.yanda.exception.DOPException;
import com.yanda.mapper.generated.BannerInfoMapper;
import com.yanda.service.AttachmentService;
import com.yanda.service.BannerService;

@Service
public class BannerServiceImpl extends BaseServiceImpl<BannerInfoMapper ,BannerInfo, Long> implements BannerService {
	
	@Autowired
	private AttachmentService attachmentService;


	@Cacheable(value = "bannerList")
	@Override
	public PageResult<BannerInfo> list(int pageNum, int pageSize, String searchVal) {
		LOG.info("轮播图数据列表将从数据库中获取...");
		Page<BannerInfo> pageInfo = PageHelper.startPage(pageNum, pageSize);
		BannerInfoExample bannerInfoExample = new BannerInfoExample();
		bannerInfoExample.createCriteria().andBannerDescLike("%" + searchVal + "%");
		bannerInfoExample.setOrderByClause("update_time desc");
		mapper.selectByExample(bannerInfoExample);
		PageResult<BannerInfo> pageResult = new PageResult<>(pageInfo.getTotal(), pageInfo.getPages(),
				pageInfo.getPageSize(), pageInfo.getResult());
		return pageResult;
	}
	
	/**
	 * 先保存附件记录，再保存轮播图记录
	 * @param attachmentInfo
	 * @param bannerInfo
	 * @throws DOPException 
	 */
	@CacheEvict(value = "bannerList", allEntries=true, beforeInvocation=true)
	@Transactional(rollbackFor={DOPException.class})
	@Override
	public void addBanner(AttachmentInfo attachmentInfo, BannerInfo bannerInfo) throws DOPException {
		LOG.info("添加轮播图，清空轮播图缓存数据...");
		attachmentService.save(attachmentInfo);
		bannerInfo.setAppendixId(attachmentInfo.getAppendixId());
		this.save(bannerInfo);
	}
	
	@CacheEvict(value = "bannerList", allEntries=true, beforeInvocation=true)
	@Transactional(rollbackFor={DOPException.class})
	@Override
	public int deleteById(Long id) throws DOPException {
		LOG.info("删除轮播图，清空轮播图缓存数据...");
		BannerInfo bannerInfo = this.mapper.selectByPrimaryKey(id);
		attachmentService.deleteById(bannerInfo.getAppendixId());
		return super.deleteById(id);
	}
	
	@CacheEvict(value = "bannerList", allEntries=true, beforeInvocation=true)
	@Override
	public int update(BannerInfo t) throws DOPException {
		LOG.info("更新轮播图，清空轮播图缓存数据...");
		return super.update(t);
	}
	
	
}
