package com.yanda.entity;

import java.util.List;

public class ReportInfo {
	
	/**
	 * 行数组
	 */
	private List<String> column;
	
	/**
	 * 列值数组
	 */
	private List<Long> row;
	

	public List<String> getColumn() {
		return column;
	}

	public void setColumn(List<String> column) {
		this.column = column;
	}

	public List<Long> getRow() {
		return row;
	}

	public void setRow(List<Long> row) {
		this.row = row;
	}
	
	
}
