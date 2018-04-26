package com.yanda.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yanda.entity.EpisodeDetailInfo;
import com.yanda.entity.PageResult;
import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.entity.generated.EpisodeInfo;
import com.yanda.entity.generated.EpisodeInfoExample;
import com.yanda.entity.generated.MovieInfo;
import com.yanda.exception.DOPException;
import com.yanda.mapper.MovieAttachmentMapper;
import com.yanda.mapper.generated.EpisodeInfoMapper;
import com.yanda.service.AttachmentService;
import com.yanda.service.EpisodeService;
import com.yanda.service.MovieService;

@Service
public class EpisodeServiceImpl extends BaseServiceImpl<EpisodeInfoMapper, EpisodeInfo, Long> implements EpisodeService {
	
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private MovieService movieService;
	@Autowired
	private MovieAttachmentMapper movieAttachmentMapper;
	
	@Cacheable(value = "episodeList")
	@Override
	public PageResult<EpisodeInfo> list(int pageNum, int pageSize, Long mvId, String searchVal) {
		LOG.info("根据分页数和视频集名称查询的视频集数据列表将从数据库中获取...");
		Page<EpisodeInfo> pageInfo = PageHelper.startPage(pageNum, pageSize);
		EpisodeInfoExample example = new EpisodeInfoExample();
		example.createCriteria().andMvIdEqualTo(mvId).andEpisodeNameLike("%" + searchVal + "%");
		example.setOrderByClause("update_time desc");
		mapper.selectByExample(example);
		PageResult<EpisodeInfo> pageResult = new PageResult<>(pageInfo.getTotal(), pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getResult());
		return pageResult;
	}
	
	@CacheEvict(value = "episodeList", allEntries=true, beforeInvocation=true)
	@Transactional(rollbackFor={DOPException.class})
	@Override
	public void addEpisode(AttachmentInfo imgAttach, AttachmentInfo videoAttach, MovieInfo movieInfo, EpisodeInfo episodeInfo)
			throws DOPException {
		LOG.info("添加视频集，清空视频集缓存数据...");
		attachmentService.save(imgAttach);
		attachmentService.save(videoAttach);
		episodeInfo.setImgAppendixId(imgAttach.getAppendixId());
		episodeInfo.setMvAppendixId(videoAttach.getAppendixId());
		movieService.update(movieInfo);
		this.save(episodeInfo);
	}

	@Override
	public EpisodeInfo selectByMvIdAndEpisodeNum(Long mvId, int episodeNum) {
		EpisodeInfoExample example = new EpisodeInfoExample();
		example.createCriteria().andMvIdEqualTo(mvId).andEpisodeNumEqualTo(episodeNum);
		List<EpisodeInfo> episodeInfo = mapper.selectByExample(example);
		if (CollectionUtils.isEmpty(episodeInfo))
			return null;
		return episodeInfo.get(0);
	}
	
	@Cacheable(value = "episodeList")
	@Override
	public EpisodeDetailInfo findEpisodeDetailInfoByMvIdAndNum(Long mvId, int episodeNum) {
		LOG.info("根据视频集ID和集数查询的视频集将从数据库中获取...");
		return movieAttachmentMapper.findEpisodeDetailInfoByMvIdAndNum(mvId, episodeNum);
	}
	
	@CacheEvict(value = "episodeList", allEntries=true, beforeInvocation=true)
	@Transactional(rollbackFor={DOPException.class})
	@Override
	public int deleteById(Long id) throws DOPException {
		LOG.info("删除视频集，清空视频集缓存数据...");
		EpisodeInfo episodeInfo = this.mapper.selectByPrimaryKey(id);
		attachmentService.deleteById(episodeInfo.getImgAppendixId());
		attachmentService.deleteById(episodeInfo.getMvAppendixId());
		return super.deleteById(id);
	}
	

}
