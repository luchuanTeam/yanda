package com.yanda.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yanda.component.MessageSender;
import com.yanda.entity.JsonResult;
import com.yanda.entity.PageResult;
import com.yanda.entity.UserDetailInfo;
import com.yanda.entity.generated.UserInfo;
import com.yanda.entity.generated.UserInfo.Col;
import com.yanda.entity.generated.UserInfoExample;
import com.yanda.exception.DOPException;
import com.yanda.mapper.generated.UserInfoMapper;
import com.yanda.service.UserService;
import com.yanda.util.DesEncryptUtil;
import com.yanda.util.RestTemplateUtil;
import com.yanda.util.StringUtil;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private MessageSender messageSender;
	@Autowired
	private UserInfoMapper userInfoMapper;

	private static final String KEY_DATA = "12345678"; // 加密的密钥
	private static final int COOKIE_AGE = 172800; // cookie的时限
	private static final String appId = "wx8c025f88b3f63c44";
	private static final String appSecret = "a308a19541497abdab9a2cad360188d3";
	private static final String CODE_KEY_PRE = "message#code#"; // 验证码存储key前缀
	private static final int CODE_EXPIRE = 2; // 验证码有效期，单位分钟
	

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JsonResult login(HttpServletRequest request, HttpServletResponse response) {
		String userName = getNotEmptyValue(request, "userName");
		if (StringUtil.isEmpty(userName)) {
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
		if (userInfo.getStatus().intValue() != 1) {
			return result(-1, "该账号已被停用");
		}
		
		UserDetailInfo user = userService.findUserDetailByUserId(userInfo.getUserId());
		
		String str = new Date().getTime() + "&" + JSON.toJSONString(user);
		String token = DesEncryptUtil.encryptToHex(str.getBytes(), KEY_DATA);
		String sessionId = request.getSession().getId();
		Cookie cookie = new Cookie(sessionId, token);
		cookie.setMaxAge(COOKIE_AGE); // 设置cookie时限为2天
		response.addCookie(cookie);
		Map<String, Object> map = new HashMap<>();
		map.put("sessionId", sessionId);
		map.put("token", token);
		map.put("userInfo", user);
		return result(200, "success", map);
	}

	/**
	 * 检查token的有效性
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkToken", method = RequestMethod.POST)
	public JsonResult checkToken(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String sessionId = getNotEmptyValue(request, "sessionId");
		String token = getNotEmptyValue(request, "token");
		if (sessionId == null || token == null) {
			return result(-1, "会话已过期，请重新登录");
		}
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				if (name.equals(sessionId)) {
					String value = cookie.getValue();
					if (value.equals(token)) {
						String str = DesEncryptUtil.decrypt(token, KEY_DATA); // 对传入的token
																				// 进行解密
						String userStr = str.substring(str.lastIndexOf("&") + 1);
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
	
	@CacheEvict(value = { "userList" }, allEntries = true, beforeInvocation = true)
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public JsonResult register(HttpServletRequest request) {
		String userName = getNotEmptyValue(request, "userName");
		if (StringUtil.isEmpty(userName)) {
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
		String nickName = getNotEmptyValue(request, "nickName");
		if (StringUtil.isEmpty(nickName)) {
			nickName = userName;
		}

		boolean flag = userService.findUserNameIsExist(userName);
		if (!flag) {
			try {
				UserInfo userInfo = new UserInfo();
				Date crTime = new Date();
				userInfo.setUserName(userName);
				userInfo.setPassword(password);
				userInfo.setMobile(mobile);
				userInfo.setNickName(nickName);
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
		if (!userName.equals("0")) {
			boolean flag = userService.findUserNameIsExist(userName);
			if (!flag) {
				return result(200, "success");
			}
		}
		return result(-1, "用户名已存在");
	}

	/**
	 * 获取用户列表数据
	 * 
	 * @param request
	 *            请求体
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            分页大小
	 * @param 用户昵称
	 *            | 用户名
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
	 * 
	 * @param request
	 *            请求体
	 * @param userInfo
	 *            用户实体
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
	
	/**
	 * 通过openid查询该微信用户是否存在
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findWechatIsExist", method = RequestMethod.GET)
	public JsonResult findWechatIsExist(HttpServletRequest request) {
		String openId = getNotEmptyValue(request, "openId");
		if (StringUtil.isEmpty(openId))
			return result(-1, "openid为空");
		UserInfo user = userService.findWechatIsExist(openId);
		return result(200, "success", user);

	}
	
	/**
	 * 通过微信注册用户
	 * @param request
	 * @return
	 */
	@CacheEvict(value = { "userList" }, allEntries = true, beforeInvocation = true)
	@RequestMapping(value = "/registerByWechat", method = RequestMethod.POST)
	public JsonResult registerByWechat(HttpServletRequest request) {
		String openId = getNotEmptyValue(request, "openId");
		if (StringUtil.isEmpty(openId))
			return result(-1, "openid为空");
		String avatar = getNotEmptyValue(request, "avatar");
		// 随机生成一个字符串作为用户名
		String userName = StringUtil.generateRandomWord(12, "wx");
		// 随机生成一个字符串作为密码
		String password = StringUtil.generateRandomCodeMixed(8);
		String nickName = getNotEmptyValue(request, "nickName");
		if (StringUtil.isEmpty(nickName)) {
			nickName = userName;
		}
		String sexStr = getNotEmptyValue(request, "sex");
		int sex = 3;
		if (StringUtil.isNotEmpty(sexStr)) {
			sex = Integer.valueOf(sexStr);
		}

		try {
			UserInfo userInfo = new UserInfo();
			Date crTime = new Date();
			userInfo.setUserName(userName);
			userInfo.setPassword(password);
			userInfo.setNickName(nickName);
			userInfo.setCreateTime(crTime);
			userInfo.setUpdateTime(crTime);
			userInfo.setSex(sex);
			userInfo.setAvatar(avatar);
			userInfo.setIsWechat(true);
			userInfo.setOpenId(openId);
			userService.save(userInfo);
			return result(200, "注册成功", userInfo);
		} catch (DOPException e) {
			return result(-1, e.getMessage());
		}
	}
	
	/**
	 * 调用微信接口获取openid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getOpenIdFromWeiXin", method = RequestMethod.GET)
	public JsonResult getOpenIdFromWeiXin(HttpServletRequest request) {
		String js_code = getNotEmptyValue(request, "js_code");
		if (StringUtil.isEmpty(js_code))
			return result(-1, "js_code为空");
		Map<String, Object> para = new HashMap<>();
		para.put("appid", appId);
		para.put("secret", appSecret);
		para.put("js_code", js_code);
		para.put("grant_type", "authorization_code");
		String result = RestTemplateUtil.getForObject("https://api.weixin.qq.com/sns/jscode2session", String.class, para);
		return result(200, "success", JSON.parse(result));

	}
	
	/**
	 * 发送短信验证码
	 * @param userId
	 * @param mobile
	 * @return
	 */
	@PostMapping(value = "/sendCode")
	public JsonResult sendCode(String userId, String mobile) {
		if (StringUtil.isEmpty(userId))
			return result(-1, "用户ID为空");
		if (StringUtil.isEmpty(mobile))
			return result(-1, "手机号为空");
		Random rad = new Random();  
        String code = rad.nextInt(10000) + "";
		messageSender.sendMessage(code, mobile);
		redisTemplate.opsForHash().put(CODE_KEY_PRE, userId, code);
		redisTemplate.expire(CODE_KEY_PRE + userId, CODE_EXPIRE, TimeUnit.MINUTES);
		return result(200, "发送成功");
	}
	
	/**
	 * 发送短信验证码
	 * @param userId
	 * @param mobile
	 * @return
	 */
	@PostMapping(value = "/bindMobile")
	public JsonResult bindMobile(String userId, String mobile, String code) {
		if (StringUtil.isEmpty(userId))
			return result(-1, "用户ID为空");
		if (StringUtil.isEmpty(mobile))
			return result(-1, "手机号为空");
		if (StringUtil.isEmpty(code))
			return result(-1, "验证码为空");
		Object redisCode = redisTemplate.opsForHash().get(CODE_KEY_PRE, userId);
		if (null == redisCode || !redisCode.toString().equals(code))
			return result(-1, "验证码错误或已失效");
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(Integer.valueOf(userId));
		userInfo.setMobile(mobile);
		try {
			userService.update(userInfo);
		} catch (DOPException e) {
			LOG.error("绑定用户手机号失败:"+e);
			return result(-1, "绑定失败");
		}
		return result(200, "绑定成功");
	}
	
	/*@Transactional
	@GetMapping("/batchUpdateUserNameAndPass")
	public JsonResult batchUpdateUserNameAndPass(HttpServletRequest request) {
		String token = request.getParameter("token");
		if (token == null || !token.equals("chenli"))
			return result(-1, "非法操作");
		UserInfoExample example = new UserInfoExample();
		example.createCriteria().andIsWechatEqualTo(true);
		List<UserInfo> users = userInfoMapper.selectByExampleSelective(example, Col.userId, Col.userName, Col.password);
		for (UserInfo user: users) {
			user.setUserName(StringUtil.generateRandomWord(12, "wx"));
			user.setPassword(StringUtil.generateRandomCodeMixed(8));
			userInfoMapper.updateByPrimaryKeySelective(user);
		}
		return result(200, "更新用户名和密码成功");
	}*/
}
