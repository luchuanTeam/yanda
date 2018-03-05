package com.yanda.service;

import java.io.IOException;

import com.yanda.entity.generated.AttachmentInfo;

public interface AttachmentService extends BaseService<AttachmentInfo, Long> {

	String generatedFilePath(AttachmentInfo record) throws IOException;

}
