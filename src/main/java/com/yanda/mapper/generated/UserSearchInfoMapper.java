package com.yanda.mapper.generated;

import com.yanda.entity.generated.UserSearchInfo;
import com.yanda.entity.generated.UserSearchInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserSearchInfoMapper extends MyMapper<UserSearchInfo> {
    long countByExample(UserSearchInfoExample example);

    int deleteByExample(UserSearchInfoExample example);

    List<UserSearchInfo> selectByExample(UserSearchInfoExample example);

    List<UserSearchInfo> selectByExampleSelective(@Param("example") UserSearchInfoExample example, @Param("selective") UserSearchInfo.Col ... selective);

    int updateByExampleSelective(@Param("record") UserSearchInfo record, @Param("example") UserSearchInfoExample example);

    int updateByExample(@Param("record") UserSearchInfo record, @Param("example") UserSearchInfoExample example);

    UserSearchInfo selectByPrimaryKeySelective(@Param("record") com.yanda.entity.generated.UserSearchInfoKey key, @Param("selective") UserSearchInfo.Col ... selective);

    int upsert(UserSearchInfo record);

    int upsertSelective(UserSearchInfo record);
}