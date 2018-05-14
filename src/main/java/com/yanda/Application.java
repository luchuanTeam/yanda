
package com.yanda;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Title: DOPApplication.java 
 * @Description: 通过main方法启动工程
 * @author chenli
 * @date 2016年12月13日 上午11:18:36
 * @version V1.0.0   
 */
@SpringBootApplication
@MapperScan(basePackages = "com.yanda.mapper")
@EnableScheduling
@EnableConfigurationProperties
public class Application { 
	
	public static void main(String[] args) throws Exception {
		// 加载配置文件
		SpringApplication application = new SpringApplication(Configuration.class, Application.class);
		application.run(args);
	}
	
}
