package com.yanda.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yanda.entity.JsonResult;
import com.yanda.entity.PageResult;
import com.yanda.entity.UserCollectDetailInfo;
import com.yanda.exception.DOPException;
import com.yanda.service.UserCollectService;
import com.yanda.util.StringUtil;

@RestController
@RequestMapping(value = "/collect")
public class UserCollectController extends BaseController {

	@Autowired
	private UserCollectService userCollectService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public JsonResult findCollectsByUserId(HttpServletRequest request) {
		String userId = getNotEmptyValue(request, "userId");
		if(StringUtil.isEmpty(userId)) {
			return result(-1, "用户id为空");
		}
		String pageNum = getValue(request, "pageNum", "1");
		String pageSize = getValue(request, "pageSize", "10");
		PageResult<UserCollectDetailInfo> pageResult = userCollectService.findUserCollectsByUserId(Long.valueOf(userId), Integer.valueOf(pageNum), 
						Integer.valueOf(pageSize));
		
		return result(200, "success", pageResult);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JsonResult deleteByCollectId(HttpServletRequest request) {
		String collectId = getNotEmptyValue(request, "id");
		if(StringUtil.isEmpty(collectId)) {
			return result(-1, "收藏编号为空");
		}
		try {
			userCollectService.deleteByCollectId(Long.valueOf(collectId));
			return result(200, "删除成功");
		} catch (NumberFormatException | DOPException e) {
			e.printStackTrace();
			return result(-1, "删除失败");
		}
		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JsonResult addByUIdAndEpisodeId(HttpServletRequest request) {
		String userId = getNotEmptyValue(request, "userId");
		if(StringUtil.isEmpty(userId)) {
			return result(-1, "用户id为空");
		}
		String episodeId = getNotEmptyValue(request, "episodeId");
		if(StringUtil.isEmpty(episodeId)) {
			return result(-1, "视频编号为空");
		}
		try {
			boolean flag = userCollectService.addByUIdAndEpisodeId(Long.valueOf(userId), Long.valueOf(episodeId));
			if(flag) {
				return result(200, "收藏成功");
			} else {
				return result(-1, "您已经收藏该文件");
			}
		} catch (NumberFormatException | DOPException e) {
			e.printStackTrace();
			return result(-1, "收藏失败", e.getMessage());
		}
		
	}
}
