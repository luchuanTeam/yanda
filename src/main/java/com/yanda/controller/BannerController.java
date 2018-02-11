package com.yanda.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yanda.entity.JsonResult;
import com.yanda.entity.generated.BannerInfo;
import com.yanda.service.impl.BannerServiceImpl;
import com.yanda.util.StringUtil;

@RestController
@RequestMapping(value = "/banner")
public class BannerController extends BaseController {

	private static final Logger LOG = Logger.getLogger(BannerController.class);

	@Autowired
	private BannerServiceImpl bannerService;

	@Value("${web.tempUploadPath}")
	private String tempUploadPath;

	@Value("${web.domain}")
	private String domain;

	@Value("${web.uploadPath}")
	private String pubFilePath;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JsonResult listBanners(HttpServletRequest request) {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		if (StringUtil.isEmpty(pageNum)) {
			pageNum = "1";
		}
		if (StringUtil.isEmpty(pageSize)) {
			pageSize = "4";
		}
		List<BannerInfo> bannerInfos = bannerService.list(Integer.valueOf(pageNum), Integer.valueOf(pageSize));
		return result(200, "success", bannerInfos);
	}

	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonResult fileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		if (file.isEmpty())
			return result(-1, "上传失败，请选择要上传的图片!");
		if (!file.getContentType().contains("image"))
			return result(-1, "上传的文件不是图片类型，请重新上传!");
		// 获取图片的文件名
		String fileName = file.getOriginalFilename();
		try {
			// 获取图片的扩展名
			String extensionName = StringUtils.substringAfter(fileName, ".");
			// 新的图片文件名 = 获取时间戳+"."图片扩展名
			String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
			File dest = new File(tempUploadPath, newFileName);
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}
			// 上传到指定目录
			file.transferTo(dest);
			return result(200, "上传成功！", newFileName);
		} catch (IOException e) {
			LOG.error("拷贝图片【" + fileName + "】到临时路径异常", e);
			return result(-1, "上传失败!");
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JsonResult add(HttpServletRequest request) {

		String desc = request.getParameter("desc");
		String fileName = request.getParameter("imgName");

		File tempFile = new File(tempUploadPath, fileName);
		if (StringUtil.isEmpty(fileName) || !tempFile.exists()) {
			return result(-1, "找不到上传的图片");
		}
		
		File dest = new File(pubFilePath, fileName);

		try {
			Date crTime = new Date();
			String imgUrl = domain + "/images/" + fileName;
			FileUtils.copyFile(tempFile, dest);
			BannerInfo bannerInfo = new BannerInfo();
			bannerInfo.setImgDesc(desc);
			bannerInfo.setCreateTime(crTime);
			bannerInfo.setUpdateTime(crTime);
			bannerInfo.setImgUrl(imgUrl);
			bannerService.addRecord(bannerInfo);
			return result(200, "success");
		} catch (IOException e) {
			LOG.error("拷贝临时图片【" + fileName + "】到发布路径异常", e);
			return result(-1, "发布图片失败！");
		} finally {
			tempFile.delete();
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JsonResult delete(HttpServletRequest request) {
		String imgId = request.getParameter("imgId");
		int result = bannerService.deleteRecord(Integer.valueOf(imgId));
		if (result > 0) {
			return result(200, "删除成功!");
		} else {
			return result(-1, "删除失败！");
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonResult update(HttpServletRequest request) {
		String imgId = request.getParameter("imgId");
		String desc = request.getParameter("desc");
		BannerInfo bannerInfo = new BannerInfo();
		bannerInfo.setImgId(Integer.valueOf(imgId));
		bannerInfo.setImgDesc(desc);
		bannerInfo.setUpdateTime(new Date());
		int result = bannerService.updateRecord(bannerInfo);
		if (result > 0) {
			return result(200, "更新成功!");
		} else {
			return result(-1, "更新失败！");
		}
	}

}
