package com.yanda.mapper.generated;

import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.entity.generated.AttachmentInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;

public interface AttachmentInfoMapper extends MyMapper<AttachmentInfo> {
    List<AttachmentInfo> selectByExample(AttachmentInfoExample example);
}