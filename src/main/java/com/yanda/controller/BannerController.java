package com.yanda.controller;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yanda.component.FileConfig;
import com.yanda.entity.FileType;
import com.yanda.entity.JsonResult;
import com.yanda.entity.PageResult;
import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.entity.generated.BannerInfo;
import com.yanda.exception.DOPException;
import com.yanda.service.impl.BannerServiceImpl;
import com.yanda.util.StringUtil;

@RestController
@RequestMapping(value = "/banner")
public class BannerController extends BaseController {

	@Autowired
	private BannerServiceImpl bannerService;
	@Autowired
	private FileConfig fileConfig;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JsonResult listBanners(HttpServletRequest request) {
		String pageNum = getValue(request, "pageNum", "1");
		String pageSize = getValue(request, "pageSize", "1");
		String bannerDesc = getValue(request, "bannerDesc");
		PageResult<BannerInfo> bannerInfos = bannerService.list(Integer.valueOf(pageNum), Integer.valueOf(pageSize),
				bannerDesc);
		return result(200, "success", bannerInfos);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JsonResult add(HttpServletRequest request, @RequestBody BannerInfo bannerInfo) {

		String oldFilename = request.getParameter("oldFilename");
		String newFilename = request.getParameter("newFilename");
		if (StringUtil.isEmpty(newFilename)) {
			return result(-1, "图片地址为空");
		}
		File tempFile = new File(fileConfig.getTempPath(), newFilename);
		if (StringUtil.isEmpty(newFilename) || !tempFile.exists()) {
			return result(-1, "找不到上传的图片");
		}

		try {
			Date crTime = new Date();
			// 获取图片的扩展名
			String ext = StringUtils.substringAfter(newFilename, ".");
			// 获取图片名
			String filename = StringUtils.substringBefore(newFilename, ".");

			AttachmentInfo attachmentInfo = new AttachmentInfo();
			attachmentInfo.setOldFilename(StringUtils.substringBefore(oldFilename, "."));
			attachmentInfo.setNewFilename(filename);
			attachmentInfo.setCreateTime(crTime);
			attachmentInfo.setFileExt(ext);
			attachmentInfo.setFilePath(fileConfig.getUploadPath() + "/" + fileConfig.getBaseImgDir() + "/" + filename);
			attachmentInfo.setFileType(FileType.IMG.getValue());

			bannerInfo.setCreateTime(crTime);
			bannerInfo.setUpdateTime(crTime);
			bannerService.addBanner(attachmentInfo, bannerInfo);
			return result(200, "success");
		} catch (Exception e) {
			LOG.error("拷贝临时图片【" + newFilename + "】到发布路径异常", e);
			return result(-1, "发布图片失败！");
		} finally {
			tempFile.delete();
		}
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public JsonResult delete(HttpServletRequest request, @PathVariable("id") Long id) {

		try {
			bannerService.deleteById(id);
			return result(200, "删除成功!");
		} catch (Exception e) {
			LOG.error("删除图片失败!", e);
			return result(-1, e.getMessage());
		}
	}

	@RequestMapping(value = "/batchDelete/{ids}", method = RequestMethod.POST)
	public JsonResult batchDelete(HttpServletRequest request, @PathVariable("ids") String ids) {
		try {
			long[] idArr = StringUtil.stringToLongs(ids);
			for (long id : idArr) {
				bannerService.deleteById(id);
			}
			return result(200, "删除成功!");
		} catch (Exception e) {
			LOG.error("删除图片失败!", e);
			return result(-1, e.getMessage());
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonResult update(HttpServletRequest request, @RequestBody BannerInfo bannerInfo) {
		bannerInfo.setUpdateTime(new Date());
		try {
			bannerService.update(bannerInfo);
			return result(200, "更新成功!");
		} catch (DOPException e) {
			return result(-1, "更新失败:" + e.getMessage());
		}
	}

}
