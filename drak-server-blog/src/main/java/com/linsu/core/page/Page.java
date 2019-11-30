package com.linsu.core.page;

import javax.validation.constraints.Max;

/**
 * 分页模型
 * @author lisonglin
 * @date 2018年3月27日 下午7:10:11
 */
public class Page {

	/**
	 * 页码
	 */
	private Integer number = 1;
	
	/**
	 * 页大小
	 */
	@Max(value=200, message="limit error")
	private Integer limit = 20;
	
	/**
	 * 排序
	 */
	private String order;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Integer getOffset() {
		return (this.number-1)*this.limit;
	}
	
}
