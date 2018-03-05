package com.yanda.controller;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.yanda.component.FileConfig;
import com.yanda.entity.FileType;
import com.yanda.entity.JsonResult;
import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.exception.DOPException;
import com.yanda.util.ActionResultUtil;
import com.yanda.util.StringUtil;

/**
 * 控制基础类
 * @author chenli
 *
 */
public class BaseController {
	
	protected Logger LOG = Logger.getLogger(this.getClass());
	
	@Autowired
	protected FileConfig fileConfig;
	
	public final static String PAGE_SIZE = ActionResultUtil.PAGE_SIZE;
	
	public final static String CUR_PAGE = ActionResultUtil.CUR_PAGE;
	
	public final static Integer PAGE_NUM_DEFAULT_VAL = ActionResultUtil.PAGE_NUM_DEFAULT_VAL;
	
	public final static Integer CUR_PAGE_DEFAULT_VAL = ActionResultUtil.CUR_PAGE_DEFAULT_VAL;
	
	public Object success() {
		return ActionResultUtil.success(null);
	}
	
	public Object success(Object data) {
		return ActionResultUtil.success(data);
	}
	
	public Object failed() {
		return ActionResultUtil.failed("");
	}
	
	public Object failed(Object data, String msg) {
		return ActionResultUtil.failed(data, msg);
	}
	
	public Object failed(String msg) {
		return ActionResultUtil.failed(msg);
	}
	
	public Object result(boolean result, String msg, Object data, Integer totalNum) {
		return ActionResultUtil.result(result, msg, data, totalNum);
	}
	
	public Object result(boolean result) {
		return ActionResultUtil.result(result);
	}
	
	/**
	 * 针对采购办推送接口定义的返回结果
	 * @param status
	 * @param message
	 * @return
	 */
	public JsonResult result(int status, String message) {
		return ActionResultUtil.result(status, message, null);
	}
	
	/**
	 * @param status
	 * @param message
	 * @data data
	 * @return
	 */
	public JsonResult result(int status, String message, Object data) {
		return ActionResultUtil.result(status, message, data);
	}
	
	public String getValue(HttpServletRequest request, String name, String defaultVal) {
		String value = request.getParameter(name);
		if (StringUtil.isEmpty(value))
			return defaultVal;
		return value;
	}
		
	public String getValue(HttpServletRequest request, String name) {
		return getValue(request, name, "");
	}
	
	/**
	 * 处理上传到临时目录的图片
	 * @param request
	 * @return
	 * @throws DOPException
	 */
	public AttachmentInfo handleImgAttach(HttpServletRequest request) throws DOPException {
		String oldFilename = request.getParameter("oldFilename");
		String newFilename = request.getParameter("newFilename");
		
		if (StringUtil.isEmpty(newFilename)) {
			throw new DOPException("图片地址为空");
		}
		
		File tempFile = new File(fileConfig.getTempPath(), newFilename);
		if (StringUtil.isEmpty(newFilename) || !tempFile.exists()) {
			throw new DOPException("找不到上传的图片");
		}
		
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
		return attachmentInfo;
	}
	
	/**
	 * 处理上传到临时目录的视频
	 * 注意： 视频路径需要在具体业务里设置
	 * @param request
	 * @return
	 * @throws DOPException 
	 */
	public AttachmentInfo handleVideoAttach(HttpServletRequest request) throws DOPException {
		String mvOldFilename = request.getParameter("mvOldFilename");
		String mvNewFilename = request.getParameter("mvNewFilename");
		if (StringUtil.isEmpty(mvNewFilename)) {
			throw new DOPException("图片地址为空");
		}
		
		File tempFile = new File(fileConfig.getTempPath(), mvNewFilename);
		if (StringUtil.isEmpty(mvNewFilename) || !tempFile.exists()) {
			throw new DOPException("找不到上传的图片");
		}
		
		Date crTime = new Date();
		// 获取图片的扩展名
		String ext = StringUtils.substringAfter(mvNewFilename, ".");
		// 获取图片名
		String filename = StringUtils.substringBefore(mvNewFilename, ".");

		AttachmentInfo attachmentInfo = new AttachmentInfo();
		attachmentInfo.setOldFilename(StringUtils.substringBefore(mvOldFilename, "."));
		attachmentInfo.setNewFilename(filename);
		attachmentInfo.setCreateTime(crTime);
		attachmentInfo.setFileExt(ext);
		attachmentInfo.setFileType(FileType.VIDEO.getValue());
		return attachmentInfo;
	}
}
