package com.yanda.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yanda.entity.JsonResult;
import com.yanda.entity.generated.UserInfo;


/**
 * 登录相关接口控制类
 * LoginController.java
 * @author chenli
 * @time 2018年3月7日 下午10:22:18
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController extends BaseController {
	
	/**
	 * 根据用户信息进行系统登录
	 * @param request 请求体
	 * @param user 用户实体
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public JsonResult login(HttpServletRequest request, @RequestBody UserInfo user) {
		request.getParameter("userName");
		return result(200, "hasLogin", user);
	}
	
}
