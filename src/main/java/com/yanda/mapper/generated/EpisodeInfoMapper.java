package com.yanda.mapper.generated;

import com.yanda.entity.generated.EpisodeInfo;
import com.yanda.entity.generated.EpisodeInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EpisodeInfoMapper extends MyMapper<EpisodeInfo> {
    long countByExample(EpisodeInfoExample example);

    int deleteByExample(EpisodeInfoExample example);

    List<EpisodeInfo> selectByExample(EpisodeInfoExample example);

    List<EpisodeInfo> selectByExampleSelective(@Param("example") EpisodeInfoExample example, @Param("selective") EpisodeInfo.Col ... selective);

    int updateByExampleSelective(@Param("record") EpisodeInfo record, @Param("example") EpisodeInfoExample example);

    int updateByExample(@Param("record") EpisodeInfo record, @Param("example") EpisodeInfoExample example);

    EpisodeInfo selectByPrimaryKeySelective(@Param("episodeId") Long episodeId, @Param("selective") EpisodeInfo.Col ... selective);

    int upsert(EpisodeInfo record);

    int upsertSelective(EpisodeInfo record);
}