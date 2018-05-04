package com.yanda.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yanda.entity.ClassifyDetailInfo;
import com.yanda.entity.MovieDetailInfo;
import com.yanda.entity.PageResult;
import com.yanda.entity.WebClassifyInfo;
import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.entity.generated.ClassifyInfo;
import com.yanda.entity.generated.ClassifyInfoExample;
import com.yanda.entity.generated.MovieInfo;
import com.yanda.entity.generated.MovieInfo.Col;
import com.yanda.entity.generated.MovieInfoExample;
import com.yanda.entity.generated.MovieInfoExample.Criteria;
import com.yanda.exception.DOPException;
import com.yanda.mapper.MovieClassifyMapper;
import com.yanda.mapper.generated.ClassifyInfoMapper;
import com.yanda.mapper.generated.MovieInfoMapper;
import com.yanda.service.AttachmentService;
import com.yanda.service.MovieService;
import com.yanda.util.StringUtil;

@Service
public class MovieServiceImpl extends BaseServiceImpl<MovieInfoMapper, MovieInfo, Long> implements MovieService {
	
	@Autowired
	private MovieClassifyMapper movieClassifyMapper;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private ClassifyInfoMapper classifyInfoMapper;

	
	@Override
	public PageResult<MovieInfo> findMovieListByClassify(int classifyId, String searchKey, String searchVal, String order,
			int pageNum, int pageSize) {
		
		Page<MovieInfo> pageInfo = PageHelper.startPage(pageNum, pageSize);
		movieClassifyMapper.findMovieListByClassify(classifyId, searchKey, searchVal, order);
		PageResult<MovieInfo> pageResult = new PageResult<>(pageInfo.getTotal(), pageInfo.getPages(),
				pageInfo.getPageSize(), pageInfo.getResult());
		
		return pageResult;
	}
	
	@Override
	public String findMovieClassifyFullName(int classifyId) {
		// 此处根据id获取到该分类信息以及该分类的父级分类信息
		ClassifyDetailInfo detailInfo = movieClassifyMapper.findClassifyInfo(classifyId);
		String fullName = detailInfo.getClassifyName();
		if (detailInfo.getParent() != null) {
			fullName = detailInfo.getParent().getClassifyName() + "/" + fullName;
			// 若父级分类不是最顶层的分类继续递归获取上一级分类，否则返回分类全路径
			if (detailInfo.getParent().getParentId() != 0) {
				return findMovieClassifyFullName(detailInfo.getParent().getParentId());
			} else {
				return fullName;
			}
		} else {
			return fullName;
		}
	}
	
	@Cacheable(value = "movieList")
	@Override
	public PageResult<MovieDetailInfo> list(int pageNum, int pageSize, String searchVal) {
		LOG.info("根据分页数和视频名称查询的视频数据列表将从数据库中获取...");
		Page<MovieInfo> pageInfo = PageHelper.startPage(pageNum, pageSize);
		MovieInfoExample example = new MovieInfoExample();
		example.createCriteria().andMvNameLike("%" + searchVal + "%");
		example.setOrderByClause("update_time desc");
		List<MovieInfo> mvList = mapper.selectByExample(example);
		List<MovieDetailInfo> mDetailInfos = this.getMovieDetailInfos(mvList);
		PageResult<MovieDetailInfo> pageResult = new PageResult<>(pageInfo.getTotal(), pageInfo.getPages(),
				pageInfo.getPageSize(), mDetailInfos);
		return pageResult;
	}
	
	@Override
	public List<MovieDetailInfo> getMovieDetailInfos(List<MovieInfo> mvList) {
		List<MovieDetailInfo> mDetailInfos = new LinkedList<>();
		for (MovieInfo mvInfo : mvList) {
			
			MovieDetailInfo detailInfo = new MovieDetailInfo(mvInfo);
			if (StringUtil.isEmpty(detailInfo.getClassifyName())) {
				//在数据库表冗余了分类名称字段，不从库进行查询，对历史数据进行查库
				detailInfo.setClassifyName(this.findMovieClassifyFullName(mvInfo.getClassifyId()));
			}
			mDetailInfos.add(detailInfo);
		}
		return mDetailInfos;
	}
	
	@CacheEvict(value = {"movieList", "report"}, allEntries=true, beforeInvocation=true)
	@Transactional(rollbackFor={DOPException.class})
	@Override
	public void addMovie(MovieInfo movieInfo, AttachmentInfo attachmentInfo) throws DOPException {
		LOG.info("添加视频，清空视频缓存数据...");
		attachmentService.save(attachmentInfo);
		movieInfo.setImgAppendixId(attachmentInfo.getAppendixId());
		this.save(movieInfo);
	}
	
	@CacheEvict(value = "movieList", allEntries=true, beforeInvocation=true)
	@Override
	public void updateMovie(MovieInfo movieInfo, AttachmentInfo attachmentInfo) throws DOPException {
		LOG.info("更新视频，清空视频缓存数据...");
		if (null != attachmentInfo) {
			attachmentService.save(attachmentInfo);
			movieInfo.setImgAppendixId(attachmentInfo.getAppendixId());
		} 
		this.update(movieInfo);
	}
	
