package com.jzwb.common;

import com.jzwb.common.Order.Direction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页
 */
public class Page<T> implements Serializable {

	//内容
	private final List<T> content = new ArrayList<>();
	//总记录数
	private final long total;
	//分页信息
	private final Pageable pageable;

	public Page() {
		this.total = 0L;
		this.pageable = new Pageable();
	}

	/**
	 * @param content  内容
	 * @param total    总记录数
	 * @param pageable 分页信息
	 */
	public Page(List<T> content, long total, Pageable pageable) {
		this.content.addAll(content);
		this.total = total;
		this.pageable = pageable;
	}

	public int getPageNumber() {
		return pageable.getPageNumber();
	}

	public int getPageSize() {
		return pageable.getPageSize();
	}

	public String getSearchProperty() {
		return pageable.getSearchProperty();
	}

	public String getSearchValue() {
		return pageable.getSearchValue();
	}

	public String getOrderProperty() {
		return pageable.getOrderProperty();
	}

	public Direction getOrderDirection() {
		return pageable.getOrderDirection();
	}

	public List<Order> getOrders() {
		return pageable.getOrders();
	}

	public List<Filter> getFilters() {
		return pageable.getFilters();
	}

	public int getTotalPages() {
		return (int) Math.ceil((double) getTotal() / (double) getPageSize());
	}

	public List<T> getContent() {
		return content;
	}

	public long getTotal() {
		return total;
	}

	public Pageable getPageable() {
		return pageable;
	}
}