package com.yanda.mapper.generated;

import com.yanda.entity.generated.UserInfo;
import com.yanda.entity.generated.UserInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper extends MyMapper<UserInfo> {
    long countByExample(UserInfoExample example);

    int deleteByExample(UserInfoExample example);

    List<UserInfo> selectByExample(UserInfoExample example);

    List<UserInfo> selectByExampleSelective(@Param("example") UserInfoExample example, @Param("selective") UserInfo.Col ... selective);

    int updateByExampleSelective(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByExample(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    UserInfo selectByPrimaryKeySelective(@Param("userId") Integer userId, @Param("selective") UserInfo.Col ... selective);

    int upsert(UserInfo record);

    int upsertSelective(UserInfo record);
}