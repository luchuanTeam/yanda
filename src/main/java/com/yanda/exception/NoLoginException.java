package com.yanda.exception;

public class NoLoginException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7064000400415752349L;
	
	/**
	 * 异常消息
	 */
	private String message = "用户未登录";
	
	/**
	 * 响应状态码
	 */
	private int status = 401;
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


}
