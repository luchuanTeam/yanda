package com.yanda.util;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承自己的MyMapper 
 * FIXME 特别注意，该接口不能被扫描到，否则会出错 
 * FIXME 最后在启动类中通过MapperScan注解指定扫描的mapper路径： 
 * MyMapper.java
 * @author chenli
 * @time 2018年3月2日 上午9:02:34
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

	List<T> selectByExample(Object example);
}
