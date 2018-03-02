package com.yanda.mapper.generated;

import com.yanda.entity.generated.BannerInfo;
import com.yanda.entity.generated.BannerInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;

public interface BannerInfoMapper extends MyMapper<BannerInfo> {
    List<BannerInfo> selectByExample(BannerInfoExample example);
}