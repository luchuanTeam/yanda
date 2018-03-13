package com.yanda.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yanda.entity.JsonResult;
import com.yanda.entity.PageResult;
import com.yanda.entity.generated.CommentInfo;
import com.yanda.exception.DOPException;
import com.yanda.service.CommentService;
import com.yanda.util.StringUtil;

@RestController
@RequestMapping(value = "/comment")
public class CommentController extends BaseController {
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JsonResult listComments(HttpServletRequest request) {
		String pageNum = getValue(request, "pageNum", "1");
		String pageSize = getValue(request, "pageSize", "1");
		String criteria = getValue(request, "criteria", "1");
		String parentId = getValue(request, "parentId", "0");
		String episodeId = getNotEmptyValue(request, "episodeId");
		if (StringUtil.isEmpty(episodeId)) {
			return result(-1, "视频编号为空");
		}
		PageResult<CommentInfo> comments = commentService.list(Integer.valueOf(pageNum), Integer.valueOf(pageSize)
				, Long.valueOf(episodeId), Long.valueOf(parentId), criteria);
		return result(200, "success", comments);
	}
	
	@RequestMapping(value = "/addAgreeCount", method = RequestMethod.POST)
	public JsonResult addAgreeCount(HttpServletRequest request) {
		String commentId = getNotEmptyValue(request, "commentId");
		if (StringUtil.isEmpty(commentId)) {
			return result(-1, "评论编号为空");
		}
		try {
			commentService.addAgreeCount(Long.valueOf(commentId));
			return result(200, "success", "点赞成功");
		} catch (NumberFormatException | DOPException e) {
			return result(-1, "点赞失败");
		}
	}
	
	@RequestMapping(value = "/saveComment", method = RequestMethod.POST)
	public JsonResult saveComment(HttpServletRequest request, @RequestBody CommentInfo commentInfo) {
		commentInfo.setCreateTime(new Date());
		commentInfo.setAgreeCount(0);	
		try {
			commentService.saveComment(commentInfo);
			return result(200, "success", "评论成功");
		} catch (DOPException e) {
			e.printStackTrace();
			return result(-1, e.getMessage());
		}
		
	}
	

	
}
