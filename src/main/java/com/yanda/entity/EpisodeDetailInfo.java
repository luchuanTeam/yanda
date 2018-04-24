package com.yanda.entity;

import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.entity.generated.EpisodeInfo;

/**
 * 包含
 * EpisodeDetailInfo.java
 * @author chenli
 * @time 2018年4月22日 下午5:15:28
 */
public class EpisodeDetailInfo extends EpisodeInfo {

	
	private static final long serialVersionUID = 8813161551961685560L;
	
	
	
	private AttachmentInfo mvAttach;

	public AttachmentInfo getMvAttach() {
		return mvAttach;
	}

	public void setMvAttach(AttachmentInfo mvAttach) {
		this.mvAttach = mvAttach;
	}


}
