package com.yanda.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanda.component.FileConfig;
import com.yanda.entity.FileType;
import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.exception.DOPException;
import com.yanda.mapper.generated.AttachmentInfoMapper;
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
		try {
			
			String filePath = record.getFilePath();
			File dest = new File(filePath, record.getNewFilename() + "." + record.getFileExt());
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}
			FileUtils.copyFile(tempFile, dest);
			// 图片类型附件要生成缩略图
			if (FileType.IMG.getValue() == record.getFileType()) {
				generatedThumbnail(tempFile, new File(filePath), newFilename, fileExt);
			}
			tempFile.delete();
			return this.mapper.insertSelective(record);
			
		} catch (IOException e) {
			String tips = "添加附件失败：生成缩略图异常";
			LOG.error(tips, e);
			throw new DOPException(tips);
		} 
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
			int result = this.mapper.deleteByPrimaryKey(recordId);
			String filePath = attachmentInfo.getFilePath();
			// 若是删除视频附件则只删除对应的文件不删除整个文件夹
			if (attachmentInfo.getFileType() == FileType.VIDEO.getValue()) {
				filePath += "/" + attachmentInfo.getNewFilename() + "." +attachmentInfo.getFileExt();
			}
			FileUtils.deleteDirectory(new File(filePath));
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
	 * @throws IOException
	 */
	private void generatedThumbnail(File sFile, File folder, String fileName, String ext) throws IOException {
		if (!folder.exists() || !folder.isDirectory()) {
			folder.mkdirs();
		}
		for (int size : iconSize) {
			File dfile = new File(folder, fileName + "_" + size + "." + ext);
			ImageUtils.zoom(sFile, dfile, size, size, ext);
		}
	}

}