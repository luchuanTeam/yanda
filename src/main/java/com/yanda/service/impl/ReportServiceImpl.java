package com.yanda.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.yanda.entity.ReportInfo;
import com.yanda.entity.WebClassifyInfo;
import com.yanda.mapper.MovieClassifyMapper;
import com.yanda.mapper.ReportMapper;
import com.yanda.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	
	private Logger LOG = Logger.getLogger(this.getClass());
	
	@Autowired
	private ReportMapper reportMapper;
	@Autowired
	private MovieClassifyMapper movieClassifyMapper;
	
	
	@Cacheable(value = "report")
	@Override
	public ReportInfo getClassifyMvCount() {
		LOG.info("缓存失效，重新统计一级分类视频数量...");
		// 有视频数的分类结果集
		Map<Integer, Map<Integer,Long>> result = reportMapper.getClassifyMvCount();
		// 所有一级分类数据
		List<WebClassifyInfo> classifyInfos = movieClassifyMapper.findOneLevelClassifyList();
		// 组装成前台需要的结果对象
		ReportInfo reportInfo = new ReportInfo();
		List<String> column = new LinkedList<>();
		List<Long> row = new LinkedList<>();
		
		for (WebClassifyInfo cInfo : classifyInfos) {
			// 默认分类视频数为0，如果结果集中有数据则覆盖
			Long count = 0L;
			column.add(cInfo.getLabel());
			
			for (Entry<Integer, Map<Integer, Long>> entry : result.entrySet()) {
				if (entry.getKey() != null) {
					if (cInfo.getId().intValue() == entry.getKey().intValue()) {
						count = entry.getValue().get("total");
						break;
					}
				}
			}
			
			row.add(count);
		}
		
		reportInfo.setColumn(column);
		reportInfo.setRow(row);
		return reportInfo;
	}

}
