package com.yanda.mapper.generated;

import com.yanda.entity.generated.UserRoleInfo;
import com.yanda.entity.generated.UserRoleInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleInfoMapper extends MyMapper<UserRoleInfo> {
    long countByExample(UserRoleInfoExample example);

    int deleteByExample(UserRoleInfoExample example);

    List<UserRoleInfo> selectByExample(UserRoleInfoExample example);

    List<UserRoleInfo> selectByExampleSelective(@Param("example") UserRoleInfoExample example, @Param("selective") UserRoleInfo.Col ... selective);

    int updateByExampleSelective(@Param("record") UserRoleInfo record, @Param("example") UserRoleInfoExample example);

    int updateByExample(@Param("record") UserRoleInfo record, @Param("example") UserRoleInfoExample example);

    UserRoleInfo selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") UserRoleInfo.Col ... selective);
}