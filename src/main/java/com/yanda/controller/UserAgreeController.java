package com.yanda.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yanda.entity.JsonResult;
import com.yanda.entity.PageResult;
import com.yanda.entity.generated.UserAgreeInfo;
import com.yanda.service.UserAgreeService;
import com.yanda.util.StringUtil;

@RestController
@RequestMapping(value = "/userAgree")
public class UserAgreeController extends BaseController{
	
	@Autowired
	private UserAgreeService userAgreeService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JsonResult listUserAgree(HttpServletRequest request) {
		String userId = getNotEmptyValue(request, "userId");
		String episodeId = getNotEmptyValue(request, "episodeId");
		if(StringUtil.isEmpty(userId)) {
			return result(-1, "用户未登录");
		} else if (StringUtil.isEmpty(episodeId)) {
			return result(-1, "未获取到视频信息");
		}
		PageResult<UserAgreeInfo> pageResult = userAgreeService.list(Long.valueOf(userId), Long.valueOf(episodeId));
		return result(200, "success", pageResult);
	}

}
