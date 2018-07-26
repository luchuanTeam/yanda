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
import com.yanda.entity.generated.PaperInfo;
import com.yanda.entity.generated.PaperInfoExample;
import com.yanda.entity.generated.PaperInfoExample.Criteria;
import com.yanda.exception.DOPException;
import com.yanda.mapper.generated.PaperInfoMapper;
import com.yanda.service.AttachmentService;
import com.yanda.service.PaperService;

@Service
public class PaperServiceImpl extends BaseServiceImpl<PaperInfoMapper, PaperInfo, Integer> implements PaperService {
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Cacheable(value = "paperList")
	@Override
	public PageResult<PaperInfo> getList(int pageNum, int pageSize, String searchVal) {
		return this.getList(pageNum, pageSize, searchVal, 1);
	}
	
	
	@CacheEvict(value = {"paperList", "paper"}, allEntries=true, beforeInvocation=true)
	@Override
	public int deleteById(Integer id) throws DOPException {
		LOG.info("删除试题，清空试题缓存数据...");
		PaperInfo paperInfo = this.mapper.selectByPrimaryKey(id);
		attachmentService.deleteById(paperInfo.getAppendixId());
		return super.deleteById(id);
	}
	
	@CacheEvict(value = {"paperList", "paper"}, allEntries=true, beforeInvocation=true)
	@Override
	public int update(PaperInfo t) throws DOPException {
		return super.update(t);
	}
	
	@Cacheable(value = {"paper"})
	@Override
	public PaperInfo selectById(Integer id) throws DOPException {
		return super.selectById(id);
	}
	
	@CacheEvict(value = {"paperList", "paper"}, allEntries=true, beforeInvocation=true)
	@Override
	public int upsertSelective(PaperInfo t) throws DOPException {
		return super.upsertSelective(t);
	}
	
	@CacheEvict(value = {"paperList"}, allEntries=true, beforeInvocation=true)
	@Transactional
	@Override
	public int save(PaperInfo paper, AttachmentInfo attch) throws DOPException {
		attachmentService.save(attch);
		paper.setAppendixId(attch.getAppendixId());
		return super.save(paper);
	}

	
	@Cacheable(value = "paperList")
	@Override
	public PageResult<PaperInfo> getList(int pageNum, int pageSize, String searchVal, Integer paperType) {
		PaperInfoExample example = new PaperInfoExample();
		Criteria c1 = example.createCriteria();
		c1.andPaperTypeEqualTo(paperType);
		c1.andPaperNameLike("%" + searchVal + "%");
		
		Criteria c2 = example.createCriteria();
		c2.andPaperTypeEqualTo(paperType);
		c2.andPaperDescLike("%" + searchVal + "%");
		
		example.or(c2);
		Page<PaperInfo> pageInfo = PageHelper.startPage(pageNum, pageSize);
		this.mapper.selectByExample(example);
		PageResult<PaperInfo> pageResult = new PageResult<>(pageInfo.getTotal(), pageInfo.getPages(),
				pageInfo.getPageSize(), pageInfo.getResult());
		return pageResult;
	}


}
