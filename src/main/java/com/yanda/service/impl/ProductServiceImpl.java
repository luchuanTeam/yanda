package com.yanda.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.yanda.entity.generated.ProductInfo;
import com.yanda.entity.generated.ProductInfoExample;
import com.yanda.exception.DOPException;
import com.yanda.mapper.generated.ProductInfoMapper;
import com.yanda.service.ProductService;
import com.yanda.util.Const;

@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductInfoMapper, ProductInfo, Integer> implements ProductService {
	
	@Cacheable(value = "productList")
	@Override
	public List<ProductInfo> findAll() {
		ProductInfoExample example = new ProductInfoExample();
		example.createCriteria().andProductEqualTo(Const.PRODUCT_VIP);
		example.setOrderByClause("product_order desc");
		return this.mapper.selectByExample(example);
	}

	
	@CacheEvict(value = {"productList"}, allEntries=true, beforeInvocation=true)
	@Override
	public int save(ProductInfo t) throws DOPException {
		return super.save(t);
	}
	
	@CacheEvict(value = {"productList", "product"}, allEntries=true, beforeInvocation=true)
	@Override
	public int deleteById(Integer id) throws DOPException {
		return super.deleteById(id);
	}
	
	@CacheEvict(value = {"productList", "product"}, allEntries=true, beforeInvocation=true)
	@Override
	public int update(ProductInfo t) throws DOPException {
		return super.update(t);
	}
	
	@Cacheable(value = "product")
	@Override
	public ProductInfo selectById(Integer id) throws DOPException {
		return super.selectById(id);
	}
	
	@CacheEvict(value = {"productList", "product"}, allEntries=true, beforeInvocation=true)
	@Override
	public int upsertSelective(ProductInfo t) throws DOPException {
		return super.upsertSelective(t);
	}

	
	
}
