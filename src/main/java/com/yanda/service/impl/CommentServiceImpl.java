package com.yanda.service.impl;


import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public PageResult<CommentInfo> list(int pageNum, int pageSize, Long episodeId, Long parentId, String criteria) {
		Page<CommentInfo> pageInfo = PageHelper.startPage(pageNum, pageSize);
		CommentInfoExample example = new CommentInfoExample();
		if(criteria.equals("2")) {
			criteria = "create_time asc";
		} else if(criteria.equals("3")) {
			criteria = "agree_count desc";
		} else {
			criteria = "create_time desc";
		}
		example.createCriteria().andEpisodeIdEqualTo(episodeId).andParentIdEqualTo(parentId);
		example.setOrderByClause(criteria);
		mapper.selectByExample(example);
		PageResult<CommentInfo> pageResult = new PageResult<>(pageInfo.getTotal(), pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getResult());
		return pageResult;

	}

	@Override
	public synchronized void addAgreeCount(Map<String, Object> map) throws DOPException {
		for(String key: map.keySet()) {
			CommentInfo commentInfo = this.selectById(Long.valueOf(key));
			Integer agreeCount = commentInfo.getAgreeCount();
			agreeCount++;
			commentInfo.setAgreeCount(agreeCount);
			this.update(commentInfo);
		}
	}

	@Transactional(rollbackFor={DOPException.class})
	@Override
	public void saveComment(CommentInfo commentInfo) throws DOPException {
		if (commentInfo.getParentId() == null) {		// 如果传送数据中没有 parentId 属性，说明是直接评论
			commentInfo.setParentId(Long.valueOf("0"));	
		} else if(commentInfo.getParentId() != 0){		// 如果有parentId 属性，为 父评论添加 子评论数目
			CommentInfo parent = this.selectById(commentInfo.getParentId());
			Integer commentCount = parent.getCommentCount();
			commentCount ++;
			parent.setCommentCount(commentCount);
			this.update(parent);
		}
		this.save(commentInfo);
	}


	

}
