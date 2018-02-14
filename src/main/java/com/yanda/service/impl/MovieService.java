package com.yanda.service.impl;

import com.yanda.entity.PageResult;
import com.yanda.entity.generated.MovieInfo;
import com.yanda.service.IBaseService;

public interface MovieService extends IBaseService<MovieInfo> {

	PageResult<MovieInfo> findMovieListByClassify(int classifyId, String searchKey, String searchVal, String order,
			int pageNum, int pageSize);

}
