package com.yanda.service;

import com.yanda.entity.generated.VipCardInfo;

public interface VipCardService extends BaseService<VipCardInfo, Integer> {
	
	/**
	 * 获取会员卡号码
	 * @return
	 */
	String getVipCardNum();
	
	/**
	 * 通过会员卡账号密码查询会员卡信息
	 * @param cardNum
	 * @param password
	 * @return
	 */
	VipCardInfo findByCardNumAndPassword(String cardNum, String password);
	
	/**
	 * 通过绑定的用户id查询会员卡信息
	 * @param userId
	 * @return
	 */
	VipCardInfo findByUserId(Integer userId);
	
}
