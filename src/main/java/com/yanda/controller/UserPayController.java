package com.yanda.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.yanda.entity.JsonResult;
import com.yanda.service.UserPayService;
import com.yanda.util.StringUtil;

@RestController
@RequestMapping(value = "/userPay")
public class UserPayController extends BaseController {
	
	@Autowired
	private UserPayService userPayService;
	
	@GetMapping(value= "/notifyUrl") 
	public void getNotify() {
	}
	
	@PostMapping(value = "/getPaySign")
	public JsonResult getPaySign(HttpServletRequest request) {
		String str = getNotEmptyValue(request, "str");
		if (StringUtil.isEmpty(str)) {
			return result(-1, "传入字符串为空");
		}
		str = HtmlUtils.htmlUnescape(str);
		String md5Str = userPayService.getPaySign(str);
		return result(200, "success", md5Str);
	}

}
