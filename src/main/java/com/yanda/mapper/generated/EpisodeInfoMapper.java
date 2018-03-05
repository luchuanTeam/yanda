package com.yanda.mapper.generated;

import com.yanda.entity.generated.EpisodeInfo;
import com.yanda.entity.generated.EpisodeInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;

public interface EpisodeInfoMapper extends MyMapper<EpisodeInfo> {
    List<EpisodeInfo> selectByExample(EpisodeInfoExample example);
}