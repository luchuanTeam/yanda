package com.yanda.entity;



import com.yanda.entity.generated.EpisodeInfo;
import com.yanda.entity.generated.UserCollectInfo;


public class UserCollectDetailInfo extends UserCollectInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EpisodeInfo episodeInfo;
	
	public UserCollectDetailInfo() {
		super();
	}
	public EpisodeInfo getEpisodeInfo() {
		return episodeInfo;
	}
	public void setEpisodeInfo(EpisodeInfo episodeInfo) {
		this.episodeInfo = episodeInfo;
	}
	

}
