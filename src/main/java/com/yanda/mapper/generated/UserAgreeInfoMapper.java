package com.yanda.mapper.generated;

import com.yanda.entity.generated.UserAgreeInfo;
import com.yanda.entity.generated.UserAgreeInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;

public interface UserAgreeInfoMapper extends MyMapper<UserAgreeInfo> {
    List<UserAgreeInfo> selectByExample(UserAgreeInfoExample example);
}