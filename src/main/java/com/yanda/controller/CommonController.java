package com.yanda.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yanda.component.FileConfig;
import com.yanda.entity.JsonResult;

@RestController
@RequestMapping(value = "/common")
public class CommonController extends BaseController {
	
	@Autowired
	private FileConfig fileConfig;
	
	@RequestMapping(value = "/imgUpload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
			File dest = new File(fileConfig.getTempPath(), fileName);
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}
			// 上传到指定目录
			file.transferTo(dest);
			return result(200, "上传成功！", fileName);
		} catch (IOException e) {
			LOG.error("拷贝图片【" + fileName + "】到临时路径异常", e);
			return result(-1, "上传失败!");
		}
	}
	
}
