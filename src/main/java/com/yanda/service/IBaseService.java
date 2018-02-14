package com.yanda.service;

import com.yanda.entity.PageResult;

/**
 * 说明：增删改基础服务接口
 * @author chenli
 * @time 2018年1月4日 上午11:22:18
 */
public interface IBaseService<T> {

	/**
	 * 添加一条记录
	 * @param t
	 * @return
	 */
	public int addRecord(T record);
	
	/**
	 * 删除一条记录
	 * @param t
	 * @return
	 */
	public int deleteRecord(int recordId);
	
	/**
	 * 更新一条记录
	 * @param t
	 * @return
	 */
	public int updateRecord(T record);
	
	/**
	 * 列表数据
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageResult<T> list(int pageNum, int pageSize, String searchVal);
	
}
