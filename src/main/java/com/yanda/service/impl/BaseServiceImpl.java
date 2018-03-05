package com.yanda.service.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.yanda.exception.DOPException;
import com.yanda.service.BaseService;
import com.yanda.util.MyMapper;

public abstract class BaseServiceImpl<D extends MyMapper<T>, T extends Serializable, ID> implements BaseService<T, ID> {

	protected Logger LOG = Logger.getLogger(this.getClass());
	
	@Autowired
    protected D mapper;
	
    public D getMapper() {
        return mapper;
    } 
	
	/**
	 * 新增实体
	 * @param t
	 * @return
	 */
    @Override
	public int save(T t) throws DOPException {
		return this.mapper.insertSelective(t);
	}
  
    /** 
     * 通过id删除实体 
     * @param id id 
     * @return 提示信息 
     */  
    @Override
    public int deleteById(ID id) throws DOPException {  
        return this.mapper.deleteByPrimaryKey(id);  
    }
    
    /**
     * 修改实体
     * @param record
     * @return
     */
    @Override
    public int update(T t) throws DOPException {
		return this.mapper.updateByPrimaryKeySelective(t);
	}
    
    /** 
     * 通过id查询 实体 
     * @param id id 
     * @return 实体 
     */  
    @Override
    public T selectById(ID id) throws DOPException {  
        return this.mapper.selectByPrimaryKey(id);  
    }

}
