package com.yanda.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanda.component.FfmpegTool;
import com.yanda.component.FileConfig;
import com.yanda.entity.FileType;
import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.entity.generated.AttachmentInfoExample;
import com.yanda.entity.generated.EpisodeInfo;
import com.yanda.entity.generated.EpisodeInfoExample;
import com.yanda.exception.DOPException;
import com.yanda.mapper.generated.AttachmentInfoMapper;
import com.yanda.mapper.generated.EpisodeInfoMapper;
import com.yanda.service.AttachmentService;
import com.yanda.util.ImageUtils;


/**
 * 附件处理服务类
 * AttachmentServiceImpl.java
 * @author chenli
 * @time 2018年3月1日 下午5:18:24
 */
@Service
public class AttachmentServiceImpl extends BaseServiceImpl<AttachmentInfoMapper, AttachmentInfo, Long> implements AttachmentService {
	
	@Autowired
	private FfmpegTool ffTool;
	@Autowired
	private EpisodeInfoMapper episodeInfoMapper;
	
	public AttachmentServiceImpl() {
		super();
	}
	
	/**
	 * 缩略图尺寸Set
	 */
	public static final Set<Integer> iconSize = new LinkedHashSet<Integer>() {

		private static final long serialVersionUID = 2915123611231596820L;

		{
			add(800);
			add(500);
			add(200);
			add(80);
		}
	};
	
	@Autowired
	private FileConfig fileConfig;

	
	/**
	 * 先将临时文件拷贝到附件对应的实际目录，再保存实体记录
	 * 如果附件是图片要生成不同尺寸的缩略图
	 * @param record 附件实体
	 */
	@Transactional(rollbackFor={DOPException.class})
	@Override
	public int save(AttachmentInfo record) throws DOPException {
		String newFilename = record.getNewFilename();
		String fileExt = record.getFileExt();
		File tempFile = new File(fileConfig.getTempPath(), newFilename + "." + fileExt);
		setAttachmentDuration(record, tempFile);
			
		String filePath = record.getFilePath();
		File dest = new File(filePath, record.getNewFilename() + "." + record.getFileExt());
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			FileUtils.copyFile(tempFile, dest);
		} catch (IOException e) {
			LOG.error("拷贝临时文件到正式服务器失败" + e);
			throw new DOPException("拷贝临时文件到正式服务器失败");
		}
		// 图片类型附件要生成缩略图
		if (FileType.IMG.getValue() == record.getFileType()) {
			generatedThumbnail(tempFile, new File(filePath), newFilename, fileExt);
		}
		tempFile.delete();
		return this.mapper.insertSelective(record);
	}
	
	/**
	 * 先删除附件，再删除附件记录
	 * @param recordId 附件id
	 */
	@Transactional(rollbackFor={DOPException.class})
	@Override
	public int deleteById(Long recordId) throws DOPException {
		try {
			
			AttachmentInfo attachmentInfo = this.mapper.selectByPrimaryKey(recordId);
			if (null == attachmentInfo)
				return 0;
			int result = this.mapper.deleteByPrimaryKey(recordId);
			String filePath = attachmentInfo.getFilePath();
			// 若是删除视频附件则只删除对应的文件不删除整个文件夹
			if (attachmentInfo.getFileType() == FileType.VIDEO.getValue()) {
				filePath += "/" + attachmentInfo.getNewFilename() + "." +attachmentInfo.getFileExt();
			}
			FileUtils.forceDeleteOnExit(new File(filePath));
			return result;
			
		} catch (IOException e) {
			String tips = "删除附件异常";
			LOG.error(tips, e);
			throw new DOPException(tips);
		}
	}

	
	/**
	 * 根据附件类型生成不同上传目录
	 * @param fileType
	 * @return
	 * @throws IOException 
	 */
	@Override
	public String generatedFilePath(AttachmentInfo record) throws IOException {
		int fileType = record.getFileType();
		String filePath = fileConfig.getUploadPath();
		if (FileType.IMG.getValue() == fileType) {
			filePath += "/" + fileConfig.getBaseImgDir() + "/" + record.getNewFilename();
		} else if (FileType.VIDEO.getValue() == fileType) {
			filePath += "/" + fileConfig.getBaseVideoDir();
		} else {
			filePath += "/" + fileConfig.getBaseDir();
		}
		return filePath;
	}
	
	/**
	 * 生成不同尺寸的缩略图
	 * @param sFile
	 * @param folder
	 * @param fileName
	 * @throws DOPException 
	 * @throws IOException
	 */
	private void generatedThumbnail(File sFile, File folder, String fileName, String ext) throws DOPException {
		if (!folder.exists() || !folder.isDirectory()) {
			folder.mkdirs();
		}
		try {
			for (int size : iconSize) {
				File dfile = new File(folder, fileName + "_" + size + "." + ext);
				ImageUtils.zoom(sFile, dfile, size, size, ext);
			}
		} catch (Exception e) {
			LOG.error("生成缩略图异常" + e);
			throw new DOPException("生成缩略图异常");
		}
		
	}
	
	@Cacheable(value = "attach")
	@Override
	public AttachmentInfo selectById(Long id) throws DOPException {
		LOG.info("从数据库获取附件ID=["+id+"]的信息");
		return super.selectById(id);
	}
	
	/**
	 * 获取音视频附件时长
	 * @param attach
	 * @param file
	 */
	private void setAttachmentDuration(AttachmentInfo attach, File file) {
		if (attach.getFileType().intValue() != 20)
			return;
		attach.setDuration(ffTool.getMediaDuration(file)); 
	}

	@Override
	public void updateAllDuration() {
		AttachmentInfoExample example = new AttachmentInfoExample();
		example.createCriteria().andFileTypeEqualTo(20).andDurationIsNull();
		List<AttachmentInfo> attachs = this.mapper.selectByExample(example);
		for (AttachmentInfo attach : attachs) {
			File media = new File(attach.getFilePath() + "/" + attach.getNewFilename() + "." + attach.getFileExt());
			Long duration = ffTool.getMediaDuration(media);
			if (null == duration)
				continue;
			attach.setDuration(duration);
			EpisodeInfoExample example2 = new EpisodeInfoExample();
			example2.createCriteria().andMvAppendixIdEqualTo(attach.getAppendixId());
			List<EpisodeInfo> eInfos = episodeInfoMapper.selectByExample(example2);
			this.mapper.updateByPrimaryKeySelective(attach);
			for (EpisodeInfo episode : eInfos) {
				episode.setDuration(duration);
				episodeInfoMapper.updateByPrimaryKey(episode);
			}
		}
		
	}
}
