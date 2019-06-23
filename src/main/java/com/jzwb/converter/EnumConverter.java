package com.jzwb.converter;

import org.apache.commons.beanutils.converters.AbstractConverter;

/**
 * Converter - 枚举
 */
public class EnumConverter extends AbstractConverter {

	/**
	 * 枚举类型
	 */
	private final Class<?> enumClass;

	public EnumConverter(Class<?> enumClass) {
		this(enumClass, null);
	}

	public EnumConverter(Class<?> enumClass, Object defaultValue) {
		super(defaultValue);
		this.enumClass = enumClass;
	}

	/**
	 * 获取默认类型
	 *
	 * @return
	 */
	@Override
	protected Class<?> getDefaultType() {
		return this.enumClass;
	}

	/**
	 * 转换为枚举对象
	 *
	 * @param type  类型
	 * @param value 值
	 * @return
	 */
	protected Object convertToType(Class type, Object value) {
		String stringValue = value.toString().trim();
		return Enum.valueOf(type, stringValue);
	}

	/**
	 * 转换为字符串
	 *
	 * @param value 值
	 * @return
	 */
	protected String convertToString(Object value) {
		return value.toString();
	}
}