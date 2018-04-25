/**
 * 
 */
package com.yanda.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yanda.component.FileConfig;
import com.yanda.entity.FileType;
import com.yanda.entity.JsonResult;
import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.exception.DOPException;
import com.yanda.service.impl.AttachmentServiceImpl;
import com.yanda.util.StringUtil;

/**
 * 附件控制类 AttachmentController.java
 * 
 * @author chenli
 * @time 2018年2月28日 下午9:02:42
 */
@Controller
@RequestMapping(value = "/attach")
public class AttachmentController extends BaseController {

	@Autowired
	private FileConfig fileConfig;
	@Autowired
	private AttachmentServiceImpl attachmentService;
	
	/**
	 * 图片上传
	 * @param request 请求
	 * @param file 上传的文件
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/doImgUpload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonResult doImgUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		if (file.isEmpty())
			return result(-1, "上传失败，请选择要上传的图片!");
		if (!file.getContentType().contains("image"))
			return result(-1, "上传的文件不是图片类型，请重新上传!");
		// 获取图片的文件名+后缀
		String fileName = file.getOriginalFilename();
		try {
			Map<String, String> resultMap = new HashMap<>();
			// 获取图片的扩展名
			String fileExt = StringUtils.substringAfter(fileName, ".");
			// 获取新图片的名字
			String newFileName = String.valueOf(System.currentTimeMillis()) + "." + fileExt;

			File dest = new File(fileConfig.getTempPath(), newFileName);
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}
			// 上传到指定目录
			file.transferTo(dest);
			resultMap.put("filename", fileName);
			resultMap.put("newFilename", newFileName);
			return result(200, "上传成功！", resultMap);
		} catch (IOException e) {
			String tips = "拷贝图片【" + fileName + "】到临时路径异常";
			LOG.error(tips, e);
			return result(-1, tips);
		}
	}
	
	/**
	 * 视频上传
	 * @param request 请求体
	 * @param file 上传的文件
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/doVideoUpload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonResult doVideoUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		if (file.isEmpty())
			return result(-1, "上传失败，请选择要上传的视频!");
		if (!file.getContentType().contains("video"))
			return result(-1, "上传的文件不是视频类型，请重新上传!");
		// 获取图片的文件名+后缀
		String fileName = file.getOriginalFilename();
		try {
			Map<String, String> resultMap = new HashMap<>();
			// 获取图片的扩展名
			String fileExt = StringUtils.substringAfter(fileName, ".");
			// 获取新图片的名字
			String newFileName = String.valueOf(System.currentTimeMillis()) + "." + fileExt;

			File dest = new File(fileConfig.getTempPath(), newFileName);
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}
			// 上传到指定目录
			file.transferTo(dest);
			resultMap.put("filename", fileName);
			resultMap.put("newFilename", newFileName);
			return result(200, "上传成功！", resultMap);
		} catch (IOException e) {
			String tips = "拷贝图片【" + fileName + "】到临时路径异常";
			LOG.error(tips, e);
			return result(-1, tips);
		}
	}
	
	/**
	 * 读取附件
	 * @param request 请求体
	 * @param response 响应体
	 */
	@RequestMapping(value = "/readFile", method = RequestMethod.GET)
	public void readFile(HttpServletRequest request, HttpServletResponse response) {
		String id = getNotEmptyValue(request, "id");
		String size = getNotEmptyValue(request, "size");

		if (StringUtil.isEmpty(id)) {
			return;
		}

		AttachmentInfo attach = null;
		try {
			attach = attachmentService.selectById(Long.valueOf(id));
			initResponseType(response, attach);
		} catch (NumberFormatException e) {
			LOG.error("数据类型转换失败：传入的附件ID=["+id+"]不是number类型", e);
		} catch (DOPException e) {
			LOG.error(e.getMessage());
		}
		if (null == attach) {
			return;
		}
		
		String fileName = attach.getNewFilename();
		if (FileType.IMG.getValue() == attach.getFileType()
				&& AttachmentServiceImpl.iconSize.contains(Integer.valueOf(size))) {
			fileName += "_" + size + "." + attach.getFileExt();
		} else {
			fileName += "." + attach.getFileExt();
		}
		
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			bis = new BufferedInputStream(
					new FileInputStream(new File(attach.getFilePath() + "/" +  fileName)));
			int length = bis.read(buff);
			while (length != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				length = bis.read(buff);
			}
		} catch (IOException e) {
			LOG.error("下载附件异常:" + e.getMessage());
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					LOG.error("关闭输入流异常", e);
				}
			}
		}
	}
	
	
	/**
	 * 根据附件的类型初始化响应体，若是图片则显示图片，若是其他文件则下载
	 * @param response
	 * @param attach
	 */
	private void initResponseType(HttpServletResponse response, AttachmentInfo attach) {
		String fileExt = attach.getFileExt();
		String fileShowName = attach.getOldFilename() + "." + fileExt;
		if ("jpg".equalsIgnoreCase(fileExt) || "jpeg".equalsIgnoreCase(fileExt)) {
			response.setContentType("image/jpeg");
		} else if ("png".equalsIgnoreCase(fileExt)) {
			response.setContentType("image/png");
		} else if ("gif".equalsIgnoreCase(fileExt)) {
			response.setContentType("image/gif");
		} else {
			response.setHeader("content-type", "application/octet-stream");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileShowName);
		}
		
	}
}
