package com.yanda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yanda.entity.PageResult;
import com.yanda.entity.generated.ClassifyInfo;
import com.yanda.entity.generated.ClassifyInfoExample;
import com.yanda.entity.generated.MovieInfoExample;
import com.yanda.exception.DOPException;
import com.yanda.mapper.generated.ClassifyInfoMapper;
import com.yanda.mapper.generated.MovieInfoMapper;
import com.yanda.service.ClassifyService;

@Service
public class ClassifyServiceImpl extends BaseServiceImpl<ClassifyInfoMapper, ClassifyInfo, Integer> implements ClassifyService {
	
	@Autowired
	private MovieInfoMapper movieInfoMapper;
	
	@Cacheable(value = "classifyList")
	@Override
	public PageResult<ClassifyInfo> list(int pageNum, int pageSize, String searchVal) {						
		
		Page<ClassifyInfo> pageInfo = PageHelper.startPage(pageNum, pageSize);
		ClassifyInfoExample example = new ClassifyInfoExample();
		example.or().andClassifyNameLike("%" + searchVal + "%").andParentIdEqualTo(0);
		example.setOrderByClause("classify_order asc");
		this.mapper.selectByExample(example);
		PageResult<ClassifyInfo> pageResult = new PageResult<>();
		pageResult.setList(pageInfo.getResult());
		pageResult.setTotal(pageInfo.getTotal());
		return pageResult;
	}
	
	@Cacheable(value = "classifyList")
	@Override
	public PageResult<ClassifyInfo> getClassifyById(int pageNum, int pageSize, Integer parentId, String searchVal) {
		Page<ClassifyInfo> pageInfo = PageHelper.startPage(pageNum, pageSize);
		ClassifyInfoExample example = new ClassifyInfoExample();
		example.or().andClassifyNameLike("%" + searchVal + "%").andParentIdEqualTo(parentId);
		example.setOrderByClause("classify_order asc");
		this.mapper.selectByExample(example);
		PageResult<ClassifyInfo> pageResult = new PageResult<>();
		pageResult.setList(pageInfo.getResult());
		pageResult.setTotal(pageInfo.getTotal());
		return pageResult;
	}

	@Override
	public int deleteById(Integer id) throws DOPException {
		MovieInfoExample example = new MovieInfoExample();
		example.createCriteria().andClassifyIdEqualTo(id);
		int count = movieInfoMapper.selectCountByExample(example);
		if (count > 0) {
			throw new DOPException("该分类下存在视频。不能删除该分类");
		} else {
			return super.deleteById(id);
		}
	}
	
	

}
