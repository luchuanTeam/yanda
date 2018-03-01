package com.trs.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.yanda.Application;
import com.yanda.Configuration;
import com.yanda.entity.generated.BannerInfo;
import com.yanda.mapper.generated.BannerInfoMapper;

/**
 * 测试用例
 * 
 * @author chenli
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class, Configuration.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestController {

	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private BannerInfoMapper mapper;

	@Test
	public void testPushApi() {
		for (int i=0;i<3;i++) {
			BannerInfo record = new BannerInfo();
			record.setCreateTime(new Date());
			record.setImgDesc("111");
			record.setImgUrl("http://www.baidu.com");
			record.setUpdateTime(new Date());
			mapper.insertSelective(record);
			System.out.println("返回主键="+record.getImgId());
		}
		
	}

}
