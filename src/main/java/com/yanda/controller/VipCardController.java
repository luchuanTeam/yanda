package com.yanda.controller;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yanda.entity.JsonResult;
import com.yanda.entity.PageResult;
import com.yanda.entity.generated.VipCardInfo;
import com.yanda.exception.DOPException;
import com.yanda.service.VipCardService;
import com.yanda.util.StringUtil;

@RestController
@RequestMapping(value = "/vip")
public class VipCardController extends BaseController {
	
	@Autowired
	private VipCardService vipCardService;
	
	/**
	 * 获取会员卡列表数据
	 * @param request 请求体
	 * @param pageNum 页码
	 * @param pageSize 分页大小
	 * @param cardNum 会员卡描述
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JsonResult listBanners(HttpServletRequest request) {
		String pageNum = getValue(request, "pageNum", "1");
		String pageSize = getValue(request, "pageSize", "10");
		String cardNum = getNotEmptyValue(request, "cardNum");
		PageResult<VipCardInfo> VipCardInfos = vipCardService.getList(Integer.valueOf(pageNum), Integer.valueOf(pageSize),
				cardNum);
		return result(200, "success", VipCardInfos);
	}
	
	/**
	 * 添加一张会员卡图
	 * @param request 请求体
	 * @param VipCardInfo 会员卡实体对象
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JsonResult add(HttpServletRequest request, @RequestBody VipCardInfo VipCardInfo) {
		Integer month = VipCardInfo.getPurchaseMonths();
		if (null == month)
			return result(-1, "购买月数不能为空");
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.MONTH, month);
		VipCardInfo.setCreateTime(now);
		VipCardInfo.setUpdateTime(now);
		VipCardInfo.setExpTime(calendar.getTime());
		try {
			vipCardService.save(VipCardInfo);
			return result(200, "success");
		} catch (Exception e) {
			LOG.error("添加会员卡异常", e);
			return result(-1, "添加失败！");
		}
	}
	
	@RequestMapping(value = "/getVipCardNum", method = RequestMethod.GET)
	public String geVipCardNum(HttpServletRequest request) {
		return vipCardService.getVipCardNum();
	}
	
	/**
	 * 删除一张会员卡
	 * @param request 请求体
	 * @param id 会员卡实体id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public JsonResult delete(HttpServletRequest request, @PathVariable("id") Integer id) {

		try {
			vipCardService.deleteById(id);
			return result(200, "删除成功!");
		} catch (Exception e) {
			LOG.error("删除失败!", e);
			return result(-1, e.getMessage());
		}
	}
	
	/**
	 * 批量删除会员卡
	 * @param request 请求体
	 * @param ids 会员卡实体ids字符串以","拼接
	 * @return
	 */
	@RequestMapping(value = "/batchDelete/{ids}", method = RequestMethod.POST)
	public JsonResult batchDelete(HttpServletRequest request, @PathVariable("ids") String ids) {
		try {
			int[] idArr = StringUtil.stringToInts(ids);
			for (int id : idArr) {
				vipCardService.deleteById(id);
			}
			return result(200, "删除成功!");
		} catch (Exception e) {
			LOG.error("删除失败!", e);
			return result(-1, e.getMessage());
		}
	}
	
	/**
	 * 续费会员卡
	 * @param request 请求体
	 * @param VipCardInfo 会员卡实体
	 * @return
	 * @throws DOPException 
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonResult update(HttpServletRequest request, @RequestBody VipCardInfo VipCardInfo) throws DOPException {
		String addMonth = getNotEmptyValue(request, "addMonth");
		if (StringUtil.isNotEmpty(addMonth)) {
			int addMonthVal = Integer.valueOf(addMonth);
			VipCardInfo.setPurchaseMonths(VipCardInfo.getPurchaseMonths() + addMonthVal);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(VipCardInfo.getExpTime());
			calendar.add(Calendar.MONTH, addMonthVal);
			VipCardInfo.setExpTime(calendar.getTime());
		}
		VipCardInfo.setUpdateTime(new Date());
		vipCardService.update(VipCardInfo);
		return result(200, "更新成功!");

	}
	
	/**
	 * 绑定会员卡持卡人
	 * @param request 请求体
	 * @param VipCardInfo 会员卡实体
	 * @return
	 * @throws DOPException 
	 */
	@RequestMapping(value = "/bind", method = RequestMethod.POST)
	public JsonResult bind(HttpServletRequest request, @RequestBody VipCardInfo VipCardInfo) throws DOPException {
		Integer userId = VipCardInfo.getUserId();
		if (null == userId)
			return result(-1, "绑定的用户为空");
		VipCardInfo.setUpdateTime(new Date());
		vipCardService.update(VipCardInfo);
		return result(200, "绑定成功!");
	}
	
	/**
	 * 通过会员卡号绑定用户
	 * @param request
	 * @param VipCardInfo
	 * @return
	 * @throws DOPException
	 */
	@RequestMapping(value = "/bindByCardNum", method = RequestMethod.POST)
	public JsonResult bindByCardNum(HttpServletRequest request) throws DOPException {
		String cardNum = getNotEmptyValue(request, "cardNum");
		if (StringUtil.isEmpty(cardNum))
			return result(-1, "卡号为空");
		String cardPassword = getNotEmptyValue(request, "cardPassword");
		if (StringUtil.isEmpty(cardPassword))
			return result(-1, "密码为空");
		String userId = getNotEmptyValue(request, "userId");
		if (StringUtil.isEmpty(userId))
			return result(-1, "绑定的用户为空");
		
		VipCardInfo vipCard = vipCardService.findByCardNumAndPassword(cardNum, cardPassword);
		if (null == vipCard)
			return result(-1, "卡号或密码错误");
		if (null != vipCard.getUserId())
			return result(-1, "该会员卡已被绑定");
		vipCard.setUserId(Integer.valueOf(userId));
		vipCard.setUpdateTime(new Date());
		vipCardService.upsertSelective(vipCard);
		return result(200, "绑定成功!");
	}
}
