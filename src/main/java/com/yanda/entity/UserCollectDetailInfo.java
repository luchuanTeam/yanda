package com.yanda.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yanda.entity.generated.EpisodeInfo;
import com.yanda.entity.generated.UserInfo;


public class UserCollectDetailInfo extends UserInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	
	/**
	 * 覆盖账号密码，不返回前台
	 */
	@JsonIgnore
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	private List<EpisodeInfo> episodes;

	public List<EpisodeInfo> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<EpisodeInfo> episodes) {
		this.episodes = episodes;
	}
	
	public UserCollectDetailInfo() {
		super();
	}
	
	

}
