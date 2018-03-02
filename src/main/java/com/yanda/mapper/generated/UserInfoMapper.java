package com.yanda.mapper.generated;

import com.yanda.entity.generated.UserInfo;
import com.yanda.entity.generated.UserInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;

public interface UserInfoMapper extends MyMapper<UserInfo> {
    List<UserInfo> selectByExample(UserInfoExample example);
}