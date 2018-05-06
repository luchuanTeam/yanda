package com.yanda.mapper.generated;

import com.yanda.entity.generated.BannerInfo;
import com.yanda.entity.generated.BannerInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BannerInfoMapper extends MyMapper<BannerInfo> {
    long countByExample(BannerInfoExample example);

    int deleteByExample(BannerInfoExample example);

    List<BannerInfo> selectByExample(BannerInfoExample example);

    List<BannerInfo> selectByExampleSelective(@Param("example") BannerInfoExample example, @Param("selective") BannerInfo.Col ... selective);

    int updateByExampleSelective(@Param("record") BannerInfo record, @Param("example") BannerInfoExample example);

    int updateByExample(@Param("record") BannerInfo record, @Param("example") BannerInfoExample example);

    BannerInfo selectByPrimaryKeySelective(@Param("bannerId") Long bannerId, @Param("selective") BannerInfo.Col ... selective);

    int upsert(BannerInfo record);

    int upsertSelective(BannerInfo record);
}