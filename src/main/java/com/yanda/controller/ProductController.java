package com.yanda.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yanda.entity.generated.ProductInfo;
import com.yanda.exception.DOPException;
import com.yanda.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/list")
	public Object getList(HttpServletRequest request) {
		return result(200, "success", productService.findAll());
	}
	
	@PostMapping("/upsert")
	public Object upsert(HttpServletRequest request, @RequestBody ProductInfo product) throws DOPException {
		if (product.getId() == null) {
			product.setCreateTime(new Date());
		}
		productService.upsertSelective(product);
		return result(200, "success");
	}
	
	@PostMapping("/delete/{id}")
	public Object delete(HttpServletRequest request, @PathVariable Integer id) throws DOPException {
		productService.deleteById(id);
		return result(200, "success");
	}
	
}
