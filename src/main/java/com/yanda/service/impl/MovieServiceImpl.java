package com.yanda.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yanda.entity.ClassifyDetailInfo;
import com.yanda.entity.MovieDetailInfo;
import com.yanda.entity.PageResult;
import com.yanda.entity.WebClassifyInfo;
import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.entity.generated.MovieInfo;
import com.yanda.entity.generated.MovieInfoExample;
import com.yanda.entity.generated.MovieInfoExample.Criteria;
import com.yanda.exception.DOPException;
import com.yanda.mapper.MovieClassifyMapper;
import com.yanda.mapper.generated.MovieInfoMapper;
import com.yanda.service.AttachmentService;
import com.yanda.service.MovieService;

@Service
public class MovieServiceImpl extends BaseServiceImpl<MovieInfoMapper ,MovieInfo, Long> implements MovieService {
	
	@Autowired
	private MovieClassifyMapper movieClassifyMapper;
	@Autowired
	private AttachmentService attachmentService;


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

	@Override
	public PageResult<MovieDetailInfo> list(int pageNum, int pageSize, String searchVal) {
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
			detailInfo.setClassifyName(this.findMovieClassifyFullName(mvInfo.getClassifyId()));
			mDetailInfos.add(detailInfo);
		}
		return mDetailInfos;
	}
	
	@Transactional(rollbackFor={DOPException.class})
	@Override
	public void addMovie(MovieInfo movieInfo, AttachmentInfo attachmentInfo) throws DOPException {
		attachmentService.save(attachmentInfo);
		movieInfo.setImgAppendixId(attachmentInfo.getAppendixId());
		this.save(movieInfo);
	}
	
	@Transactional(rollbackFor={DOPException.class})
	@Override
	public int deleteById(Long id) throws DOPException {
		MovieInfo movieInfo = this.mapper.selectByPrimaryKey(id);
		if (movieInfo.getEpisodeCount() > 0) {
			throw new DOPException("视频下还有视频集，不能删除该视频");
		}
		attachmentService.deleteById(movieInfo.getImgAppendixId());
		try {
			FileUtils.deleteDirectory(new File(movieInfo.getMvPath()));
		} catch (IOException e) {
			String tips = "删除视频文件目录失败";
			LOG.error(tips, e);
			throw new DOPException(tips);
		}
		return super.deleteById(id);
	}

	@Override
	public List<WebClassifyInfo> findOneLevelClassifyList() {
		return movieClassifyMapper.findOneLevelClassifyList();
	}

	@Override
	public List<WebClassifyInfo> findClassifyList(int parentId) {
		return movieClassifyMapper.findClassifyList(parentId);
	}

	@Override
	public PageResult<MovieDetailInfo> getPubMovies(int classifyId, int pageNum, int pageSize) {
		Page<MovieInfo> pageInfo = PageHelper.startPage(pageNum, pageSize);
		MovieInfoExample example = new MovieInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andEpisodeCountGreaterThanOrEqualTo(1);
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

}
