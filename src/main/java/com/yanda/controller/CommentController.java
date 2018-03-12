package com.yanda.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
		String episodeId = getNotEmptyValue(request, "episodeId");
		if (StringUtil.isEmpty(episodeId)) {
			return result(-1, "视频编号为空");
		}
		PageResult<CommentInfo> comments = commentService.list(Integer.valueOf(pageNum), Integer.valueOf(pageSize)
				, Long.valueOf(episodeId), criteria);
		return result(200, "success", comments);
	}
	
	@RequestMapping(value = "/addAgreeCount", method = RequestMethod.GET)
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
	
}
