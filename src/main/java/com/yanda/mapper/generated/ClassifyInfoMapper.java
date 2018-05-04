package com.yanda.mapper.generated;

import com.yanda.entity.generated.ClassifyInfo;
import com.yanda.entity.generated.ClassifyInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassifyInfoMapper extends MyMapper<ClassifyInfo> {
    long countByExample(ClassifyInfoExample example);

    int deleteByExample(ClassifyInfoExample example);

    List<ClassifyInfo> selectByExample(ClassifyInfoExample example);

    List<ClassifyInfo> selectByExampleSelective(@Param("example") ClassifyInfoExample example, @Param("selective") ClassifyInfo.Col ... selective);

    int updateByExampleSelective(@Param("record") ClassifyInfo record, @Param("example") ClassifyInfoExample example);

    int updateByExample(@Param("record") ClassifyInfo record, @Param("example") ClassifyInfoExample example);

    ClassifyInfo selectByPrimaryKeySelective(@Param("classifyId") Integer classifyId, @Param("selective") ClassifyInfo.Col ... selective);
}