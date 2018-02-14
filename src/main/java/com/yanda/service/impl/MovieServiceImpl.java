package com.yanda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yanda.entity.PageResult;
import com.yanda.entity.generated.MovieInfo;
import com.yanda.mapper.MovieClassifyMapper;
import com.yanda.mapper.generated.MovieInfoMapper;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieInfoMapper movieInfoMapper;
	
	@Autowired
	private MovieClassifyMapper movieClassifyMapper;

	@Override
	public int addRecord(MovieInfo record) {
		return movieInfoMapper.insertSelective(record);
	}

	@Override
	public int deleteRecord(int recordId) {
		return movieInfoMapper.deleteByPrimaryKey(recordId);
	}

	@Override
	public int updateRecord(MovieInfo record) {
		return movieInfoMapper.updateByPrimaryKeySelective(record);
	}

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
