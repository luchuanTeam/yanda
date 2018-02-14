package com.yanda.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yanda.entity.PageResult;
import com.yanda.entity.generated.BannerInfo;
import com.yanda.entity.generated.BannerInfoExample;
import com.yanda.mapper.generated.BannerInfoMapper;
import com.yanda.service.IBaseService;
import com.yanda.util.FileUtil;

@Service
public class BannerServiceImpl implements IBaseService<BannerInfo> {
	
	@Autowired
	private BannerInfoMapper bannerInfoMapper;
	
	@Value("${web.uploadPath}")
	private String pubFilePath;

	@Override
	public int addRecord(BannerInfo record) {
		return bannerInfoMapper.insertSelective(record);
	}

	@Override
	public int deleteRecord(int recordId) {
		BannerInfo bannerInfo = bannerInfoMapper.selectByPrimaryKey(recordId);
		String imgUrl = bannerInfo.getImgUrl();
		deleteImgFile(imgUrl);
		return bannerInfoMapper.deleteByPrimaryKey(recordId);
	}

	@Override
	public int updateRecord(BannerInfo record) {
		return bannerInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public PageResult<BannerInfo> list(int pageNum, int pageSize, String searchVal) {
		Page<BannerInfo> pageInfo = PageHelper.startPage(pageNum, pageSize);
		BannerInfoExample bannerInfoExample = new BannerInfoExample();
		bannerInfoExample.createCriteria().andImgDescLike("%"+searchVal+"%");
		bannerInfoExample.setOrderByClause("update_time desc");
		bannerInfoMapper.selectByExample(bannerInfoExample);
		PageResult<BannerInfo> pageResult = new PageResult<>(pageInfo.getTotal(), pageInfo.getPages(),
				pageInfo.getPageSize(), pageInfo.getResult());
		return pageResult;
	}
	
	private void deleteImgFile(String imgUrl) {
		String fileName = FileUtil.getFileName(imgUrl);
		File imgFile = new File(pubFilePath, fileName);
		if (imgFile.exists()) {
			imgFile.delete();
		}
	}

}
