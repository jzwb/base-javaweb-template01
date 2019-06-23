package com.jzwb.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity - 测试
 */
@Entity
@Table(name = "t_test")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_test_sequence")
public class Test extends OrderEntity {

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}