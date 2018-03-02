package com.yanda.service;

import java.io.Serializable;

import com.yanda.exception.DOPException;

public interface BaseService<T extends Serializable, ID> {
	
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
	
}
