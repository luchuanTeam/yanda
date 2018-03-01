package com.yanda.service;

import com.yanda.entity.PageResult;
import com.yanda.entity.generated.MovieInfo;

public interface MovieService {

	PageResult<MovieInfo> findMovieListByClassify(int classifyId, String searchKey, String searchVal, String order,
			int pageNum, int pageSize);

}
