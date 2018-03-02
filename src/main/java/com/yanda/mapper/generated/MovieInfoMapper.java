package com.yanda.mapper.generated;

import com.yanda.entity.generated.MovieInfo;
import com.yanda.entity.generated.MovieInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;

public interface MovieInfoMapper extends MyMapper<MovieInfo> {
    List<MovieInfo> selectByExample(MovieInfoExample example);
}