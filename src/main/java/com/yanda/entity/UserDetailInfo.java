package com.yanda.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yanda.entity.generated.RoleInfo;
import com.yanda.entity.generated.UserInfo;

public class UserDetailInfo extends UserInfo {

	
	private static final long serialVersionUID = 2596516089919551490L;
	
	/**
	 * 用户角色
	 */
	private List<RoleInfo> role;
	
	/**
	 * 覆盖账号密码，不返回前台
	 */
	@JsonIgnore
	private String password;

	

	public List<RoleInfo> getRole() {
		return role;
	}



	public void setRole(List<RoleInfo> role) {
		this.role = role;
	}

	

	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public UserDetailInfo() {
		super();
	}



	public UserDetailInfo(Integer userId, String userName, String password, String nickName, String mobile, Integer sex,
			Date createTime, Date updateTime, String avatar, Integer status, List<RoleInfo> role) {
		super(userId, userName, password, nickName, mobile, sex, createTime, updateTime, avatar, status);
		this.role = role;
	}


	
}
