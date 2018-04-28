package com.yanda.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yanda.entity.JsonResult;
import com.yanda.entity.PageResult;
import com.yanda.entity.UserDetailInfo;
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
		UserDetailInfo user = userService.findUserDetailByUserId(userInfo.getUserId());
		String str = new Date().getTime() + "&" + JSON.toJSONString(user);
		String token = DesEncryptUtil.encryptToHex(str.getBytes(), KEY_DATA);
		String sessionId = request.getSession().getId();
		Cookie cookie = new Cookie(sessionId, token);
		cookie.setMaxAge(COOKIE_AGE);   //设置cookie时限为2天
		response.addCookie(cookie);
		Map<String, Object> map = new HashMap<>();
		map.put("sessionId", sessionId);
		map.put("token", token);
		map.put("userInfo", user);
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
						UserDetailInfo userInfo = (UserDetailInfo) JSON.parseObject(userStr, UserDetailInfo.class);
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
	
	/**
	 * 获取用户列表数据
	 * @param request 请求体
	 * @param pageNum 页码
	 * @param pageSize 分页大小
	 * @param 用户昵称 | 用户名
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JsonResult listBanners(HttpServletRequest request) {
		String pageNum = getValue(request, "pageNum", "1");
		String pageSize = getValue(request, "pageSize", "1");
		String searchVal = getNotEmptyValue(request, "searchVal");
		PageResult<UserInfo> userInfos = userService.list(Integer.valueOf(pageNum), Integer.valueOf(pageSize),
				searchVal);
		return result(200, "success", userInfos);
	}
	
	/**
	 * 更新用户
	 * @param request 请求体
	 * @param userInfo 用户实体
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonResult update(HttpServletRequest request, @RequestBody UserInfo userInfo) {
		userInfo.setUpdateTime(new Date());
		try {
			userService.update(userInfo);
			return result(200, "更新成功!");
		} catch (DOPException e) {
			return result(-1, "更新失败:" + e.getMessage());
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public JsonResult getUser(HttpServletRequest request, @PathVariable int id) {
		if (id == 0)
			return result(-1, "用户id为空");
		try {
			UserDetailInfo user = userService.findUserDetailByUserId(id);
			return result(200, "", user);
		} catch (Exception e) {
			return result(-1, "查询失败");
		}
	}
	
}
