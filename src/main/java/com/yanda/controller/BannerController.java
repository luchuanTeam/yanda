package com.yanda.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yanda.entity.JsonResult;
import com.yanda.entity.PageResult;
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
		String pageNum = getValue(request, "pageNum", "1");
		String pageSize = getValue(request, "pageSize", "1");
		String imgDesc = getValue(request, "imgDesc");
		PageResult<BannerInfo> bannerInfos = bannerService.list(Integer.valueOf(pageNum), Integer.valueOf(pageSize), imgDesc);
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
	public JsonResult add(HttpServletRequest request, @RequestBody BannerInfo bannerInfo) {

		String fileName = bannerInfo.getImgUrl();
		if (StringUtil.isEmpty(fileName)) {
			return result(-1, "图片地址为空");
		}
		File tempFile = new File(tempUploadPath, fileName);
		if (StringUtil.isEmpty(fileName) || !tempFile.exists()) {
			return result(-1, "找不到上传的图片");
		}
		
		//String imgPubFilePath = 
		
		File dest = new File(pubFilePath, fileName);

		try {
			Date crTime = new Date();
			String imgUrl = domain + "/images/" + fileName;
			FileUtils.copyFile(tempFile, dest);
			bannerInfo.setCreateTime(crTime);
			bannerInfo.setUpdateTime(crTime);
			bannerInfo.setImgUrl(imgUrl);
			int imgId = bannerService.addRecord(bannerInfo);
			System.out.println(imgId);
			return result(200, "success");
		} catch (IOException e) {
			LOG.error("拷贝临时图片【" + fileName + "】到发布路径异常", e);
			return result(-1, "发布图片失败！");
		} finally {
			tempFile.delete();
		}
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public JsonResult delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		
		try {
			bannerService.deleteRecord(id);
			return result(200, "删除成功!");
		} catch (Exception e) {
			LOG.error("删除图片失败!", e);
			return result(-1, e.getMessage());
		}
	}
	
	@RequestMapping(value = "/batchDelete/{ids}", method = RequestMethod.POST)
	public JsonResult batchDelete(HttpServletRequest request, @PathVariable("ids") String ids) {
		try {
			int[] idArr =  StringUtil.stringToInts(ids);
			for (int id : idArr) {
				bannerService.deleteRecord(id);
			}
			return result(200, "删除成功!");
		} catch (Exception e) {
			LOG.error("删除图片失败!", e);
			return result(-1, e.getMessage());
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonResult update(HttpServletRequest request, @RequestBody BannerInfo bannerInfo) {
		request.getParameter("imgId");
		bannerInfo.setUpdateTime(new Date());
		int result = bannerService.updateRecord(bannerInfo);
		if (result > 0) {
			return result(200, "更新成功!");
		} else {
			return result(-1, "更新失败！");
		}
	}
	
	
}
