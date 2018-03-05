package com.yanda.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yanda.entity.JsonResult;
import com.yanda.entity.MovieDetailInfo;
import com.yanda.entity.PageResult;
import com.yanda.entity.WebClassifyInfo;
import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.entity.generated.MovieInfo;
import com.yanda.service.MovieService;

@RestController
@RequestMapping(value = "/movie")
public class MovieController extends BaseController {
	
	@Autowired
	private MovieService movieService;
	
	/**
	 * 获取包含视频分类路径的视频列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JsonResult listBanners(HttpServletRequest request) {
		String pageNum = getValue(request, "pageNum", "1");
		String pageSize = getValue(request, "pageSize", "1");
		String bannerDesc = getValue(request, "mvName");
		PageResult<MovieDetailInfo> mvDetailInfos = movieService.list(Integer.valueOf(pageNum), Integer.valueOf(pageSize),
				bannerDesc);
		return result(200, "success", mvDetailInfos);
	}
	
	/**
	 * 添加一条视频记录
	 * @param request
	 * @param bannerInfo
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JsonResult add(HttpServletRequest request, @RequestBody MovieInfo movieInfo) {

		try {
			AttachmentInfo attachmentInfo = handleImgAttach(request);
			movieInfo.setCreateTime(attachmentInfo.getCreateTime());
			movieInfo.setUpdateTime(attachmentInfo.getCreateTime());
			movieInfo.setEpisodeCount(0);
			movieService.addMovie(movieInfo, attachmentInfo);
			return result(200, "success");
		} catch (Exception e) {
			LOG.error("拷贝临时图片到发布路径异常", e);
			return result(-1, e.getMessage());
		} 
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public JsonResult delete(HttpServletRequest request, @PathVariable("id") Long id) {

		try {
			movieService.deleteById(id);
			return result(200, "删除成功!");
		} catch (Exception e) {
			LOG.error("删除视频失败!", e);
			return result(-1, e.getMessage());
		}
	}
	
	/**
	 * 获取一级分类
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getClassify")
	public List<WebClassifyInfo> getClassify(HttpServletRequest request) {
		return movieService.findOneLevelClassifyList();
	}
	
	/**
	 * 获取子分类
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getClassify/{id}")
	public List<WebClassifyInfo> getClassifyById(HttpServletRequest request, @PathVariable Integer id) {
		return movieService.findClassifyList(id);
	}
}
