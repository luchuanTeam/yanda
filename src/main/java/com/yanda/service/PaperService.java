package com.yanda.service;

import com.yanda.entity.PageResult;
import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.entity.generated.PaperInfo;
import com.yanda.exception.DOPException;

public interface PaperService extends BaseService<PaperInfo, Integer> {
	
	/**
	 * 新增实体
	 * @param t
	 * @return
	 */
	public int save(PaperInfo paper, AttachmentInfo attch) throws DOPException;
	
	
	PageResult<PaperInfo> getList(int pageNum, int pageSize, String searchVal, Integer paperType);

}
