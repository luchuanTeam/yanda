package com.yanda.service.impl;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yanda.entity.PageResult;
import com.yanda.entity.generated.CommentInfo;
import com.yanda.entity.generated.CommentInfoExample;
import com.yanda.exception.DOPException;
import com.yanda.mapper.generated.CommentInfoMapper;
import com.yanda.service.CommentService;

@Service
public class CommentServiceImpl extends BaseServiceImpl<CommentInfoMapper, CommentInfo, Long> implements CommentService {

	@Override
	public PageResult<CommentInfo> list(int pageNum, int pageSize, Long episodeId, String criteria) {
		Page<CommentInfo> pageInfo = PageHelper.startPage(pageNum, pageSize);
		CommentInfoExample example = new CommentInfoExample();
		if(criteria.equals("2")) {
			criteria = "create_time desc";
		} else if(criteria.equals("3")) {
			criteria = "agree_count desc";
		} else {
			criteria = "create_time asc";
		}
		example.createCriteria().andEpisodeIdEqualTo(episodeId);
		example.setOrderByClause(criteria);
		mapper.selectByExample(example);
		PageResult<CommentInfo> pageResult = new PageResult<>(pageInfo.getTotal(), pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getResult());
		return pageResult;

	}

	@Override
	public synchronized void addAgreeCount(Long commentId) throws DOPException {
		CommentInfo commentInfo = this.selectById(commentId);
		Integer agreeCount = commentInfo.getAgreeCount();
		agreeCount++;
		commentInfo.setAgreeCount(agreeCount);
		this.save(commentInfo);

	}

}
