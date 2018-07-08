package com.yanda.mapper.generated;

import com.yanda.entity.generated.ProductInfo;
import com.yanda.entity.generated.ProductInfoExample;
import com.yanda.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductInfoMapper extends MyMapper<ProductInfo> {
    long countByExample(ProductInfoExample example);

    int deleteByExample(ProductInfoExample example);

    List<ProductInfo> selectByExample(ProductInfoExample example);

    List<ProductInfo> selectByExampleSelective(@Param("example") ProductInfoExample example, @Param("selective") ProductInfo.Col ... selective);

    int updateByExampleSelective(@Param("record") ProductInfo record, @Param("example") ProductInfoExample example);

    int updateByExample(@Param("record") ProductInfo record, @Param("example") ProductInfoExample example);

    ProductInfo selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") ProductInfo.Col ... selective);

    int upsert(ProductInfo record);

    int upsertSelective(ProductInfo record);
}