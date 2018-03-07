package com.yanda;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * mybatis配置扫描类
 * 由于使用了通用mapper，此处的MapperScannerConfigurer要引入tk.mybatis.spring.mapper.MapperScannerConfigurer来扫描mapper
 * MapperScannerConfigurerConfig.java
 * @author chenli
 * @time 2018年3月7日 下午11:27:24
 */
@Configuration
public class MapperScannerConfigurerConfig {
	
	@Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
    	MapperScannerConfigurer mapper = new MapperScannerConfigurer();
    	mapper.setBasePackage("com.yanda.mapper");
    	return mapper;
    }
	
}
