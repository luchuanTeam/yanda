package com.yanda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yanda.entity.PageResult;
import com.yanda.entity.generated.MovieInfo;
import com.yanda.mapper.MovieClassifyMapper;
import com.yanda.mapper.generated.MovieInfoMapper;
import com.yanda.service.BaseService;
import com.yanda.service.MovieService;

@Service
public class MovieServiceImpl extends BaseService<MovieInfoMapper ,MovieInfo, Long> implements MovieService {
	
	@Autowired
	private MovieClassifyMapper movieClassifyMapper;

	@Override
	public PageResult<MovieInfo> list(int pageNum, int pageSize, String searchVal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult<MovieInfo> findMovieListByClassify(int classifyId, String searchKey, String searchVal, String order,
			int pageNum, int pageSize) {
		Page<MovieInfo> pageInfo = PageHelper.startPage(pageNum, pageSize);
		movieClassifyMapper.findMovieListByClassify(classifyId, searchKey, searchVal, order);
		PageResult<MovieInfo> pageResult = new PageResult<>(pageInfo.getTotal(), pageInfo.getPages(),
				pageInfo.getPageSize(), pageInfo.getResult());
		
		return pageResult;
	}

}
