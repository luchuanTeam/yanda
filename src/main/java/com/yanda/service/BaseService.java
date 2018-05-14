package com.yanda.service;

import java.io.Serializable;

import com.yanda.entity.PageResult;
import com.yanda.exception.DOPException;


/**
 * 增删改查接口服务类
 * 所有服务类都要集成此接口，方便通过接口依赖注入的服务类调用相关方法
 * BaseService.java
 * @author chenli
 * @time 2018年3月7日 下午11:09:08
 */
public interface BaseService<T extends Serializable, ID> {
	
	
	PageResult<T> getList(int pageNum, int pageSize, String searchVal);
	
	/**
	 * 新增实体
	 * @param t
	 * @return
	 */
	public int save(T t) throws DOPException;
  
    /** 
     * 通过id删除实体 
     * @param id id 
     * @return 提示信息 
     */  
    public int deleteById(ID id) throws DOPException;
    
    /**
     * 修改实体
     * @param record
     * @return
     */
    public int update(T t) throws DOPException;
    
    /** 
     * 通过id查询 实体 
     * @param id id 
     * @return 实体 
     */  
    public T selectById(ID id) throws DOPException;
    
    /**
     * 存在即更新
     * @param t
     * @return
     * @throws DOPException
     */
    public int upsertSelective(T t) throws DOPException;
  
	
}
