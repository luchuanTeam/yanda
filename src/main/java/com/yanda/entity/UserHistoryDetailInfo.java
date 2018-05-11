package com.yanda.entity;


import com.yanda.entity.generated.EpisodeInfo;
import com.yanda.entity.generated.UserHistoryInfo;

public class UserHistoryDetailInfo extends UserHistoryInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EpisodeInfo episodeInfo;
	
	public UserHistoryDetailInfo() {
		super();
	}

	public EpisodeInfo getEpisodeInfo() {
		return episodeInfo;
	}

	public void setEpisodeInfo(EpisodeInfo episodeInfo) {
		this.episodeInfo = episodeInfo;
	}
	

}
