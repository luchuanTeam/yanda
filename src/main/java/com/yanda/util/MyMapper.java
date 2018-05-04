package com.yanda.util;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 定义自己的MyMapper接口，该接口要继承Mapper<T>, MySqlMapper<T>，来继承mybatis的增删改查通用接口
 * 定义改接口的原因是可以自定义其他的一些接口而不仅仅限制于Mapper里的接口
 * FIXME 特别注意，该接口不能被扫描到，否则会出错 
 * FIXME 最后在启动类中通过MapperScan注解指定扫描的mapper路径： 
 * MyMapper.java
 * @author chenli
 * @time 2018年3月2日 上午9:02:34
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
	/**
	 * 以下接口均是为了避免tk.mybtis扫描时报Mapped Statements collection already contains value的错误
	 */
	
	
	List<T> selectByExample(Object example);
	
	int deleteByExample(Object example);
	
	Long countByExample(Object example);
	
    int updateByExample(T obj, Object obj1);
    
    int updateByExampleSelective(T obj, Object obj1);
}
