package com.yanda.service;

import com.yanda.entity.PageResult;
import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.entity.generated.EpisodeInfo;
import com.yanda.entity.generated.MovieInfo;
import com.yanda.exception.DOPException;

public interface EpisodeService extends BaseService<EpisodeInfo, Long> {
	/**
	 * 根据视频id获取视频集
	 * @param pageNum
	 * @param pageSize
	 * @param mvId 视频id
	 * @param searchVal 视频名称 用于搜索
	 * @return
	 */
	PageResult<EpisodeInfo> list(int pageNum, int pageSize, Long mvId, String searchVal);
	
	/**
	 * 添加一条视频集
	 * 保存图片附件、视频附件、更新视频信息、新增视频集四个操作为事务性操作，其中一个报错都需要回滚
	 * 将四个实体传入是为了在一个方法里进行数据库操作，形成一个事务
	 * @param imgAttach 视频集对应的图片附件
	 * @param videoAttach 视频集对应的视频附件
	 * @param movieInfo 视频集对应的视频
	 * @param episodeInfo 视频集实体记录
	 * @throws DOPException
	 */
	void addEpisode(AttachmentInfo imgAttach, AttachmentInfo videoAttach, MovieInfo movieInfo, EpisodeInfo episodeInfo) throws DOPException;
	
	/**
	 * 根据视频ID和视频集数查询视频集
	 * @param mvId
	 * @param episodeNum
	 * @return
	 */
	EpisodeInfo selectByMvIdAndEpisodeNum(Long mvId, int episodeNum);
}
