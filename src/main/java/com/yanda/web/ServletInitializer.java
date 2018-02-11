package com.yanda.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.yanda.Configuration;

/**
 * @Title: DOPServletInitializer.java 
 * @Description: 通过web的形式启动工程，实现 CommsServletInitializer启动监听类
 * @author chenli
 * @date 2016年12月13日 下午4:01:44
 * @version V1.0.0   
 */
@MapperScan("com.yanda.mapper")
public class ServletInitializer extends SpringBootServletInitializer {
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 加载配置文件
		return builder.sources(Configuration.class);
	}
	
	@Override
	public void onStartup(final ServletContext container) throws ServletException {
		super.onStartup(container);
	}
}
