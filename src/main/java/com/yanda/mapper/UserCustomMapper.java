package com.yanda.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yanda.entity.UserCollectDetailInfo;
import com.yanda.entity.UserDetailInfo;
import com.yanda.entity.generated.RoleInfo;

public interface UserCustomMapper {
	
	/**
	 * 根据用户id查询用户详细信息
	 * @param userId
	 * @return
	 */
	UserDetailInfo findUserDetailByUserId(@Param("userId") int userId);
	
	/**
	 * 根据用户id查询用户角色
	 * @param userId
	 * @return
	 */
	List<RoleInfo> findUserRoleByUserId(@Param("user_id") int userId);
	
	/**
	 * 根据用户id查询用户收藏
	 * @param userId
	 * @return
	 */
	UserCollectDetailInfo findUserCollectDetailByUserId(@Param("user_id") Long userId);
	
}
