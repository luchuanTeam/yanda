package com.yanda.service;

import java.util.List;

import com.yanda.entity.generated.KeywordInfo;

public interface KeywordService extends BaseService<KeywordInfo, Integer> {
	
    List<KeywordInfo> queryDefaults();

    KeywordInfo queryDefault();

    List<KeywordInfo> queryHots();
    
    List<KeywordInfo> queryByKeyword(String keyword, Integer page, Integer size);

    List<KeywordInfo> querySelective(String keyword, Integer page, Integer limit, String sort, String order);

    int countSelective(String keyword, Integer page, Integer limit, String sort, String order);

	
}
