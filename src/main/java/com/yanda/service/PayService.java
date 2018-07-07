package com.yanda.service;

import com.yanda.entity.PageResult;
import com.yanda.entity.generated.PayInfo;

public interface PayService extends BaseService<PayInfo, Long> {
	
	/**
	 * 对传入的字符串进行md5加密生成签名
	 * @return
	 */
	String getPaySign(String str);

	/**
	 * 根据 userId 来查询消费记录
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @return
	 */
	PageResult<PayInfo> list(int pageNum, int pageSize, int userId);
	
}
