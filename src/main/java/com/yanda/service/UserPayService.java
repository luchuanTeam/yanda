package com.yanda.service;

public interface UserPayService {

	/**
	 * 对传入的字符串进行md5加密生成签名
	 * @return
	 */
	String getPaySign(String str);
}
