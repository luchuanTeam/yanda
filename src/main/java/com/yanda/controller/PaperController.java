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
import com.yanda.entity.generated.PaperInfo;
import com.yanda.exception.DOPException;
import com.yanda.service.PaperService;

@RestController
@RequestMapping(value = "/paper")
public class PaperController extends BaseController {
	
	@Autowired
	private PaperService paperService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JsonResult listBanners(HttpServletRequest request) throws DOPException {
		String pageNum = getValue(request, "pageNum", "1");
		String pageSize = getValue(request, "pageSize", "10");
		String searchVal = getNotEmptyValue(request, "searchVal");
		String paperType = getValue(request, "paperType", "1");
		PageResult<PaperInfo> papers = paperService.getList(Integer.valueOf(pageNum), Integer.valueOf(pageSize),
				searchVal, Integer.valueOf(paperType));
		return result(200, "success", papers);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JsonResult add(HttpServletRequest request, @RequestBody PaperInfo Paper) throws DOPException {
		AttachmentInfo attach = handleAttach(request);
		Date date = new Date();
		Paper.setCreateTime(date);
		Paper.setUpdateTime(date);
		paperService.save(Paper, attach);
		return result(200, "success");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonResult update(HttpServletRequest request, @RequestBody PaperInfo Paper) throws DOPException {
		Paper.setUpdateTime(new Date());
		paperService.update(Paper);
		return result(200, "success");
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public JsonResult delete(HttpServletRequest request, @PathVariable("id") Integer id) throws DOPException {
		paperService.deleteById(id);
		return result(200, "删除成功!");

	}
	
}
