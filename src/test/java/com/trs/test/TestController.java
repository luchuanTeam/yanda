package com.trs.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.yanda.Application;
import com.yanda.Configuration;
import com.yanda.component.MessageSender;
import com.yanda.entity.generated.BannerInfo;
import com.yanda.mapper.generated.BannerInfoMapper;
import com.yanda.service.ReportService;

/**
 * 测试用例
 * 
 * @author chenli
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class, Configuration.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestController {

	@Autowired
	private BannerInfoMapper mapper;
	@Autowired
	private ReportService report;
	@Autowired
	private MessageSender meSender;

	
	public void testPushApi() {
		for (int i=0;i<3;i++) {
			BannerInfo record = new BannerInfo();
			record.setCreateTime(new Date());
			record.setBannerDesc("111");
			record.setUpdateTime(new Date());
			mapper.insertSelective(record);
		}
		
	}
	
	@Test
	public void testReport() {
		System.out.println(JSON.toJSONString(report.getClassifyMvCount()));
	}
	
	@Test
	public void send() {
		meSender.sendMessage("1111", "17620059260");
		
	}

}
