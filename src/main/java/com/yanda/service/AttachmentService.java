package com.yanda.service;

import java.io.IOException;

import com.yanda.entity.generated.AttachmentInfo;

public interface AttachmentService {

	String generatedFilePath(AttachmentInfo record) throws IOException;

}
