package com.yanda.mapper.generated;

import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.entity.generated.AttachmentInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AttachmentInfoMapper extends MyMapper<AttachmentInfo> {
    long countByExample(AttachmentInfoExample example);

    int deleteByExample(AttachmentInfoExample example);

    List<AttachmentInfo> selectByExample(AttachmentInfoExample example);

    List<AttachmentInfo> selectByExampleSelective(@Param("example") AttachmentInfoExample example, @Param("selective") AttachmentInfo.Col ... selective);

    int updateByExampleSelective(@Param("record") AttachmentInfo record, @Param("example") AttachmentInfoExample example);

    int updateByExample(@Param("record") AttachmentInfo record, @Param("example") AttachmentInfoExample example);

    AttachmentInfo selectByPrimaryKeySelective(@Param("appendixId") Long appendixId, @Param("selective") AttachmentInfo.Col ... selective);

    int upsert(AttachmentInfo record);

    int upsertSelective(AttachmentInfo record);
}