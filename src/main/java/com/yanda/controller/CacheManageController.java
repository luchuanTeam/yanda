package com.yanda.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yanda.entity.JsonResult;

@RequestMapping(value = "/cache")
@RestController
public class CacheManageController extends BaseController {
	
	@CacheEvict(value = "bannerList", allEntries=true, beforeInvocation=true)
	@RequestMapping(value = "/clearBanner")
	public JsonResult clearBanner(HttpServletRequest request) {
		return result(200, "清除轮播图缓存数据成功");
	}
	
	@CacheEvict(value = "movieList", allEntries=true, beforeInvocation=true)
	@RequestMapping(value = "/clearMovie")
	public JsonResult clearMovie(HttpServletRequest request) {
		return result(200, "清除视频缓存数据成功");
	}
	
	@CacheEvict(value = "episodeList", allEntries=true, beforeInvocation=true)
	@RequestMapping(value = "/clearEpisode")
	public JsonResult clearEpisode(HttpServletRequest request) {
		return result(200, "清除视频集缓存数据成功");
	}
	
	@CacheEvict(value = "classifyList", allEntries=true, beforeInvocation=true)
	@RequestMapping(value = "/clearClassify")
	public JsonResult clearClassify(HttpServletRequest request) {
		return result(200, "清除分类缓存数据成功");
	}
	
	@CacheEvict(value = "userList", allEntries=true, beforeInvocation=true)
	@RequestMapping(value = "/clearUser")
	public JsonResult clearUser(HttpServletRequest request) {
		return result(200, "清除用户缓存数据成功");
	}

}
