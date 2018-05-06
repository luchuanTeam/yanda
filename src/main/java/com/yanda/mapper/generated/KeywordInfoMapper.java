package com.yanda.mapper.generated;

import com.yanda.entity.generated.KeywordInfo;
import com.yanda.entity.generated.KeywordInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface KeywordInfoMapper extends MyMapper<KeywordInfo> {
    long countByExample(KeywordInfoExample example);

    int deleteByExample(KeywordInfoExample example);

    List<KeywordInfo> selectByExample(KeywordInfoExample example);

    List<KeywordInfo> selectByExampleSelective(@Param("example") KeywordInfoExample example, @Param("selective") KeywordInfo.Col ... selective);

    int updateByExampleSelective(@Param("record") KeywordInfo record, @Param("example") KeywordInfoExample example);

    int updateByExample(@Param("record") KeywordInfo record, @Param("example") KeywordInfoExample example);

    KeywordInfo selectByPrimaryKeySelective(@Param("record") com.yanda.entity.generated.KeywordInfoKey key, @Param("selective") KeywordInfo.Col ... selective);

    int upsert(KeywordInfo record);

    int upsertSelective(KeywordInfo record);
}