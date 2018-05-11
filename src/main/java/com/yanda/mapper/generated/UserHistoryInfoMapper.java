package com.yanda.mapper.generated;

import com.yanda.entity.generated.UserHistoryInfo;
import com.yanda.entity.generated.UserHistoryInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserHistoryInfoMapper extends MyMapper<UserHistoryInfo> {
    long countByExample(UserHistoryInfoExample example);

    int deleteByExample(UserHistoryInfoExample example);

    List<UserHistoryInfo> selectByExample(UserHistoryInfoExample example);

    List<UserHistoryInfo> selectByExampleSelective(@Param("example") UserHistoryInfoExample example, @Param("selective") UserHistoryInfo.Col ... selective);

    int updateByExampleSelective(@Param("record") UserHistoryInfo record, @Param("example") UserHistoryInfoExample example);

    int updateByExample(@Param("record") UserHistoryInfo record, @Param("example") UserHistoryInfoExample example);

    UserHistoryInfo selectByPrimaryKeySelective(@Param("historyId") Long historyId, @Param("selective") UserHistoryInfo.Col ... selective);

    int upsert(UserHistoryInfo record);

    int upsertSelective(UserHistoryInfo record);
}