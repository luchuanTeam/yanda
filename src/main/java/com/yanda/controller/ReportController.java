package com.yanda.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yanda.entity.ReportInfo;
import com.yanda.service.ReportService;

/**
 * 统计报表相关接口控制器
 * ReportController.java
 * @author chenli
 * @time 2018年4月28日 上午11:22:08
 */
@RestController
@RequestMapping(value = "/report")
public class ReportController extends BaseController {
	
	@Autowired
	private ReportService reportService;
	
	/**
	 * 获取视频分类统计
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/classifyMvCount", method = RequestMethod.GET)
	public ReportInfo classifyMvCount(HttpServletRequest request) {
		return reportService.getClassifyMvCount();
	}
	
}
