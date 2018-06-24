package com.yanda.service;

import com.yanda.entity.generated.VipCardInfo;

public interface VipCardService extends BaseService<VipCardInfo, Integer> {
	
	/**
	 * 获取会员卡号码
	 * @return
	 */
	String getVipCardNum();
	
	
}
