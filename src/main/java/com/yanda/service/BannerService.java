package com.yanda.service;

import com.yanda.entity.PageResult;
import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.entity.generated.BannerInfo;
import com.yanda.exception.DOPException;

public interface BannerService extends BaseService<BannerInfo, Long> {
	
    PageResult<BannerInfo> list(int pageNum, int pageSize, String searchVal);

	void addBanner(AttachmentInfo attachmentInfo, BannerInfo bannerInfo) throws DOPException;

	
	
}