	@CacheEvict(value = {"movieList", "report"}, allEntries=true, beforeInvocation=true)
	@Transactional(rollbackFor={DOPException.class})
	@Override
	public int deleteById(Long id) throws DOPException {
		LOG.info("删除视频，清空视频缓存数据...");
		MovieInfo movieInfo = this.mapper.selectByPrimaryKey(id);
		if (movieInfo.getEpisodeCount() > 0) {
			throw new DOPException("视频下还有视频集，不能删除该视频");
		}
		attachmentService.deleteById(movieInfo.getImgAppendixId());
		try {
			if (StringUtil.isNotEmpty(movieInfo.getMvPath())) {
				FileUtils.deleteDirectory(new File(movieInfo.getMvPath()));
			}
		} catch (IOException e) {
			String tips = "删除视频文件目录失败";
			LOG.error(tips, e);
			throw new DOPException(tips);
		}
		return super.deleteById(id);
	}
	
	@Cacheable(value = "classifyList")
	@Override
	public List<WebClassifyInfo> findOneLevelClassifyList() {
		LOG.info("分类列表将从数据库中获取...");
		return movieClassifyMapper.findOneLevelClassifyList();
	}
	
	@Cacheable(value = "classifyList")
	@Override
	public List<WebClassifyInfo> findClassifyList(int parentId) {
		LOG.info("根据父分类ID获取的分类列表将从数据库中获取...");
		return movieClassifyMapper.findClassifyList(parentId);
	}
	
	@Cacheable(value = "movieList")
	@Override
	public PageResult<MovieDetailInfo> getPubMovies(int classifyId, int pageNum, int pageSize) {
		LOG.info("根据分页数和分类ID查询的视频数据列表将从数据库中获取...");
		Page<MovieInfo> pageInfo = PageHelper.startPage(pageNum, pageSize);
		MovieInfoExample example = new MovieInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andEpisodeCountGreaterThanOrEqualTo(1).andIsPublicEqualTo(true);
		if (0 != classifyId) {
			criteria.andClassifyIdEqualTo(classifyId);
		}
		example.setOrderByClause("update_time desc");
		List<MovieInfo> mvList = mapper.selectByExample(example);
		List<MovieDetailInfo> mDetailInfos = this.getMovieDetailInfos(mvList);
		PageResult<MovieDetailInfo> pageResult = new PageResult<>(pageInfo.getTotal(), pageInfo.getPages(),
				pageInfo.getPageSize(), mDetailInfos);
		return pageResult;
	}

	@Override
	public List<MovieInfo> findMovieByNameOrIntro(String searchVal) {
		MovieInfoExample example = new MovieInfoExample();
		example.or().andMvNameLike(searchVal).andIsPublicEqualTo(true);
		example.or().andMvIntroLike(searchVal).andIsPublicEqualTo(true);
		return this.mapper.selectByExample(example);
	}

	@Override
	public PageResult<MovieInfo> querySelective(Integer classifyId, String keyword, Integer pageNum, Integer pageSize,
			String sortWithOrder) {
		
		Page<MovieInfo> page = PageHelper.startPage(pageNum, pageSize);
		
		MovieInfoExample example = new MovieInfoExample();
        Criteria criteria = example.createCriteria();

        if(classifyId != null && classifyId != 0) {
            criteria.andClassifyIdEqualTo(classifyId);
        }
        if(keyword != null){
            criteria.andMvNameLike("%" + keyword + "%");
        }
        criteria.andIsPublicEqualTo(true);

        if(sortWithOrder != null){
            example.setOrderByClause(sortWithOrder);
        }
        
        this.mapper.selectByExample(example);
        
        PageResult<MovieInfo> pageResult = new PageResult<>();
        pageResult.setList(page.getResult());
        pageResult.setTotal(page.getTotal());

        return pageResult;

	}

	@Override
	public List<Integer> getClassifyIds(String keyword) {
		MovieInfoExample example = new MovieInfoExample();
        Criteria criteria = example.createCriteria();
        if(keyword != null){
            criteria.andMvNameLike("%" + keyword + "%");
        }
        criteria.andIsPublicEqualTo(true);
        
        List<MovieInfo> mvList = this.mapper.selectByExampleSelective(example, Col.classifyId);
        List<Integer> idList = new ArrayList<Integer>();
        for(MovieInfo mv : mvList){
        	idList.add(mv.getClassifyId());
        }
		return idList;
	}

	@Override
	public List<ClassifyInfo> findClassifyListByIds(List<Integer> classifyIds) {

		ClassifyInfoExample example = new ClassifyInfoExample();
        example.or().andClassifyIdIn(classifyIds);
		return classifyInfoMapper.selectByExampleSelective(example, com.yanda.entity.generated.ClassifyInfo.Col.classifyId, com.yanda.entity.generated.ClassifyInfo.Col.classifyName);
	}


}
