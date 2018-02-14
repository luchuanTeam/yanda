package com.yanda.entity;

import java.util.List;

public class PageResult<E> {
	/**
	 * 总数
	 */
	private long total;
	
	/**
	 * 页码
	 */
	private Integer pageNum;
	
	/**
	 * 分页数
	 */
	private Integer pageSize;
	
	public PageResult(long total, Integer pageNum, Integer pageSize, List<E> list) {
		super();
		this.total = total;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.list = list;
	}

	/**
	 * 列表
	 */
	private List<E> list;
	

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	
	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}
	
	
}
