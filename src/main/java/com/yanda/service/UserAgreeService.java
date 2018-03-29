package com.yanda.service;


import com.yanda.entity.generated.UserAgreeInfo;

public interface UserAgreeService extends BaseService<UserAgreeInfo, Long> {

	UserAgreeInfo selectByCommentIdAndUserId(Long commentId, Long userId);
}
