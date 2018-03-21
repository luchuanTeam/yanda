package com.yanda.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yanda.entity.PageResult;
import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.entity.generated.EpisodeInfo;
import com.yanda.entity.generated.EpisodeInfoExample;
import com.yanda.entity.generated.MovieInfo;
import com.yanda.exception.DOPException;
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
	
	@Override
	public PageResult<EpisodeInfo> list(int pageNum, int pageSize, Long mvId, String searchVal) {
		Page<EpisodeInfo> pageInfo = PageHelper.startPage(pageNum, pageSize);
		EpisodeInfoExample example = new EpisodeInfoExample();
		example.createCriteria().andMvIdEqualTo(mvId).andEpisodeNameLike("%" + searchVal + "%");
		example.setOrderByClause("update_time desc");
		mapper.selectByExample(example);
		PageResult<EpisodeInfo> pageResult = new PageResult<>(pageInfo.getTotal(), pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getResult());
		return pageResult;
	}
	
	@Transactional(rollbackFor={DOPException.class})
	@Override
	public void addEpisode(AttachmentInfo imgAttach, AttachmentInfo videoAttach, MovieInfo movieInfo, EpisodeInfo episodeInfo)
			throws DOPException {
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

}
