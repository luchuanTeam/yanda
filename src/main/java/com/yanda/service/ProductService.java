package com.yanda.service;

import java.util.List;

import com.yanda.entity.generated.ProductInfo;

public interface ProductService extends BaseService<ProductInfo, Integer> {
	
	List<ProductInfo> findAll();
	
}
