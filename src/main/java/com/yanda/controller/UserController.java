package com.yanda.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yanda.entity.JsonResult;
import com.yanda.entity.generated.UserInfo;
import com.yanda.exception.DOPException;
import com.yanda.service.UserService;
import com.yanda.util.StringUtil;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JsonResult login(HttpServletRequest request) {
		String userName = getNotEmptyValue(request, "userName");
		if(StringUtil.isEmpty(userName)) {
			return result(-1, "请填写登录账户");
		}
		String password = getNotEmptyValue(request, "password");
		if (StringUtil.isEmpty(password)) {
			return result(-1, "请填写登录密码");
		}
		UserInfo userInfo = userService.login(userName, password);
		if (userInfo == null) {
			return result(-1, "用户名密码错误");
		}
		// 不返回用户密码
		userInfo.setPassword(null);
		return result(200, "success", userInfo);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public JsonResult register(HttpServletRequest request) {
		String userName = getNotEmptyValue(request, "userName");
		if(StringUtil.isEmpty(userName)) {
			return result(-1, "请填写登录账户");
		}
		String password = getNotEmptyValue(request, "password");
		if (StringUtil.isEmpty(password)) {
			return result(-1, "请填写登录密码");
		}
		String mobile = getNotEmptyValue(request, "mobile");
		if (StringUtil.isEmpty(mobile)) {
			return result(-1, "请填写手机号");
		}
		boolean flag = userService.findUserNameIsExist(userName);
		if(flag) {
			try {
				UserInfo userInfo = new UserInfo();
				Date crTime = new Date();
				userInfo.setUserName(userName);
				userInfo.setPassword(password);
				userInfo.setMobile(mobile);
				userInfo.setCreateTime(crTime);
				userInfo.setUpdateTime(crTime);
				userService.save(userInfo);
				return result(200, "注册成功");
			} catch (DOPException e) {
				return result(-1, e.getMessage());
			}
		} else {
			return result(-1, "用户名已存在");
		}
	}
	
	@RequestMapping(value = "/findUserNameIsExist", method = RequestMethod.POST)
	public JsonResult findUserNameIsExist(HttpServletRequest request) {
		String userName = getValue(request, "userName", "0");
		if(!userName.equals("0")) {
			boolean flag = userService.findUserNameIsExist(userName);
			if (flag) {
				return result(200, "success");
			}
		}
		return result(-1, "用户名已存在");
	}
}
