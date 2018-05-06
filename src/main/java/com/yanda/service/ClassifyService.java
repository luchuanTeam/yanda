package com.yanda.service;

import com.yanda.entity.PageResult;
import com.yanda.entity.generated.ClassifyInfo;

public interface ClassifyService extends BaseService<ClassifyInfo, Integer> {
	
	/**
	 * 获取一级分类列表数据
	 * @param pageNum
	 * @param pageSize
	 * @param searchVal 
	 * @return
	 */
    PageResult<ClassifyInfo> list(int pageNum, int pageSize, String searchVal);
    
    /**
	 * 获取二级分类列表数据
	 * @param pageNum
	 * @param pageSize
	 * @param parentId
	 * @param searchVal 轮播图描述 用于搜索
	 * @return
	 */
    PageResult<ClassifyInfo> getClassifyById(int pageNum, int pageSize, Integer parentId, String searchVal);

}
