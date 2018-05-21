package com.yanda.aop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.yanda.entity.JsonResult;
import com.yanda.entity.RequestParam;
import com.yanda.entity.generated.LogInfo;
import com.yanda.exception.DOPException;
import com.yanda.exception.NoLoginException;
import com.yanda.mapper.generated.LogInfoMapper;

/**
 * controller异常捕获处理aop
 * 
 * ExceptionHandleAop.java
 * 
 * @author chenli
 * @time 2018年5月19日 下午5:02:28
 */
@Aspect
@Configuration
public class ExceptionHandleAop {

	private static Logger LOG = Logger.getLogger(ExceptionHandleAop.class);
	@Autowired
	private LogInfoMapper logInfoMapper;

	// 匹配com.yanda.controller包及其子包下的所有类的所有方法
	@Pointcut("execution(* com.yanda.controller..*.*(..))")
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
		String requestUrl = request.getRequestURL().toString();
		String method = request.getMethod();
		String ip = request.getRemoteAddr();
		String classMethod = point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName();
		List<RequestParam> params = getParamsByRequest(request);
		LogInfo logInfo = new LogInfo();
		logInfo.setRequestUrl(requestUrl);
		logInfo.setMethod(method);
		logInfo.setClassMethod(classMethod);
		logInfo.setIp(ip);
		logInfo.setParams(JSON.toJSONString(params));
		logInfo.setCreateTime(new Date());

		try {
			return point.proceed();
		} catch (NoLoginException e) {
			saveLog(logInfo, e);
			return new JsonResult(e.getStatus(), e.getMessage());
		} catch (DOPException e) {
			saveLog(logInfo, e);
			return new JsonResult(e.getStatus(), e.getMessage());
		} catch (Throwable e) {
			saveLog(logInfo, e);
			return new JsonResult(500, "服务器内部错误！");
		}
	}
	
	/**
	 * 从请求体中获取参数列表
	 * @param request
	 * @return
	 */
	private List<RequestParam> getParamsByRequest(HttpServletRequest request) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		List<RequestParam> params = new ArrayList<RequestParam>();
		for (String key : parameterMap.keySet()) {
			RequestParam param = new RequestParam();
			param.setName(key);
			param.setValue(request.getParameter(key));
			params.add(param);
		}
		return params;
	}
	
	private void saveLog(LogInfo logInfo,  Throwable e) {
		LOG.error(e);
		logInfo.setMessage(e.getMessage());
		logInfo.setError(e.toString());
		logInfoMapper.insertSelective(logInfo);
	}
	
}
