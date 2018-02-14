package com.yanda.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yanda.entity.JsonResult;
import com.yanda.entity.generated.UserInfo;

@RestController
@RequestMapping(value = "/login")
public class LoginController extends BaseController {
	
	@RequestMapping(method = RequestMethod.POST)
	public JsonResult login(HttpServletRequest request, @RequestBody UserInfo user) {
		request.getParameter("userName");
		return result(200, "hasLogin", user);
	}
	
}
