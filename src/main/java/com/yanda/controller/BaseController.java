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
	/**
	 * 日志记录，继承BaseController的控制类可直接调用
	 */
	protected Logger LOG = Logger.getLogger(this.getClass());
	
	@Autowired
	protected FileConfig fileConfig;
	
	/**
	 * 分页大小
	 */
	public final static String PAGE_SIZE = ActionResultUtil.PAGE_SIZE;
	
	/**
	 * 当前页数
	 */
	public final static String CUR_PAGE = ActionResultUtil.CUR_PAGE;
	
	/**
	 * 默认分页大小
	 */
	public final static Integer PAGE_NUM_DEFAULT_VAL = ActionResultUtil.PAGE_NUM_DEFAULT_VAL;
	
	/**
	 * 默认当前页数
	 */
	public final static Integer CUR_PAGE_DEFAULT_VAL = ActionResultUtil.CUR_PAGE_DEFAULT_VAL;

	/**
	 * 接口数据返回
	 * @param status  请求响应码   -1-失败 200-成功
	 * @param message 响应消息结果
	 * @return
	 */
	public JsonResult result(int status, String message) {
		return this.result(status, message, null);
	}
	
	/**
	 * 接口数据返回
	 * @param status  请求响应码   -1-失败 200-成功
	 * @param message 响应消息结果
	 * @data data 响应数据
	 * @return
	 */
	public JsonResult result(int status, String message, Object data) {
		return ActionResultUtil.result(status, message, data);
	}
	
	/**
	 * 从请求体中获取指定参数值
	 * @param request 请求体
	 * @param name 参数名
	 * @param defaultVal 参数值为空时的默认值
	 * @return
	 */
	public String getValue(HttpServletRequest request, String name, String defaultVal) {
		String value = request.getParameter(name);
		if (StringUtil.isEmpty(value))
			return defaultVal;
		return value;
	}
	
	/**
	 * 从请求体中获取指定参数值，若为null则返回空字符串
	 * @param request 请求体
	 * @param name 参数名
	 * @return
	 */
	public String getNotEmptyValue(HttpServletRequest request, String name) {
		return getValue(request, name, "");
	}
	
	/**
	 * 处理上传到临时目录的图片
	 * @param request
	 * @return 返回附件实体
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
		String ext = StringUtils.substringAfterLast(newFilename, ".");
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
	 * @return 返回附件实体
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
		String ext = StringUtils.substringAfterLast(mvNewFilename, ".");
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
	
	/**
	 * 处理上传到临时目录的附件
	 * @param request
	 * @return 返回附件实体
	 * @throws DOPException
	 */
	public AttachmentInfo handleAttach(HttpServletRequest request) throws DOPException {
		String oldFilename = request.getParameter("oldFilename");
		String newFilename = request.getParameter("newFilename");
		
		if (StringUtil.isEmpty(newFilename)) {
			throw new DOPException("附件地址为空");
		}
		
		File tempFile = new File(fileConfig.getTempPath(), newFilename);
		if (StringUtil.isEmpty(newFilename) || !tempFile.exists()) {
			throw new DOPException("找不到上传的附件");
		}
		
		Date crTime = new Date();
		// 获取附件的扩展名
		String ext = StringUtils.substringAfterLast(newFilename, ".");
		// 获取附件名
		String filename = StringUtils.substringBefore(newFilename, ".");

		AttachmentInfo attachmentInfo = new AttachmentInfo();
		attachmentInfo.setOldFilename(StringUtils.substringBefore(oldFilename, "."));
		attachmentInfo.setNewFilename(filename);
		attachmentInfo.setCreateTime(crTime);
		attachmentInfo.setFileExt(ext);
		attachmentInfo.setFilePath(fileConfig.getUploadPath() + "/" + fileConfig.getBaseDir() + "/" + filename);
		attachmentInfo.setFileType(FileType.ATTACH.getValue());
		return attachmentInfo;
	}
	
	public String getAttachRelativePath(AttachmentInfo record) {
		int fileType = record.getFileType();
		String filePath = "";
		if (FileType.IMG.getValue() == fileType) {
			filePath += "/" + fileConfig.getBaseImgDir() + "/" + record.getNewFilename() + "/" + record.getNewFilename() + "." + record.getFileExt();
		} else if (FileType.VIDEO.getValue() == fileType) {
			filePath += "/" + fileConfig.getBaseVideoDir() + "/" + record.getNewFilename() + "/" + record.getNewFilename() + "." + record.getFileExt();
		} else {
			filePath += "/" + fileConfig.getBaseDir() + "/" + record.getNewFilename() + "/"  + record.getNewFilename() + "." + record.getFileExt();
		}
		return filePath;
	}
}
