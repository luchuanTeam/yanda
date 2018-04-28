package com.yanda.service;

import java.util.List;

import com.yanda.entity.generated.RoleInfo;

public interface UserRoleService extends BaseService<RoleInfo, Integer> {
	
	/**
     * 根据用户Id查询用户角色
     * @param userId
     * @return
     */
    List<RoleInfo> findUserRoleByUserId(int userId);
    
    /**
     * 查询角色列表
     * @return
     */
    List<RoleInfo> list();
    
    /**
     * 更新用户角色
     * @param userId
     * @param roleIds
     */
    void updateUserRoles(int userId, String roleIds);
    
    
	
}
