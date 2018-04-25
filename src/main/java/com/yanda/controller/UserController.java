package com.yanda.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yanda.entity.JsonResult;
import com.yanda.entity.generated.UserInfo;
import com.yanda.exception.DOPException;
import com.yanda.service.UserService;
import com.yanda.util.DesEncryptUtil;
import com.yanda.util.StringUtil;


@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	private static final String KEY_DATA = "12345678";		//加密的密钥
	private static final int COOKIE_AGE = 172800;			// cookie的时限
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JsonResult login(HttpServletRequest request, HttpServletResponse response) {
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
		String str = new Date().getTime() + "&" + JSON.toJSONString(userInfo);
		String token = DesEncryptUtil.encryptToHex(str.getBytes(), KEY_DATA);
		String sessionId = request.getSession().getId();
		Cookie cookie = new Cookie(sessionId, token);
		cookie.setMaxAge(COOKIE_AGE);   //设置cookie时限为2天
		response.addCookie(cookie);
		Map<String, String> map = new HashMap<>();
		map.put("sessionId", sessionId);
		map.put("token", token);
		return result(200, "success", map);
	}
	
	/**
	 * 检查token的有效性
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkToken", method = RequestMethod.POST)
	public JsonResult checkToken(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String sessionId = getNotEmptyValue(request, "sessionId");
		String token = getNotEmptyValue(request, "token");
		if(sessionId == null || token == null) {
			return result(-1, "会话已过期，请重新登录");
		}
		if(cookies != null) {
			for(Cookie cookie: cookies) {
				String name = cookie.getName();
				if(name.equals(sessionId)) {
					String value = cookie.getValue();
					if(value.equals(token)) {
						String str = DesEncryptUtil.decrypt(token, KEY_DATA);		//对传入的token 进行解密
						String userStr = str.substring(str.lastIndexOf("&")+1);
						UserInfo userInfo = (UserInfo) JSON.parseObject(userStr, UserInfo.class);
						return result(200, "验证通过", userInfo);
					} else {
						return result(-1, "验证未通过，请重新登录");
					}
				}
			}
		}
		return result(-1, "会话已过期，请重新登录");
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
		if(!flag) {
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
			if (!flag) {
				return result(200, "success");
			}
		}
		return result(-1, "用户名已存在");
	}
}
