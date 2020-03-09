package com.edu.admin.server.page.table;

import java.io.Serializable;
import java.util.Map;

/**
 * 分页查询参数
 * 
 * @author 小威老师
 *
 */
public class PageTableRequest implements Serializable {

	private static final long serialVersionUID = 7328071045193618467L;

	private Integer offset;
	private Integer limit;
	private Integer currentPage;
	private Map<String, Object> params;

	private OrderByObject orderByObject;

	private String orderBy;

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public OrderByObject getOrderByObject() {
		return orderByObject;
	}

	public void setOrderByObject(OrderByObject orderByObject) {
		this.orderByObject = orderByObject;
	}
}
