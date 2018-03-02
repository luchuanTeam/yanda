package com.yanda;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;

@Configuration
public class MapperScannerConfigurerConfig {
	
	@Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
    	MapperScannerConfigurer mapper = new MapperScannerConfigurer();
    	mapper.setBasePackage("com.yanda.mapper");
    	return mapper;
    }
	
}
