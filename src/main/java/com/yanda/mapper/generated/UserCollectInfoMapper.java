package com.yanda.mapper.generated;

import com.yanda.entity.generated.UserCollectInfo;
import com.yanda.entity.generated.UserCollectInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCollectInfoMapper extends MyMapper<UserCollectInfo> {
    long countByExample(UserCollectInfoExample example);

    int deleteByExample(UserCollectInfoExample example);

    List<UserCollectInfo> selectByExample(UserCollectInfoExample example);

    List<UserCollectInfo> selectByExampleSelective(@Param("example") UserCollectInfoExample example, @Param("selective") UserCollectInfo.Col ... selective);

    int updateByExampleSelective(@Param("record") UserCollectInfo record, @Param("example") UserCollectInfoExample example);

    int updateByExample(@Param("record") UserCollectInfo record, @Param("example") UserCollectInfoExample example);

    UserCollectInfo selectByPrimaryKeySelective(@Param("collectId") Long collectId, @Param("selective") UserCollectInfo.Col ... selective);

    int upsert(UserCollectInfo record);

    int upsertSelective(UserCollectInfo record);
}