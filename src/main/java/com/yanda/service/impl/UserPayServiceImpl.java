package com.yanda.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.yanda.service.UserPayService;

@Service
public class UserPayServiceImpl implements UserPayService {

	@Override
	public String getPaySign(String str) {
		String md5Str = DigestUtils.md5Hex(str);
		return md5Str;
	}

}
