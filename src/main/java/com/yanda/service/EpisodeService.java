package com.yanda.service;

import com.yanda.entity.PageResult;
import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.entity.generated.EpisodeInfo;
import com.yanda.entity.generated.MovieInfo;
import com.yanda.exception.DOPException;

public interface EpisodeService extends BaseService<EpisodeInfo, Long> {
	
	PageResult<EpisodeInfo> list(int pageNum, int pageSize, Long mvId, String searchVal);
	
	void addEpisode(AttachmentInfo imgAttach, AttachmentInfo videoAttach, MovieInfo movieInfo, EpisodeInfo episodeInfo) throws DOPException;
	
}
