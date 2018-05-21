package com.yanda.exception;

public class NullParamException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2585759107324467907L;
	
	/**
	 * 异常消息
	 */
	private String message;
	
	/**
	 * 响应状态码
	 */
	private int status = 400;
	
	

	public NullParamException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return "缺少" + message + "参数";
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
