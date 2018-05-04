package com.yanda.service;

import java.util.List;

import com.yanda.entity.generated.UserSearchInfo;

public interface UserSearchService extends BaseService<UserSearchInfo, Integer> {
	
	public List<UserSearchInfo> queryByUid(int userId);

    public void deleteByUid(int userId);
}
