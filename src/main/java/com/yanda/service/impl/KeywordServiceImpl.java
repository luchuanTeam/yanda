package com.yanda.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.yanda.entity.generated.KeywordInfo;
import com.yanda.entity.generated.KeywordInfoExample;
import com.yanda.mapper.generated.KeywordInfoMapper;
import com.yanda.service.KeywordService;


@Service
public class KeywordServiceImpl extends BaseServiceImpl<KeywordInfoMapper, KeywordInfo, Integer> implements KeywordService {

	public List<KeywordInfo> queryDefaults() {
        KeywordInfoExample example = new KeywordInfoExample();
        example.or().andIsDefaultEqualTo(true).andIsDeletedEqualTo(false);
        return this.mapper.selectByExample(example);
    }

    public KeywordInfo queryDefault() {
        KeywordInfoExample example = new KeywordInfoExample();
        example.or().andIsDefaultEqualTo(true).andIsDeletedEqualTo(false);
        return this.mapper.selectOneByExample(example);
    }

    public List<KeywordInfo> queryHots() {
        KeywordInfoExample example = new KeywordInfoExample();
        example.or().andIsHotEqualTo(true).andIsDeletedEqualTo(false);
        return this.mapper.selectByExample(example);
    }

    public List<KeywordInfo> queryByKeyword(String keyword, Integer page, Integer size) {
        KeywordInfoExample example = new KeywordInfoExample();
        example.setDistinct(true);
        example.or().andKeywordLike("%" + keyword + "%").andIsDeletedEqualTo(false);
        PageHelper.startPage(page, size);
        return this.mapper.selectByExample(example);
    }

    public List<KeywordInfo> querySelective(String keyword, Integer page, Integer limit, String sort, String order) {
        KeywordInfoExample example = new KeywordInfoExample();
        KeywordInfoExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(keyword)) {
            criteria.andKeywordLike("%" + keyword + "%");
        }
        criteria.andIsDeletedEqualTo(false);

        PageHelper.startPage(page, limit);
        return this.mapper.selectByExample(example);
    }

    public int countSelective(String keyword, Integer page, Integer limit, String sort, String order) {
        KeywordInfoExample example = new KeywordInfoExample();
        KeywordInfoExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(keyword)) {
            criteria.andKeywordLike("%" + keyword + "%");
        }
        criteria.andIsDeletedEqualTo(false);

        PageHelper.startPage(page, limit);
        return (int)this.mapper.selectCountByExample(example);
    }

    @Override
    public int deleteById(Integer id) {
        KeywordInfo keywords = this.mapper.selectByPrimaryKey(id);
        if(keywords == null){
            return 0;
        }
        keywords.setIsDeleted(true);
        return this.mapper.updateByPrimaryKey(keywords);
    }

}
