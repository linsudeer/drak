package com.linsu.core.tip.bean;

import java.util.List;

/**
 * 响应消息
 * @author lisonglin
 * @date 2018年4月13日 下午1:15:00
 */
public class Tip {

	protected int code;
	protected String message;
	protected Object data;
	protected int total;
	public Tip() {}
	
	public Tip(int code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
		if(data != null && data instanceof List) {
			this.total = ((List<?>)data).size();
		}
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}
