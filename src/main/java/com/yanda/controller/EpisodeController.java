package com.yanda.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yanda.entity.JsonResult;
import com.yanda.entity.PageResult;
import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.entity.generated.EpisodeInfo;
import com.yanda.entity.generated.MovieInfo;
import com.yanda.service.EpisodeService;
import com.yanda.service.MovieService;
import com.yanda.util.StringUtil;

@RestController
@RequestMapping(value = "/episode")
public class EpisodeController extends BaseController {

	@Autowired
	private MovieService movieService;
	@Autowired
	private EpisodeService episodeService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JsonResult listBanners(HttpServletRequest request) {
		String pageNum = getValue(request, "pageNum", "1");
		String pageSize = getValue(request, "pageSize", "1");
		String episodeName = getValue(request, "episodeName");
		String mvId = getValue(request, "mvId");
		if (StringUtil.isEmpty(mvId)) {
			return result(-1, "视频编号为空");
		}

		PageResult<EpisodeInfo> episodes = episodeService.list(Integer.valueOf(pageNum), Integer.valueOf(pageSize),
				Long.valueOf(mvId), episodeName);

		return result(200, "success", episodes);
	}

	/**
	 * 添加一集视频
	 * 
	 * @param request
	 * @param bannerInfo
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JsonResult add(HttpServletRequest request, @RequestBody EpisodeInfo episodeInfo) {

		Long mvId = episodeInfo.getMvId();
		if (mvId == null) {
			return result(-1, "主视频编号为空");
		}

		try {
			AttachmentInfo imgAttach = handleImgAttach(request);
			AttachmentInfo videoAttach = handleVideoAttach(request);

			MovieInfo movieInfo = movieService.selectById(mvId);
			movieInfo.setEpisodeCount(movieInfo.getEpisodeCount()+1);
			if (StringUtil.isNotEmpty(movieInfo.getMvPath())) {
				videoAttach.setFilePath(movieInfo.getMvPath());
			} else {
				String filePath = fileConfig.getUploadPath() + "/" + fileConfig.getBaseVideoDir() + "/" + videoAttach.getNewFilename();
				videoAttach.setFilePath(filePath);
				movieInfo.setMvPath(filePath);
			}

			episodeInfo.setCreateTime(videoAttach.getCreateTime());
			episodeInfo.setUpdateTime(videoAttach.getCreateTime());
			episodeService.addEpisode(imgAttach, videoAttach, movieInfo, episodeInfo);
			
			return result(200, "success");
		} catch (Exception e) {
			LOG.error("添加视频集异常", e);
			return result(-1, e.getMessage());
		}
	}

}
