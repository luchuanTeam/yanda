package com.yanda.mapper.generated;

import com.yanda.entity.generated.CommentInfo;
import com.yanda.entity.generated.CommentInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentInfoMapper extends MyMapper<CommentInfo> {
    long countByExample(CommentInfoExample example);

    int deleteByExample(CommentInfoExample example);

    List<CommentInfo> selectByExample(CommentInfoExample example);

    List<CommentInfo> selectByExampleSelective(@Param("example") CommentInfoExample example, @Param("selective") CommentInfo.Col ... selective);

    int updateByExampleSelective(@Param("record") CommentInfo record, @Param("example") CommentInfoExample example);

    int updateByExample(@Param("record") CommentInfo record, @Param("example") CommentInfoExample example);

    CommentInfo selectByPrimaryKeySelective(@Param("commentId") Long commentId, @Param("selective") CommentInfo.Col ... selective);

    int upsert(CommentInfo record);

    int upsertSelective(CommentInfo record);
}