package com.yanda.entity;

public enum FileType {
	
	IMG(10), VIDEO(20), ATTACH(30);
	
	private int value;

	private FileType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	
}		
