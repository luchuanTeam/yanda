package com.yanda.mapper.generated;

import com.yanda.entity.generated.UserRoleInfo;
import com.yanda.entity.generated.UserRoleInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;

public interface UserRoleInfoMapper extends MyMapper<UserRoleInfo> {
    List<UserRoleInfo> selectByExample(UserRoleInfoExample example);
}