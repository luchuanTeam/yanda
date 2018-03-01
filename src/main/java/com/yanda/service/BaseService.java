package com.yanda.service;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.yanda.entity.PageResult;
import com.yanda.exception.DOPException;

import tk.mybatis.mapper.common.Mapper;

public abstract class BaseService<D extends Mapper<T>, T extends Serializable, ID> {
	
	protected Logger LOG = Logger.getLogger(this.getClass());
	
	@Autowired  
    protected D mapper;  
	
	/**
	 * 新增实体
	 * @param t
	 * @return
	 */
	public int save(T t) throws DOPException {
		return this.mapper.insertSelective(t);
	}
  
    /** 
     * 通过id删除实体 
     * @param id id 
     * @return 提示信息 
     */  
    public int deleteById(ID id) throws DOPException {  
  
        return this.mapper.deleteByPrimaryKey(id);  
    }
    
    /**
     * 修改实体
     * @param record
     * @return
     */
    public int update(T t) throws DOPException {
		return this.mapper.updateByPrimaryKeySelective(t);
	}
    
    /** 
     * 通过id查询 实体 
     * @param id id 
     * @return 实体 
     */  
    public T selectByPrimaryKey(ID id) throws DOPException {  
        return this.mapper.selectByPrimaryKey(id);  
    } 
    
    /**
	 * 列表数据
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public abstract PageResult<T> list(int pageNum, int pageSize, String searchVal) throws DOPException;
	

	public D getMapper() {
		return mapper;
	}

	public void setMapper(D mapper) {
		this.mapper = mapper;
	}  
  
	
}
