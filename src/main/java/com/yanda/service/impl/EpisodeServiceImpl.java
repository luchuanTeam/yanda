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
import com.yanda.entity.generated.EpisodeInfo.Col;
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
	
	@CacheEvict(value = {"episodeList", "movieList"}, allEntries=true, beforeInvocation=true)
	@Transactional(rollbackFor={DOPException.class})
	@Override
	public void addEpisode(AttachmentInfo imgAttach, AttachmentInfo videoAttach, MovieInfo movieInfo, EpisodeInfo episodeInfo)
			throws DOPException {
		LOG.info("添加视频集，清空视频集缓存数据...");
		attachmentService.save(imgAttach);
		attachmentService.save(videoAttach);
		episodeInfo.setImgAppendixId(imgAttach.getAppendixId());
		episodeInfo.setMvAppendixId(videoAttach.getAppendixId());
		episodeInfo.setDuration(videoAttach.getDuration());
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
	public EpisodeDetailInfo findEpisodeDetailInfoById(Long episodeId) {
		LOG.info("根据视频集ID和集数查询的视频集将从数据库中获取...");
		return movieAttachmentMapper.findEpisodeDetailInfoById(episodeId);
	}
	
	@CacheEvict(value = {"episodeList", "movieList", "attach"}, allEntries=true, beforeInvocation=true)
	@Transactional(rollbackFor={DOPException.class})
	@Override
	public int deleteById(Long id) throws DOPException {
		LOG.info("删除视频集，清空视频集缓存数据...");
		EpisodeInfo episodeInfo = this.mapper.selectByPrimaryKey(id);
		MovieInfo mvInfo = movieService.selectById(episodeInfo.getMvId());
		int episodeCount = mvInfo.getEpisodeCount();
		if (episodeCount > 0) 
			episodeCount --;
		mvInfo.setEpisodeCount(episodeCount);
		movieService.update(mvInfo);
		attachmentService.deleteById(episodeInfo.getImgAppendixId());
		attachmentService.deleteById(episodeInfo.getMvAppendixId());
		return super.deleteById(id);
	}
	
	@CacheEvict(value = {"episodeList", "attach"}, allEntries=true, beforeInvocation=true)
	@Transactional(rollbackFor={DOPException.class, Exception.class})
	@Override
	public void updateEpisode(AttachmentInfo imgAttach, AttachmentInfo videoAttach, EpisodeInfo episodeInfo)
			throws DOPException {
		LOG.info("更新视频集，清空视频集缓存数据...");
		if (null != imgAttach) {
			attachmentService.save(imgAttach);
			episodeInfo.setImgAppendixId(imgAttach.getAppendixId());
		} 
		if (null != videoAttach) {
			attachmentService.save(videoAttach);
			episodeInfo.setMvAppendixId(imgAttach.getAppendixId());
			episodeInfo.setDuration(imgAttach.getDuration());
		}
		this.update(episodeInfo);
	}

	@Override
	public List<EpisodeInfo> getSimpleEpisodeByMvId(Long mvId) {
		EpisodeInfoExample example = new EpisodeInfoExample();
		example.createCriteria().andMvIdEqualTo(mvId);
		return this.mapper.selectByExampleSelective(example, Col.episodeId, Col.episodeId, Col.episodeName, Col.episodeNum, Col.vipType);
	}
	
	
}
