package com.yanda.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yanda.entity.JsonResult;
import com.yanda.entity.PageResult;
import com.yanda.entity.UserHistoryDetailInfo;
import com.yanda.exception.DOPException;
import com.yanda.service.UserHistoryService;
import com.yanda.util.StringUtil;

@RestController
@RequestMapping(value = "/history")
public class UserHistoryController extends BaseController {

	@Autowired
	private UserHistoryService userHistoryService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public JsonResult findHistoriesByUserId(HttpServletRequest request) {
		String userId = getNotEmptyValue(request, "userId");
		if(StringUtil.isEmpty(userId)) {
			return result(-1, "用户id为空");
		}
		String pageNum = getValue(request, "pageNum", "1");
		String pageSize = getValue(request, "pageSize", "10");
		PageResult<UserHistoryDetailInfo> pageResult = userHistoryService.findUserHistoriesByUserId(
				Long.valueOf(userId), Integer.valueOf(pageNum), Integer.valueOf(pageSize));
		return result(200, "success", pageResult);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JsonResult deleteByHistoryId(HttpServletRequest request) {
		String historyId = getNotEmptyValue(request, "id");
		if(StringUtil.isEmpty(historyId)) {
			return result(-1, "历史记录编号为空");
		}
		try {
			userHistoryService.deleteByHistoryId(Long.valueOf(historyId));
			return result(200, "删除成功");
		} catch (NumberFormatException | DOPException e) {
			// TODO Auto-generated catch block
			return result(-1, "fail", e.getMessage());
		}
		
	}
}
