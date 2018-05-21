package com.yanda.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yanda.entity.EpisodeDetailInfo;
import com.yanda.entity.JsonResult;
import com.yanda.entity.PageResult;
import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.entity.generated.EpisodeInfo;
import com.yanda.entity.generated.MovieInfo;
import com.yanda.exception.DOPException;
import com.yanda.exception.NullParamException;
import com.yanda.service.EpisodeService;
import com.yanda.service.MovieService;
import com.yanda.util.StringUtil;

/**
 * 视频集相关接口控制类 特别说明： 视频里包含视频集 EpisodeController.java
 * 
 * @author chenli
 * @time 2018年3月7日 下午10:07:56
 */
@RestController
@RequestMapping(value = "/episode")
public class EpisodeController extends BaseController {
	/**
	 * 视频服务类
	 */
	@Autowired
	private MovieService movieService;
	/**
	 * 视频集服务类
	 */
	@Autowired
	private EpisodeService episodeService;

	/**
	 * 根据视频id获取视频集列表 ， 可根据视频集名称查询
	 * 
	 * @param request
	 *            请求体
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            分页大小
	 * @param episodeName
	 *            视频集名称
	 * @param mvId
	 *            视频id
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JsonResult listBanners(HttpServletRequest request) {
		String pageNum = getValue(request, "pageNum", "1");
		String pageSize = getValue(request, "pageSize", "1");
		String episodeName = getNotEmptyValue(request, "episodeName");
		String mvId = getNotEmptyValue(request, "mvId");
		if (StringUtil.isEmpty(mvId)) {
			return result(-1, "视频编号为空");
		}

		PageResult<EpisodeInfo> episodes = episodeService.list(Integer.valueOf(pageNum), Integer.valueOf(pageSize),
				Long.valueOf(mvId), episodeName);

		return result(200, "success", episodes);
	}

	/**
	 * 根据视频id向视频里添加一集视频
	 * 
	 * @param request
	 *            请求体
	 * @param episodeInfo
	 *            视频集
	 * @return
	 * @throws DOPException
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JsonResult add(HttpServletRequest request, @RequestBody EpisodeInfo episodeInfo) throws DOPException {

		Long mvId = episodeInfo.getMvId();
		if (mvId == null) {
			return result(-1, "主视频编号为空");
		}

		AttachmentInfo imgAttach = handleImgAttach(request);
		AttachmentInfo videoAttach = handleVideoAttach(request);

		MovieInfo movieInfo = movieService.selectById(mvId);
		movieInfo.setEpisodeCount(movieInfo.getEpisodeCount() + 1);
		if (StringUtil.isNotEmpty(movieInfo.getMvPath())) {
			videoAttach.setFilePath(movieInfo.getMvPath());
		} else {
			String filePath = fileConfig.getUploadPath() + "/" + fileConfig.getBaseVideoDir() + "/"
					+ videoAttach.getNewFilename();
			videoAttach.setFilePath(filePath);
			movieInfo.setMvPath(filePath);
		}

		episodeInfo.setCreateTime(videoAttach.getCreateTime());
		episodeInfo.setUpdateTime(videoAttach.getCreateTime());
		episodeService.addEpisode(imgAttach, videoAttach, movieInfo, episodeInfo);

		return result(200, "success");

	}

	/**
	 * 根据视频id删除一集视频
	 * 
	 * @param request
	 *            请求体
	 * @param episodeInfo
	 *            视频集 ID
	 * @return
	 * @throws DOPException
	 * @throws NullParamException
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public JsonResult delete(HttpServletRequest request, @PathVariable Long id)
			throws DOPException, NullParamException {

		if (id == null)
			throw new NullParamException("视频集编号");

		episodeService.deleteById(id);
		return result(200, "success");

	}

	/**
	 * 根据视频集ID获取视频集详细信息
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws DOPException
	 */
	@RequestMapping(value = "/getDetailEpisode/{id}", method = RequestMethod.GET)
	public EpisodeDetailInfo getEpisode(HttpServletRequest request, @PathVariable Long id) throws DOPException {
		return episodeService.findEpisodeDetailInfoById(id);
	}

	/**
	 * 根据视频集ID获取视频集
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws DOPException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public EpisodeInfo getEpisodeById(HttpServletRequest request, @PathVariable Long id) throws DOPException {
		return episodeService.selectById(id);
	}

	/**
	 * 更新一条视频集记录
	 * 
	 * @param request
	 *            请求体
	 * @param movieInfo
	 *            视频实体
	 * @return
	 * @throws DOPException
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonResult update(HttpServletRequest request, @RequestBody EpisodeInfo episodeInfo) throws DOPException {

		String oldFilename = request.getParameter("oldFilename");
		String newFilename = request.getParameter("newFilename");
		AttachmentInfo imgAttach = null;
		if (StringUtil.isNotEmpty(oldFilename) && StringUtil.isNotEmpty(newFilename)) {
			imgAttach = handleImgAttach(request);
		}

		String mvOldFilename = request.getParameter("mvOldFilename");
		String mvNewFilename = request.getParameter("mvNewFilename");
		AttachmentInfo mvAttach = null;
		if (StringUtil.isNotEmpty(mvOldFilename) && StringUtil.isNotEmpty(mvNewFilename)) {
			mvAttach = handleImgAttach(request);
		}

		episodeInfo.setUpdateTime(new Date());
		episodeService.updateEpisode(imgAttach, mvAttach, episodeInfo);
		return result(200, "success");

	}

	/**
	 * 获取视频集id、集数
	 * 
	 * @param request
	 * @param mvId
	 * @return
	 * @throws DOPException
	 */
	@RequestMapping(value = "/episodes/{mvId}", method = RequestMethod.GET)
	public List<EpisodeInfo> getEpisodesByMvId(HttpServletRequest request, @PathVariable Long mvId)
			throws DOPException {
		return episodeService.getSimpleEpisodeByMvId(mvId);
	}

}
