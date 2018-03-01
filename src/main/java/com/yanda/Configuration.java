package com.yanda;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.yanda.web.filter.DOPXssFilter;


/**
 * @Title: DOPConfiguration.java 
 * @Description: 替代传统的web.xml，使用代码的形式做配置 
 * @author chenli
 * @date 2016年12月13日 上午10:41:28
 * @version V1.0.0   
 */
@EnableAutoConfiguration
@ComponentScan(excludeFilters = @ComponentScan.Filter(classes = Controller.class))
@Import(WebConfiguration.class)
public class Configuration {
	
	/**
	 * Xss filter
	 */
	@Bean
	public FilterRegistrationBean XssFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new DOPXssFilter());
		registrationBean.addUrlPatterns("/*");
		registrationBean.setName("XssFilter");
		return registrationBean;
	}
	
	@Bean  
    public RestTemplate restTemplate(ClientHttpRequestFactory factory){  
        return new RestTemplate(factory);  
    }  
      
    @Bean  
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){  
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();  
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(15000); 
        return factory;  
    }  
    
    /**
     * 定时任务线程池bean注册
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
       return new ThreadPoolTaskScheduler();
    }


}
