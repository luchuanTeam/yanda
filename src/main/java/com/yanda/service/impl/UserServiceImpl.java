package com.yanda.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yanda.entity.PageResult;
import com.yanda.entity.UserDetailInfo;
import com.yanda.entity.generated.UserInfo;
import com.yanda.entity.generated.UserInfoExample;
import com.yanda.entity.generated.VipCardInfo;
import com.yanda.entity.generated.VipCardInfoExample;
import com.yanda.exception.DOPException;
import com.yanda.mapper.UserCustomMapper;
import com.yanda.mapper.generated.UserInfoMapper;
import com.yanda.mapper.generated.VipCardInfoMapper;
import com.yanda.service.UserService;
import com.yanda.util.Const;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserInfoMapper, UserInfo, Long> implements UserService {
	
	@Autowired
	private UserCustomMapper userCustomMapper;
	@Autowired
	private VipCardInfoMapper vipCardMapper;
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public UserInfo login(String userName, String password) {
		UserInfoExample example = new UserInfoExample();
		example.or().andUserNameEqualTo(userName).andPasswordEqualTo(password);
		example.or().andMobileEqualTo(userName).andPasswordEqualTo(password);
		UserInfo userInfo = mapper.selectOneByExample(example);
		if (userInfo == null) {
			VipCardInfoExample example2 = new VipCardInfoExample();
			example2.createCriteria().andCardNumEqualTo(userName);
			VipCardInfo vip = vipCardMapper.selectOneByExample(example2);
			if (null != vip && null != vip.getUserId()) {
				example.clear();
				example.createCriteria().andUserIdEqualTo(vip.getUserId());
				userInfo = mapper.selectOneByExample(example);
			}
		}
		return userInfo;
		
	}
	
	@CacheEvict(value = "userList", allEntries=true, beforeInvocation=true)
	@Override
	public void register(String userName, String password) throws DOPException {
		LOG.info("注册新用户，清空用户缓存数据...");
		UserInfo userInfo = new UserInfo();
		userInfo.setCreateTime(new Date());
		userInfo.setPassword(password);
		userInfo.setUpdateTime(new Date());
		userInfo.setUserName(userName);
		userInfo.setAvatar("http://www.yanda123.com/app/people.png");
		userInfo.setNickName(userName);
		userInfo.setStatus(1);
		userInfo.setSex(1);
		userInfo.setMobile("");
		this.save(userInfo);
	}

	@Override
	public boolean findUserNameIsExist(String userName) {
		UserInfoExample example = new UserInfoExample();
		example.createCriteria().andUserNameEqualTo(userName);
		UserInfo userInfo = mapper.selectOneByExample(example);
		if(userInfo == null) {
			return false;
		}
		return true;
	}
	
	@Cacheable(value = "userList")
	@Override
	public PageResult<UserInfo> list(int pageNum, int pageSize, String searchVal) {
		LOG.info("用户数据列表将从数据库中获取...");
		Page<UserInfo> pageInfo = PageHelper.startPage(pageNum, pageSize);
		UserInfoExample example = new UserInfoExample();
		example.or().andUserNameLike("%" + searchVal + "%");
		example.or().andNickNameLike("%" + searchVal + "%");
		mapper.selectByExample(example);
		PageResult<UserInfo> pageResult = new PageResult<>(pageInfo.getTotal(), pageInfo.getPages(),
				pageInfo.getPageSize(), pageInfo.getResult());
		return pageResult;
	}
	
	@CacheEvict(value = "userList", allEntries=true, beforeInvocation=true)
	@Override
	public int update(UserInfo t) throws DOPException {
		return super.update(t);
	}

	@Override
	public UserDetailInfo findUserDetailByUserId(int userId) {
		return userCustomMapper.findUserDetailByUserId(userId);
	}

	@Override
	public UserInfo findWechatIsExist(String openId) {
		UserInfoExample example = new UserInfoExample();
		example.createCriteria().andOpenIdEqualTo(openId);
		List<UserInfo> users = this.mapper.selectByExample(example);
		if (CollectionUtils.isEmpty(users))
			return null;
		return users.get(0);
	}

	@Override
	public int deleteByUserName(String userName) throws DOPException {
		UserInfoExample example = new UserInfoExample();
		example.createCriteria().andUserNameEqualTo(userName);
		return this.mapper.deleteByExample(example);
	}

	@Override
	public UserInfo findUserByUserName(String userName) throws DOPException {
		UserInfoExample example = new UserInfoExample();
		example.createCriteria().andUserNameEqualTo(userName);
		return this.mapper.selectOneByExample(example);
	}

	@Override
	public void clearLoginByUserId(Integer userId) {
		UserInfo user = this.mapper.selectByPrimaryKeySelective(userId, UserInfo.Col.userName);
		redisTemplate.opsForHash().put(Const.TOKEN_KEY_PRE, user.getUserName(), "");
	}

	
	
	
}
