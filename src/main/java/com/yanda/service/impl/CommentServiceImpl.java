package com.yanda.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yanda.entity.PageResult;
import com.yanda.entity.generated.CommentInfo;
import com.yanda.entity.generated.CommentInfoExample;
import com.yanda.entity.generated.UserAgreeInfo;
import com.yanda.exception.DOPException;
import com.yanda.mapper.generated.CommentInfoMapper;
import com.yanda.service.CommentService;
import com.yanda.service.UserAgreeService;

@Service
public class CommentServiceImpl extends BaseServiceImpl<CommentInfoMapper, CommentInfo, Long> implements CommentService {

	@Autowired
	private UserAgreeService userAgreeService;
	
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
	@Transactional(rollbackFor={DOPException.class})
	public synchronized void toggleAgreeCount(Long commentId, Long episodeId ,Long userId) throws DOPException {
		CommentInfo commentInfo = this.selectById(commentId);
		Integer agreeCount = commentInfo.getAgreeCount();
		UserAgreeInfo userAgreeInfo = userAgreeService.selectByCommentIdAndUserId(commentId, userId);
		if(userAgreeInfo == null) {
			// 原则上不要去动自动生成的类，否则下次生成会覆盖掉
			userAgreeInfo = new UserAgreeInfo();
			userAgreeInfo.setUserId(userId);
			userAgreeInfo.setCommentId(commentId);
			userAgreeInfo.setHasAgree(1);
			agreeCount++;
			userAgreeService.save(userAgreeInfo);
		} else {
			Integer hasAgree = userAgreeInfo.getHasAgree();
			if(hasAgree == 0) {
				agreeCount++;
				hasAgree = 1;
			} else {
				agreeCount--;
				hasAgree = 0;
			}
			userAgreeInfo.setHasAgree(hasAgree);
			userAgreeService.update(userAgreeInfo);
		}
		commentInfo.setAgreeCount(agreeCount);
		this.update(commentInfo);
		
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
