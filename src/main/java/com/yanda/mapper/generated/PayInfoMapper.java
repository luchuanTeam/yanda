package com.yanda.mapper.generated;

import com.yanda.entity.generated.PayInfo;
import com.yanda.entity.generated.PayInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayInfoMapper extends MyMapper<PayInfo> {
    long countByExample(PayInfoExample example);

    int deleteByExample(PayInfoExample example);

    List<PayInfo> selectByExample(PayInfoExample example);

    List<PayInfo> selectByExampleSelective(@Param("example") PayInfoExample example, @Param("selective") PayInfo.Col ... selective);

    int updateByExampleSelective(@Param("record") PayInfo record, @Param("example") PayInfoExample example);

    int updateByExample(@Param("record") PayInfo record, @Param("example") PayInfoExample example);

    PayInfo selectByPrimaryKeySelective(@Param("payId") Integer payId, @Param("selective") PayInfo.Col ... selective);

    int upsert(PayInfo record);

    int upsertSelective(PayInfo record);
}