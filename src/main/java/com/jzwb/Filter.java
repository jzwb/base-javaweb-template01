package com.jzwb;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * 筛选
 */
public class Filter implements Serializable {

	/**
	 * 运算符
	 */
	public enum Operator {

		eq,//等于
		ne,//不等于
		gt,//大于
		lt,//小于
		ge,//大于等于
		le,//小于等于
		like,//相似
		in,//包含
		isNull,//为Null
		isNotNull;//不为Null

		/**
		 * 根据string获取Operator
		 *
		 * @param value 值
		 * @return
		 */
		public static Operator fromString(String value) {
			return Operator.valueOf(value.toLowerCase());
		}
	}

	//默认是否忽略大小写
	private static final boolean DEFAULT_IGNORE_CASE = false;
	//属性
	private String property;
	//运算符
	private Operator operator;
	//值
	private Object value;
	//是否忽略大小写
	private Boolean ignoreCase = DEFAULT_IGNORE_CASE;

	public Filter() {
	}

	/**
	 * @param property 属性
	 * @param operator 运算符
	 * @param value    值
	 */
	public Filter(String property, Operator operator, Object value) {
		this.property = property;
		this.operator = operator;
		this.value = value;
	}

	/**
	 * @param property   属性
	 * @param operator   运算符
	 * @param value      值
	 * @param ignoreCase 忽略大小写
	 */
	public Filter(String property, Operator operator, Object value, boolean ignoreCase) {
		this.property = property;
		this.operator = operator;
		this.value = value;
		this.ignoreCase = ignoreCase;
	}

	/**
	 * 返回等于筛选
	 *
	 * @param property 属性
	 * @param value    值
	 * @return
	 */
	public static Filter eq(String property, Object value) {
		return new Filter(property, Operator.eq, value);
	}

	/**
	 * 返回等于筛选
	 *
	 * @param property   属性
	 * @param value      值
	 * @param ignoreCase 忽略大小写
	 * @return
	 */
	public static Filter eq(String property, Object value, boolean ignoreCase) {
		return new Filter(property, Operator.eq, value, ignoreCase);
	}

	/**
	 * 返回不等于筛选
	 *
	 * @param property 属性
	 * @param value    值
	 * @return
	 */
	public static Filter ne(String property, Object value) {
		return new Filter(property, Operator.ne, value);
	}

	/**
	 * 返回不等于筛选
	 *
	 * @param property   属性
	 * @param value      值
	 * @param ignoreCase 忽略大小写
	 * @return
	 */
	public static Filter ne(String property, Object value, boolean ignoreCase) {
		return new Filter(property, Operator.ne, value, ignoreCase);
	}

	/**
	 * 返回大于筛选
	 *
	 * @param property 属性
	 * @param value    值
	 * @return
	 */
	public static Filter gt(String property, Object value) {
		return new Filter(property, Operator.gt, value);
	}

	/**
	 * 返回小于筛选
	 *
	 * @param property 属性
	 * @param value    值
	 * @return
	 */
	public static Filter lt(String property, Object value) {
		return new Filter(property, Operator.lt, value);
	}

	/**
	 * 返回大于等于筛选
	 *
	 * @param property 属性
	 * @param value    值
	 * @return
	 */
	public static Filter ge(String property, Object value) {
		return new Filter(property, Operator.ge, value);
	}

	/**
	 * 返回小于等于筛选
	 *
	 * @param property 属性
	 * @param value    值
	 * @return
	 */
	public static Filter le(String property, Object value) {
		return new Filter(property, Operator.le, value);
	}

	/**
	 * 返回相似筛选
	 *
	 * @param property 属性
	 * @param value    值
	 * @return
	 */
	public static Filter like(String property, Object value) {
		return new Filter(property, Operator.like, value);
	}

	/**
	 * 返回包含筛选
	 *
	 * @param property 属性
	 * @param value    值
	 * @return
	 */
	public static Filter in(String property, Object value) {
		return new Filter(property, Operator.in, value);
	}

	/**
	 * 返回为Null筛选
	 *
	 * @param property 属性
	 * @return
	 */
	public static Filter isNull(String property) {
		return new Filter(property, Operator.isNull, null);
	}

	/**
	 * 返回不为Null筛选
	 *
	 * @param property 属性
	 * @return
	 */
	public static Filter isNotNull(String property) {
		return new Filter(property, Operator.isNotNull, null);
	}

	/**
	 * 返回忽略大小写筛选
	 *
	 * @return
	 */
	public Filter ignoreCase() {
		this.ignoreCase = true;
		return this;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Boolean getIgnoreCase() {
		return ignoreCase;
	}

	public void setIgnoreCase(Boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Filter other = (Filter) obj;
		return new EqualsBuilder().append(getProperty(), other.getProperty()).append(getOperator(), other.getOperator()).append(getValue(), other.getValue()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(getProperty()).append(getOperator()).append(getValue()).toHashCode();
	}
}