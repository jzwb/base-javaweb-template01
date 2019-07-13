package com.jzwb.common;

import java.io.Serializable;

/**
 * 身份信息
 */
public class Principal implements Serializable {

	//id
	private Long id;
	//用户名
	private String username;

	/**
	 * @param id       id
	 * @param username 用户名
	 */
	public Principal(Long id, String username) {
		this.id = id;
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return username;
	}

}