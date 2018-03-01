package com.yanda.service;

import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.entity.generated.BannerInfo;
import com.yanda.exception.DOPException;

public interface BannerService {

	void addBanner(AttachmentInfo attachmentInfo, BannerInfo bannerInfo) throws DOPException;

	
	
}
