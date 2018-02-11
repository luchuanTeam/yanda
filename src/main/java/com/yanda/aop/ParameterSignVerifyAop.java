package com.yanda.aop;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.trs.idm.util.Base64Util;
import com.yanda.entity.JsonResult;
import com.yanda.exception.DOPException;
import com.yanda.util.StringUtil;

/**
 * 说明：请求参数签名校验
 * 
 * @author chenli
 * @time 2018年1月22日 上午11:05:03
 */
//@Aspect
//@Configuration
public class ParameterSignVerifyAop {

	private static Logger LOG = LoggerFactory.getLogger(ParameterSignVerifyAop.class);


	// 匹配com.yanda.controller包及其子包下的所有类的所有方法
	@Pointcut("execution(* com.yanda.controller..*.*(..)) && !execution(* com.yanda.controller.IndexController.*(..)) && !execution(* com.yanda.controller.TradeCenter2Controller.*(..))")
	public void executeService() {

	}

	/**
	 * 从request参数中提取所有请求参数，并对应用ID和应用密钥进行签名比对
	 * 
	 * @param joinPoint
	 * @throws DOPException
	 */
	public void parameterVerify(ProceedingJoinPoint joinPoint) throws DOPException {
		Object[] objArr = joinPoint.getArgs();
		LOG.debug("请求目标方法为：{}",
				joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

		for (int i = 0; i < objArr.length; i++) {
			// 找出request，从request取出所有参数进行安全校验
			if (objArr[i] instanceof HttpServletRequest) {
				HttpServletRequest request = (HttpServletRequest) objArr[i];
				String appId = request.getParameter("appId");
				String appSecret = request.getParameter("appSecret");
				String data = StringEscapeUtils.unescapeHtml(request.getParameter("data"));
				String digest = request.getParameter("digest");
				if (StringUtil.isEmpty(appId)) {
					throw new DOPException("请求非法：应用ID为空！");
				}
				if (StringUtil.isEmpty(appSecret)) {
					throw new DOPException("请求非法：应用密钥为空！");
				}
				System.out.println("appId:" + appId);
				System.out.println("appSecret:" + appSecret);
				System.out.println("data:" + data);
				System.out.println("请求方摘要：" + digest);
				System.out.println(
						"接收签名摘要： " + DigestUtils.md5Hex(appId + "&" + appSecret + "&" + Base64Util.encode(data)));
				if (!digest.equals(DigestUtils.md5Hex(appId + "&" + appSecret + "&" + Base64Util.encode(data)))) {
					throw new DOPException("请求非法：签名被篡改！");
				}

			}
		}
	}

	/**
	 * aop环绕
	 * 
	 * @param proceedingJoinPoint
	 * @return
	 */
	@Around("executeService()")
	public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		try {
			parameterVerify(proceedingJoinPoint);
			return proceedingJoinPoint.proceed();
		} catch (DOPException e) {
			return new JsonResult(-1, e.getMessage());
		} catch (Throwable e) {
			e.printStackTrace();
			return new JsonResult(-1, "未知错误！");
		}
	}
}
