package com.yanda.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 默认入口控制类，用于返回请求的页面
 * IndexController.java
 * @author chenli
 * @time 2018年3月7日 下午10:18:10
 */
@Controller
public class IndexController extends BaseController {
	
	@RequestMapping(value = "/")
	public String index(HttpServletRequest request) {
		return "index";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request) {
		return "delete";
	}
	
	@RequestMapping(value = "/403")
	public String error403(HttpServletRequest request) {
		return "403";
	}
	
}
