package com.yanda.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.yanda.entity.JsonResult;
import com.yanda.entity.PageResult;
import com.yanda.entity.generated.PayInfo;
import com.yanda.exception.DOPException;
import com.yanda.service.PayService;
import com.yanda.util.StringUtil;

@RestController
@RequestMapping(value = "/pay")
public class PayController extends BaseController {
	
	@Autowired
	private PayService payService;
	
	@RequestMapping(value = "/recordList", method = RequestMethod.GET)
	public JsonResult getPayRecordsByUserId(HttpServletRequest request) {
		String userId = getNotEmptyValue(request, "userId");
		if(StringUtil.isEmpty(userId)) 
			return result(-1, "userId不能为空");
		String pageNum = getValue(request, "pageNum", "1");
		String pageSize = getValue(request, "pageSize", "10");
		PageResult<PayInfo> result = payService.list(Integer.valueOf(pageNum), Integer.valueOf(pageSize), Integer.valueOf(userId));
		return result(200, "success", result);
	}
	
	@RequestMapping(value = "/addRecord", method = RequestMethod.POST)
	public JsonResult addRecordByUserId(HttpServletRequest request) {
		String userId = getNotEmptyValue(request, "userId");
		if(StringUtil.isEmpty(userId))
			return result(-1, "userId 不能为空");
		String payStatus = getNotEmptyValue(request, "payStatus");
		if(StringUtil.isEmpty(payStatus)) 
			return result(-1, "支付状态不能为空");
		String payAmout = getValue(request, "payAmount", "0.01");
		String payMsg = getValue(request, "payMsg", "");
		String tradeNo = getValue(request, "tradeNo", "");
		PayInfo payInfo = new PayInfo();
		payInfo.setUserId(Integer.valueOf(userId));
		payInfo.setPayAmount(Float.valueOf(payAmout));
		payInfo.setPayStatus(Integer.valueOf(payStatus));
		payInfo.setPayTime(new Date());
		payInfo.setTradeNo(tradeNo);
		payInfo.setPayMsg(payMsg);
		try {
			payService.save(payInfo);
			return result(200, "success");
		} catch (DOPException e) {
			e.printStackTrace();
			return result(-1, "保存失败");
		}
	}
	
	@PostMapping(value = "/getPaySign")
	public JsonResult getPaySign(HttpServletRequest request) {
		String str = getNotEmptyValue(request, "str");
		if (StringUtil.isEmpty(str)) {
			return result(-1, "传入字符串为空");
		}
		str = HtmlUtils.htmlUnescape(str);
		String md5Str = payService.getPaySign(str);
		return result(200, "success", md5Str);
	}
	
	@GetMapping(value= "/notifyUrl") 
	public void getNotify() {}

}
