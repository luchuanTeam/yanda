package com.yanda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.yanda.service.BannerService;
import com.yanda.service.BaseService;

@Service
public class BannerServiceImpl extends BaseService<BannerInfoMapper ,BannerInfo, Long> implements BannerService {
	
	@Autowired
	private AttachmentServiceImpl attachmentService;



	@Override
	public PageResult<BannerInfo> list(int pageNum, int pageSize, String searchVal) {
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
	@Transactional(rollbackFor={DOPException.class})
	@Override
	public void addBanner(AttachmentInfo attachmentInfo, BannerInfo bannerInfo) throws DOPException {
		attachmentService.save(attachmentInfo);
		bannerInfo.setAppendixId(attachmentInfo.getAppendixId());
		this.save(bannerInfo);
	}

}
