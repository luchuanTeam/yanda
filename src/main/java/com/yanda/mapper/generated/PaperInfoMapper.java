package com.yanda.mapper.generated;

import com.yanda.entity.generated.PaperInfo;
import com.yanda.entity.generated.PaperInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PaperInfoMapper extends MyMapper<PaperInfo> {
    long countByExample(PaperInfoExample example);

    int deleteByExample(PaperInfoExample example);

    List<PaperInfo> selectByExample(PaperInfoExample example);

    List<PaperInfo> selectByExampleSelective(@Param("example") PaperInfoExample example, @Param("selective") PaperInfo.Col ... selective);

    int updateByExampleSelective(@Param("record") PaperInfo record, @Param("example") PaperInfoExample example);

    int updateByExample(@Param("record") PaperInfo record, @Param("example") PaperInfoExample example);

    PaperInfo selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") PaperInfo.Col ... selective);

    int upsert(PaperInfo record);

    int upsertSelective(PaperInfo record);
}