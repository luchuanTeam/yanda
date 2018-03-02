package com.yanda.mapper.generated;

import com.yanda.entity.generated.ClassifyInfo;
import com.yanda.entity.generated.ClassifyInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;

public interface ClassifyInfoMapper extends MyMapper<ClassifyInfo> {
    List<ClassifyInfo> selectByExample(ClassifyInfoExample example);
}