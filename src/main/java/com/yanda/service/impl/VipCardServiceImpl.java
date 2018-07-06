package com.yanda.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yanda.entity.PageResult;
import com.yanda.entity.generated.VipCardInfo;
import com.yanda.entity.generated.VipCardInfoExample;
import com.yanda.mapper.generated.VipCardInfoMapper;
import com.yanda.service.UserService;
import com.yanda.service.VipCardService;

@Service
public class VipCardServiceImpl extends BaseServiceImpl<VipCardInfoMapper, VipCardInfo, Integer>
		implements VipCardService {
	
	@Autowired
	private UserService userService;

	@Override
	public PageResult<VipCardInfo> getList(int pageNum, int pageSize, String searchVal) {
		
		LOG.info("会员卡数据列表将从数据库中获取...");
		Page<VipCardInfo> pageInfo = PageHelper.startPage(pageNum, pageSize);
		VipCardInfoExample VipCardInfoExample = new VipCardInfoExample();
		VipCardInfoExample.createCriteria().andCardNumLike("%" + searchVal + "%");
		VipCardInfoExample.setOrderByClause("update_time desc");
		mapper.selectByExample(VipCardInfoExample);
		PageResult<VipCardInfo> pageResult = new PageResult<>(pageInfo.getTotal(), pageInfo.getPages(),
				pageInfo.getPageSize(), pageInfo.getResult());
		
		return pageResult;
	}

	@Override
	public String getVipCardNum() {
		PageHelper.startPage(1, 1);
		VipCardInfoExample VipCardInfoExample = new VipCardInfoExample();
		VipCardInfoExample.setOrderByClause("create_time desc");
		List<VipCardInfo> vipCards = mapper.selectByExample(VipCardInfoExample);
		if (CollectionUtils.isEmpty(vipCards)) {
			return generateCardNum(1000);
		} else {
			int vipCard = vipCards.get(0).getCardId();
			return generateCardNum(vipCard+1);
		}
	}

	public static String generateCardNum(int vipId) {
		String cardNum = "";
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		String millis = String.valueOf(calendar.getTimeInMillis());
		if (month.length() < 2) {
			month = "0" + month;
		}
		if (day.length() < 2) {
			day = "0" + day;
		}
		millis = millis.substring(0, 2);
		cardNum = year + month + day + String.valueOf(vipId) + millis;
		return cardNum;
	}

	@Override
	public VipCardInfo findByCardNumAndPassword(String cardNum, String password) {
		VipCardInfoExample example = new VipCardInfoExample();
		example.createCriteria().andCardNumEqualTo(cardNum).andCardPasswordEqualTo(password);
		return mapper.selectOneByExample(example);
	}

	@Override
	public VipCardInfo findByUserId(Integer userId) {
		VipCardInfoExample example = new VipCardInfoExample();
		example.createCriteria().andUserIdEqualTo(userId);
		return mapper.selectOneByExample(example);
	}
	
	
	
//	@Transactional
//	@Override
//	public int save(VipCardInfo t) throws DOPException {
//		String userName = t.getCardNum();
//		String password = t.getCardPassword();
//		UserInfo user = new UserInfo();
//		user.setUserName(userName);
//		user.setNickName(userName);
//		user.setPassword(password);
//		user.setAvatar("http://www.yanda123.com/app/people.png");
//		userService.save(user);
//		return super.save(t);
//	}
//	
//	@Transactional
//	@Override
//	public int deleteById(Integer id) throws DOPException {
//		VipCardInfo vipCard = super.selectById(id);
//		String userName = vipCard.getCardNum();
//		userService.deleteByUserName(userName);
//		return super.deleteById(id);
//	}
//
//	@Override
//	public int update(VipCardInfo t) throws DOPException {
//		String userName = t.getCardNum();
//		Integer userId = t.getUserId();
//		UserInfo wxUser = userService.selectById(Long.valueOf(userId));
//		UserInfo vipUser = userService.findUserByUserName(userName);
//		vipUser.setNickName(wxUser.getNickName());
//		vipUser.setMobile(wxUser.getMobile());
//		vipUser.setSex(wxUser.getSex());
//		vipUser.setAvatar(wxUser.getAvatar());
//		return super.update(t);
//	}
	
	
}
