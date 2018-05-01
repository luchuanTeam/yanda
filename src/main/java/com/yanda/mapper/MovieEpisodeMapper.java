package com.yanda.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yanda.entity.SimpleEpisode;

public interface MovieEpisodeMapper {
	
	List<SimpleEpisode> findSimpleEpisodeByMvId(@Param("mvId") Long mvId);
	
}
