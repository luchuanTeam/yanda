package com.yanda.mapper.generated;

import com.yanda.entity.generated.RoleInfo;
import com.yanda.entity.generated.RoleInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleInfoMapper extends MyMapper<RoleInfo> {
    long countByExample(RoleInfoExample example);

    int deleteByExample(RoleInfoExample example);

    List<RoleInfo> selectByExample(RoleInfoExample example);

    List<RoleInfo> selectByExampleSelective(@Param("example") RoleInfoExample example, @Param("selective") RoleInfo.Col ... selective);

    int updateByExampleSelective(@Param("record") RoleInfo record, @Param("example") RoleInfoExample example);

    int updateByExample(@Param("record") RoleInfo record, @Param("example") RoleInfoExample example);

    RoleInfo selectByPrimaryKeySelective(@Param("roleId") Integer roleId, @Param("selective") RoleInfo.Col ... selective);

    int upsert(RoleInfo record);

    int upsertSelective(RoleInfo record);
}