package com.yanda.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yanda.entity.JsonResult;
import com.yanda.entity.PageResult;
import com.yanda.entity.UserHistoryDetailInfo;
import com.yanda.entity.generated.UserHistoryInfo;
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
	
	@RequestMapping(value = "/upsert", method = RequestMethod.POST)
	public JsonResult upsertByUserIdAndEpisodeId(HttpServletRequest request) {
		String userId = getNotEmptyValue(request, "userId");
		String episodeId = getNotEmptyValue(request, "episodeId");
		if(StringUtil.isEmpty(userId) || StringUtil.isEmpty(episodeId)) {
			return result(-1, "必传参数缺少");
		}
		String progress = getValue(request, "progress", "0");
		UserHistoryInfo userHistoryInfo = new UserHistoryInfo();
		userHistoryInfo.setUserId(Long.valueOf(userId));
		userHistoryInfo.setEpisodeId(Long.valueOf(episodeId));
		userHistoryInfo.setProgress(Integer.valueOf(progress));
		userHistoryInfo.setWatchTime(new Date());
		try {
			userHistoryService.upsertUserHistoryInfo(userHistoryInfo);
			return result(200, "success");
		} catch (DOPException e) {
			e.printStackTrace();
			return result(-1, "fail");
		}
		
	}
	
	@RequestMapping(value = "/record", method = RequestMethod.POST)
	public JsonResult findByUserIdAndEpisodeId(HttpServletRequest request) {
		String userId = getNotEmptyValue(request, "userId");
		String episodeId = getNotEmptyValue(request, "episodeId");
		if(StringUtil.isEmpty(userId) || StringUtil.isEmpty(episodeId)) {
			return result(-1, "必传参数缺少");
		}
		UserHistoryInfo userHistoryInfo = userHistoryService.findByUserIdAndEpisodeId(Long.valueOf(userId),
				Long.valueOf(episodeId));
		if(userHistoryInfo == null) {
			return result(-1, "无历史记录");
		} else {
			return result(200, "success", userHistoryInfo);
		}
	}
}
