package com.yanda.service;

import com.yanda.entity.PageResult;
import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.entity.generated.BannerInfo;
import com.yanda.exception.DOPException;

public interface BannerService extends BaseService<BannerInfo, Long> {
	/**
	 * 获取轮播图列表数据
	 * @param pageNum
	 * @param pageSize
	 * @param searchVal 轮播图描述 用于搜索
	 * @return
	 */
    PageResult<BannerInfo> list(int pageNum, int pageSize, String searchVal);
    
    /**
     * 添加轮播图，先保存附件，再保存轮播图实体记录
     * 保存图片附件、新增轮播图两个操作为事务性操作，其中一个报错都需要回滚
	 * 将两个个实体传入是为了在一个方法里进行数据库操作，形成一个事务
     * @param attachmentInfo
     * @param bannerInfo
     * @throws DOPException
     */
	void addBanner(AttachmentInfo attachmentInfo, BannerInfo bannerInfo) throws DOPException;

	
	
}
