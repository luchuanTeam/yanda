package com.yanda.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yanda.entity.JsonResult;
import com.yanda.entity.UserCollectDetailInfo;
import com.yanda.service.UserCollectService;

@RestController
@RequestMapping(value = "/collect")
public class UserCollectController extends BaseController {

	@Autowired
	private UserCollectService userCollectService;
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public JsonResult findCollectsByUserId(HttpServletRequest request, @PathVariable Long userId) {
		if(userId == null) {
			return result(-1, "用户Id为空");
		}
		UserCollectDetailInfo userCollectDetailInfo = userCollectService.findUserCollectByUserId(userId);
		return result(200, "success", userCollectDetailInfo);
	}
}
