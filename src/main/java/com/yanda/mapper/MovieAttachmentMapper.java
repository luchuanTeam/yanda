package com.yanda.mapper;

import org.apache.ibatis.annotations.Param;

import com.yanda.entity.EpisodeDetailInfo;

/**
 * 视频和附件相关接口
 * MovieAttachmentMapper.java
 * @author chenli
 * @time 2018年4月22日 下午5:18:09
 */
public interface MovieAttachmentMapper {
	
	/**
	 * 根据视频ID和集数查询某一集的具体信息
	 * @param mvId
	 * @param num
	 * @return
	 */
	EpisodeDetailInfo findEpisodeDetailInfoByMvIdAndNum(@Param("mvId") Long mvId, @Param("num") int num);
	
}
