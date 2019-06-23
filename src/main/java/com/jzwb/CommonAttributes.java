package com.jzwb;

/**
 * 公共参数
 */
public final class CommonAttributes {

	//日期格式配比
	public static final String[] DATE_PATTERNS = new String[]{"yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss"};

	//base-javaweb-template01.xml文件路径
	public static final String XML_PATH = "/base-javaweb-template01.xml";

	//base-javaweb-template01.properties文件路径
	public static final String PROPERTIES_PATH = "/base-javaweb-template01.properties";

	/**
	 * 不可实例化
	 */
	private CommonAttributes() {
	}
}