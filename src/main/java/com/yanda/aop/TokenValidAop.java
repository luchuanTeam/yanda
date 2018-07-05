package com.yanda.aop;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yanda.entity.JsonResult;
import com.yanda.util.Const;
import com.yanda.util.DesEncryptUtil;
import com.yanda.util.StringUtil;

/**
 * 小程序登录凭证和接口请求token校验aop TokenValidAop.java
 * 
 * @author chenli
 * @time 2018年7月3日 下午4:47:35
 */
@Aspect
@Configuration
public class TokenValidAop {
	
	private static Logger LOG = Logger.getLogger(TokenValidAop.class);
	
	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	// 匹配com.yanda.controller包及其子包下的所有类的所有方法
	@Pointcut("execution(* com.yanda.controller..*.*(..)) && !execution(* com.yanda.controller.UserController.*(..))"
			+ "&& !execution(* com.yanda.controller.ClassifyController.*(..))"
			+ "&& !execution(* com.yanda.controller.MovieController.getClassify*(..))")
	public void executeService() {

	}

	/**
	 * 环绕拦截控制层的方法，若发生异常记录异常信息到数据库中
	 * 
	 * @param point
	 * @return
	 */
	@Around("executeService()")
	public Object handleControllerMethod(ProceedingJoinPoint point) {

		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String terminal = request.getHeader("terminal");
		String token = request.getHeader("token");
		if (StringUtil.isNotEmpty(terminal) && terminal.equals("wechat")) {
			if (StringUtil.isEmpty(token))
				return new JsonResult(401, "请先登录"); 
			String userName = "";
			try {
				String decryToken = DesEncryptUtil.decrypt(token, Const.KEY_DATA);
				userName = decryToken.split("&")[1];
			} catch (Exception e) {
				LOG.error("token值非法", e);
				return new JsonResult(401, "登录凭证非法,请重新登录"); 
			}
			
			
			Object userToken = redisTemplate.opsForHash().get(Const.TOKEN_KEY_PRE, userName);
			if (null == userToken || !userToken.toString().equals(token)) {
				return new JsonResult(401, "登录状态已失效,请重新登录"); 
			}
			// 重新刷新token有效期
			redisTemplate.opsForHash().put(Const.TOKEN_KEY_PRE, userName, token);
			redisTemplate.expire(Const.TOKEN_KEY_PRE + userName, Const.TOKEN_EXPIRE, TimeUnit.DAYS);
			
		}
		try {
			return point.proceed();
		} catch (Throwable e) {
			return new JsonResult(500, "内部错误"); 
		}
	}
}
