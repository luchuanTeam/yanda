package com.yanda.component;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

@Component
public class MessageSender {
	
	private final static Logger LOG = Logger.getLogger(MessageSender.class);
	
	@Value("${tencent.appId}")
	private Integer appId;
	
	@Value("${tencent.appkey}")
	private String appKey; 
	
	@Value("${tencent.templateId}")
	private Integer templateId;
	
	@Value("${tencent.smsSign}")
	private String smsSign;
	
	/**
	 * 发送短信验证码
	 * @param userId 用户ID
	 * @param code   生成的验证码
	 * @param phone  手机号
	 * @return
	 */
	public boolean sendMessage(String code, String phone) {
		int successCode = -1;
		try {
		    String[] params = { code };
		    SmsSingleSender ssender = new SmsSingleSender(appId, appKey);
		    SmsSingleSenderResult result = ssender.sendWithParam("86", phone,
		    		templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
		    successCode = result.result;
		    LOG.info("向手机号为【"+phone+"】的用户发送验证码成功:" + result.toString());
		} catch (HTTPException e) {
			LOG.error("HTTP响应码错误" + e);
		} catch (JSONException e) {
		    LOG.error("json解析错误" + e);
		} catch (IOException e) {
			LOG.error("网络IO错误" + e);
		}
		return successCode > -1;
	}
	
}
