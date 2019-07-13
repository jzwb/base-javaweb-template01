package com.jzwb.common;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * 排序
 */
public class Order implements Serializable {

	/**
	 * 方向
	 */
	public enum Direction {
		asc,//递增
		desc;//递减

		/**
		 * 根据string获取Direction
		 *
		 * @param value 值
		 * @return
		 */
		public static Direction fromString(String value) {
			return Direction.valueOf(value.toLowerCase());
		}
	}

	//默认方向
	private static final Direction DEFAULT_DIRECTION = Direction.desc;
	//属性
	private String property;
	//方向
	private Direction direction = DEFAULT_DIRECTION;

	public Order() {
	}

	/**
	 * @param property  属性
	 * @param direction 方向
	 */
	public Order(String property, Direction direction) {
		this.property = property;
		this.direction = direction;
	}

	/**
	 * 返回递增排序
	 *
	 * @param property 属性
	 * @return
	 */
	public static Order asc(String property) {
		return new Order(property, Direction.asc);
	}

	/**
	 * 返回递减排序
	 *
	 * @param property 属性
	 * @return
	 */
	public static Order desc(String property) {
		return new Order(property, Direction.desc);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (this == obj)
			return true;
		Order other = (Order) obj;
		return new EqualsBuilder().append(getProperty(), other.getProperty()).append(getDirection(), other.getDirection()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(getProperty()).append(getDirection()).toHashCode();
	}
}