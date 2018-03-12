package com.yanda.mapper.generated;

import com.yanda.entity.generated.CommentInfo;
import com.yanda.entity.generated.CommentInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;

public interface CommentInfoMapper extends MyMapper<CommentInfo> {
    List<CommentInfo> selectByExample(CommentInfoExample example);
}