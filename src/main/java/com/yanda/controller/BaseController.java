package com.yanda.controller;

import com.yanda.entity.JsonResult;
import com.yanda.util.ActionResultUtil;

/**
 * 控制基础类
 * @author chenli
 *
 */
public class BaseController {
	
	public final static String PAGE_SIZE = ActionResultUtil.PAGE_SIZE;
	
	public final static String CUR_PAGE = ActionResultUtil.CUR_PAGE;
	
	public final static Integer PAGE_NUM_DEFAULT_VAL = ActionResultUtil.PAGE_NUM_DEFAULT_VAL;
	
	public final static Integer CUR_PAGE_DEFAULT_VAL = ActionResultUtil.CUR_PAGE_DEFAULT_VAL;
	
	public Object success() {
		return ActionResultUtil.success(null);
	}
	
	public Object success(Object data) {
		return ActionResultUtil.success(data);
	}
	
	public Object failed() {
		return ActionResultUtil.failed("");
	}
	
	public Object failed(Object data, String msg) {
		return ActionResultUtil.failed(data, msg);
	}
	
	public Object failed(String msg) {
		return ActionResultUtil.failed(msg);
	}
	
	public Object result(boolean result, String msg, Object data, Integer totalNum) {
		return ActionResultUtil.result(result, msg, data, totalNum);
	}
	
	public Object result(boolean result) {
		return ActionResultUtil.result(result);
	}
	
	/**
	 * 针对采购办推送接口定义的返回结果
	 * @param status
	 * @param message
	 * @return
	 */
	public JsonResult result(int status, String message) {
		return ActionResultUtil.result(status, message, null);
	}
	
	/**
	 * @param status
	 * @param message
	 * @data data
	 * @return
	 */
	public JsonResult result(int status, String message, Object data) {
		return ActionResultUtil.result(status, message, data);
	}
	
}
