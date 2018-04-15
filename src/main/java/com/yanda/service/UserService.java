package com.yanda.service;

import com.yanda.entity.generated.UserInfo;
import com.yanda.exception.DOPException;

public interface UserService extends BaseService<UserInfo, Long> {

	/**
	 * 登录
	 * @param userName
	 * @param password
	 * @return
	 */
	UserInfo login(String userName, String password);
	
	/**
	 * 注册用户
	 * @param userName
	 * @param password
	 * @throws DOPException 
	 */
	void register(String userName, String password) throws DOPException;
	
	/**
	 * 查询用户名是否已经被使用，注册时候使用
	 * @param userName
	 * @return
	 */
	boolean findUserNameIsExist(String userName);
}
