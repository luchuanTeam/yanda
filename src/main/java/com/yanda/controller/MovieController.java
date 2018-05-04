package com.yanda.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yanda.entity.JsonResult;
import com.yanda.entity.MovieDetailInfo;
import com.yanda.entity.PageResult;
import com.yanda.entity.WebClassifyInfo;
import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.entity.generated.ClassifyInfo;
import com.yanda.entity.generated.MovieInfo;
import com.yanda.entity.generated.UserSearchInfo;
import com.yanda.exception.DOPException;
import com.yanda.service.MovieService;
import com.yanda.service.UserSearchService;
import com.yanda.util.SortUtil;
import com.yanda.util.StringUtil;

/**
 * 视频相关接口控制类 MovieController.java
 * 
 * @author chenli
 * @time 2018年3月7日 下午10:23:34
 */
@RestController
@RequestMapping(value = "/movie")
public class MovieController extends BaseController {

	@Autowired
	private MovieService movieService;
	@Autowired
	private UserSearchService userSearchService;

	/**
	 * 获取包含视频分类路径的视频列表，可根据视频名称查询
	 * 
	 * @param request
	 *            请求体
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            分页大小
	 * @param mvName
	 *            视频名称
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JsonResult listMovies(HttpServletRequest request) {
		String pageNum = getValue(request, "pageNum", "1");
		String pageSize = getValue(request, "pageSize", "4");
		String mvName = getNotEmptyValue(request, "mvName");
		PageResult<MovieDetailInfo> mvDetailInfos = movieService.list(Integer.valueOf(pageNum),
				Integer.valueOf(pageSize), mvName);
		return result(200, "success", mvDetailInfos);
	}

	/**
	 * 添加一条视频记录
	 * 
	 * @param request
	 *            请求体
	 * @param movieInfo
	 *            视频实体
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

	/**
	 * 更新一条视频记录
	 * 
	 * @param request
	 *            请求体
	 * @param movieInfo
	 *            视频实体
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonResult update(HttpServletRequest request, @RequestBody MovieInfo movieInfo) {

		try {
			String oldFilename = request.getParameter("oldFilename");
			String newFilename = request.getParameter("newFilename");
			AttachmentInfo attachmentInfo = null;
			if (StringUtil.isNotEmpty(oldFilename) && StringUtil.isNotEmpty(newFilename)) {
				attachmentInfo = handleImgAttach(request);
			}
			movieInfo.setUpdateTime(new Date());
			movieService.updateMovie(movieInfo, attachmentInfo);
			return result(200, "success");
		} catch (Exception e) {
			LOG.error("拷贝临时图片到发布路径异常", e);
			return result(-1, e.getMessage());
		}
	}

	/**
	 * 删除一条视频 注意：删除的视频下不能包含有视频集
	 * 
	 * @param request
	 *            请求体
	 * @param id
	 *            视频id
	 * @return
	 */
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
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getClassify")
	public List<WebClassifyInfo> getClassify(HttpServletRequest request) {
		return movieService.findOneLevelClassifyList();
	}

	/**
	 * 根据一级分类id获取二级分类
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getClassify/{id}")
	public List<WebClassifyInfo> getClassifyById(HttpServletRequest request, @PathVariable Integer id) {
		return movieService.findClassifyList(id);
	}

	/**
	 * 根据视频ID获取视频
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws DOPException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public MovieInfo getMovie(HttpServletRequest request, @PathVariable Long id) throws DOPException {
		return movieService.selectById(id);
	}

	/**
	 * 获取包含视频分类路径的视频列表，可根据视频名称查询
	 * 
	 * @param request
	 *            请求体
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            分页大小
	 * @param mvName
	 *            视频名称
	 * @return
	 */
	@RequestMapping(value = "/getPubMovies", method = RequestMethod.GET)
	public JsonResult getMovies(HttpServletRequest request) {
		String pageNum = getValue(request, "pageNum", "1");
		String pageSize = getValue(request, "pageSize", "4");
		String classifyId = getValue(request, "classifyId", "0");
		PageResult<MovieDetailInfo> mvDetailInfos = movieService.getPubMovies(Integer.valueOf(classifyId),
				Integer.valueOf(pageNum), Integer.valueOf(pageSize));
		return result(200, "success", mvDetailInfos);
	}

	/**
	 * 根据视频名称或简介搜索视频
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws DOPException
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public JsonResult searchMovie(Integer classifyId, String keyword, Integer userId,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size, String sort, String order)
			throws DOPException {
		
		String sortWithOrder = SortUtil.mvSort(sort, order);

        //添加到搜索历史
        if (userId != null && StringUtil.isNotEmpty(keyword)) {
            UserSearchInfo searchHistoryVo = new UserSearchInfo();
            searchHistoryVo.setAddTime(new Date());
            searchHistoryVo.setKeyword(keyword);
            searchHistoryVo.setUserId(userId);
            userSearchService.save(searchHistoryVo);
        }

        //查询列表数据
        PageResult<MovieInfo> pageResult = movieService.querySelective(classifyId, keyword, page, size, sortWithOrder);

        // 查询商品所属类目列表。
        List<Integer> classifyIds = movieService.getClassifyIds(keyword);
        List<ClassifyInfo> classifyList = null;
        if(classifyIds.size() != 0) {
            classifyList = movieService.findClassifyListByIds(classifyIds);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("mvList", pageResult.getList());
        data.put("filterclassifyList", classifyList);
        data.put("count", pageResult.getTotal());
		
		return result(200, "success", data);
	}

	/**
	 * 发布或撤销发布视频
	 * 
	 * @param request
	 * @param mvId
	 * @param isPublic
	 * @return
	 * @throws DOPException
	 */
	@CacheEvict(value = "movieList", allEntries = true, beforeInvocation = true)
	@RequestMapping(value = "/pub", method = RequestMethod.POST)
	public JsonResult pubMovie(HttpServletRequest request) throws DOPException {
		String mvId = getNotEmptyValue(request, "mvId");
		if (StringUtil.isEmpty(mvId))
			return result(-1, "视频编号为空");
		String isPublic = getNotEmptyValue(request, "isPublic");
		if (StringUtil.isEmpty(mvId))
			return result(-1, "发布状态值为空");

		try {
			Long id = Long.valueOf(mvId);
			int ispublicVal = Integer.valueOf(isPublic).intValue();
			MovieInfo movieInfo = new MovieInfo();
			movieInfo.setMvId(id);
			movieInfo.setIsPublic(ispublicVal == 1);
			if (ispublicVal == 1) {
				movieInfo.setPublicTime(new Date());
			}
			movieService.update(movieInfo);

		} catch (Exception e) {
			LOG.error(e);
			return result(-1, "更新发布状态失败");
		}

		return result(200, "success");
	}
}
