package com.yanda.service;

import java.io.IOException;

import com.yanda.entity.generated.AttachmentInfo;

public interface AttachmentService extends BaseService<AttachmentInfo, Long> {
	
	/**
	 * 根据附件信息生成附件存放文件夹路径
	 * @param record
	 * @return
	 * @throws IOException
	 */
	String generatedFilePath(AttachmentInfo record) throws IOException;
	
	void updateAllDuration();

}
