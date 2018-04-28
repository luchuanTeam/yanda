package com.yanda.mapper.generated;

import com.yanda.entity.generated.RoleInfo;
import com.yanda.entity.generated.RoleInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;

public interface RoleInfoMapper extends MyMapper<RoleInfo> {
    List<RoleInfo> selectByExample(RoleInfoExample example);
}