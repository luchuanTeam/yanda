package com.yanda.service;

import com.yanda.entity.PageResult;
import com.yanda.entity.UserDetailInfo;
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
	
	/**
	 * 获取用户列表数据
	 * @param pageNum
	 * @param pageSize
	 * @param searchVal 轮播图描述 用于搜索
	 * @return
	 */
    PageResult<UserInfo> list(int pageNum, int pageSize, String searchVal);
    
    /**
     * 根据用户ID查询用户详情
     * @param userId
     * @return
     */
    UserDetailInfo findUserDetailByUserId(int userId);
    
    /**
     * 根据微信openid查询用户是否存在
     * @param openId
     * @return
     */
    UserInfo findWechatIsExist(String openId);
    
    /**
     * 通过用户名删除用户
     * @param userName
     * @return
     * @throws DOPException
     */
    int deleteByUserName(String userName) throws DOPException;
    
    /**
     * 通过用户名查找用户
     * @param userName
     * @return
     * @throws DOPException
     */
    UserInfo findUserByUserName(String userName) throws DOPException;
} 
