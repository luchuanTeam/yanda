package com.yanda.controller;

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
import com.yanda.entity.generated.AttachmentInfo;
import com.yanda.entity.generated.BannerInfo;
import com.yanda.exception.DOPException;
import com.yanda.service.BannerService;
import com.yanda.util.StringUtil;


/**
 * 轮播图相关接口控制类
 * BannerController.java
 * @author chenli
 * @time 2018年3月7日 下午9:39:59
 */
@RestController
@RequestMapping(value = "/banner")
public class BannerController extends BaseController {

	@Autowired
	private BannerService bannerService;

	
	/**
	 * 获取轮播图列表数据
	 * @param request 请求体
	 * @param pageNum 页码
	 * @param pageSize 分页大小
	 * @param bannerDesc 轮播图描述
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JsonResult listBanners(HttpServletRequest request) {
		String pageNum = getValue(request, "pageNum", "1");
		String pageSize = getValue(request, "pageSize", "10");
		String bannerDesc = getNotEmptyValue(request, "bannerDesc");
		PageResult<BannerInfo> bannerInfos = bannerService.list(Integer.valueOf(pageNum), Integer.valueOf(pageSize),
				bannerDesc);
		return result(200, "success", bannerInfos);
	}
	
	/**
	 * 添加一张轮播图图
	 * @param request 请求体
	 * @param bannerInfo 轮播图实体对象
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JsonResult add(HttpServletRequest request, @RequestBody BannerInfo bannerInfo) {
		try {
			AttachmentInfo attachmentInfo = handleImgAttach(request);
			bannerInfo.setCreateTime(attachmentInfo.getCreateTime());
			bannerInfo.setUpdateTime(attachmentInfo.getCreateTime());
			bannerService.addBanner(attachmentInfo, bannerInfo);
			return result(200, "success");
		} catch (Exception e) {
			LOG.error("拷贝临时图片到发布路径异常", e);
			return result(-1, "发布图片失败！");
		}
	}
	
	/**
	 * 删除一张轮播图
	 * @param request 请求体
	 * @param id 轮播图实体id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public JsonResult delete(HttpServletRequest request, @PathVariable("id") Long id) {

		try {
			bannerService.deleteById(id);
			return result(200, "删除成功!");
		} catch (Exception e) {
			LOG.error("删除图片失败!", e);
			return result(-1, e.getMessage());
		}
	}
	
	/**
	 * 批量删除轮播图
	 * @param request 请求体
	 * @param ids 轮播图实体ids字符串以","拼接
	 * @return
	 */
	@RequestMapping(value = "/batchDelete/{ids}", method = RequestMethod.POST)
	public JsonResult batchDelete(HttpServletRequest request, @PathVariable("ids") String ids) {
		try {
			long[] idArr = StringUtil.stringToLongs(ids);
			for (long id : idArr) {
				bannerService.deleteById(id);
			}
			return result(200, "删除成功!");
		} catch (Exception e) {
			LOG.error("删除图片失败!", e);
			return result(-1, e.getMessage());
		}
	}
	
	/**
	 * 更新轮播图
	 * @param request 请求体
	 * @param bannerInfo 轮播图实体
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonResult update(HttpServletRequest request, @RequestBody BannerInfo bannerInfo) {
		bannerInfo.setUpdateTime(new Date());
		try {
			bannerService.update(bannerInfo);
			return result(200, "更新成功!");
		} catch (DOPException e) {
			return result(-1, "更新失败:" + e.getMessage());
		}
	}

}
