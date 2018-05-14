package com.yanda.entity;

public class SimpleEpisode {
	
	/**
     * 集数ID
     */
    private Long episodeId;

    /**
     * 当前集数
     */
    private Integer episodeNum;
    
    private String episodeName;

	public Long getEpisodeId() {
		return episodeId;
	}

	public void setEpisodeId(Long episodeId) {
		this.episodeId = episodeId;
	}

	public Integer getEpisodeNum() {
		return episodeNum;
	}

	public void setEpisodeNum(Integer episodeNum) {
		this.episodeNum = episodeNum;
	}
	
    
    
}
