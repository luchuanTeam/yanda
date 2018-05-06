package com.yanda.mapper.generated;

import com.yanda.entity.generated.UserAgreeInfo;
import com.yanda.entity.generated.UserAgreeInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAgreeInfoMapper extends MyMapper<UserAgreeInfo> {
    long countByExample(UserAgreeInfoExample example);

    int deleteByExample(UserAgreeInfoExample example);

    List<UserAgreeInfo> selectByExample(UserAgreeInfoExample example);

    List<UserAgreeInfo> selectByExampleSelective(@Param("example") UserAgreeInfoExample example, @Param("selective") UserAgreeInfo.Col ... selective);

    int updateByExampleSelective(@Param("record") UserAgreeInfo record, @Param("example") UserAgreeInfoExample example);

    int updateByExample(@Param("record") UserAgreeInfo record, @Param("example") UserAgreeInfoExample example);

    UserAgreeInfo selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UserAgreeInfo.Col ... selective);

    int upsert(UserAgreeInfo record);

    int upsertSelective(UserAgreeInfo record);
}