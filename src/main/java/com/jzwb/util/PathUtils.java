package com.jzwb.util;

import com.jzwb.common.Setting;
import org.apache.commons.lang.StringUtils;

/**
 * PathUtils - 路径工具类
 */
public class PathUtils {
	//项目根目录路径
	private static String ROOT_PATH = null;
	//类路径
	private static String CLASS_PATH = null;
	//用户上传文件夹
	private static final String USER_UPLOAD_FOLDER = ".+/upload/(uimage|tmpimage)/.+";

	public static void init() {
		try {
			CLASS_PATH = Tools.class.getResource("./").getPath().replace("/com/jzwb/util/", "/");
		} catch (Exception e) {
			initOtherMethod();
		}
		if (!CLASS_PATH.startsWith("/")) {
			CLASS_PATH = "/" + CLASS_PATH;
		}
		String rootPath = SettingUtils.get().getRootPath();
		if (StringUtils.isNotBlank(rootPath)) {
			ROOT_PATH = rootPath;
		} else {
			ROOT_PATH = CLASS_PATH.replace("/WEB-INF/classes/", "/");
		}
	}

	/**
	 * 使用其他方式获取classes路径
	 */
	private static void initOtherMethod() {
		CLASS_PATH = Tools.class.getResource("PathUtils.class").getPath().replace("/com/jzwb/util/PathUtils.class", "/");
	}

	/**
	 * 获取classes路径
	 *
	 * @return
	 */
	public static String getClassesPath() {
		return CLASS_PATH;
	}

	/**
	 * 获取classes路径
	 *
	 * @param path
	 * @return
	 */
	public static String getClassesPath(String path) {
		if (path == null) {
			return CLASS_PATH;
		} else {
			if (path.startsWith("/"))
				return getClassesPath(path.substring(1));
			return CLASS_PATH + path;
		}
	}

	/**
	 * 获取项目根目录路径
	 *
	 * @return
	 */
	public static String getRootPath() {
		return ROOT_PATH;
	}

	/**
	 * 获取文件网络路径
	 *
	 * @param filePath 静态文件路径
	 * @return
	 */
	public static String getStaticUrl(String filePath) {
		Setting setting = SettingUtils.get();
		if (filePath.startsWith(getRootPath())) {
			filePath = filePath.substring(getRootPath().length());
		}
		if (filePath.startsWith("http://") || filePath.startsWith("https://")) {
			return filePath;
		}
		if (filePath.startsWith(setting.getStaticUrl())) {
			return filePath;
		}
		if (filePath.startsWith("/")) {
			return setting.getStaticUrl() + filePath;
		}
		return setting.getStaticUrl() + "/" + filePath;
	}

	/**
	 * 获取基于项目根目录路径
	 *
	 * @param path
	 * @return
	 */
	public static String getRootPath(String path) {
		if (path == null) {
			return ROOT_PATH;
		} else {
			if (path.startsWith("/")) {
				return getRootPath(path.substring(1));
			}
			return ROOT_PATH + path;
		}
	}

	/**
	 * 管理员获取文件地址
	 *
	 * @param url 文件连接
	 * @return
	 */
	public static String getFilePathWidthStaticUrlUser(String url) {
		return getFilePathWidthStaticUrl(url, true);
	}

	/**
	 * 用户获取文件地址
	 *
	 * @param url 文件连接
	 * @return
	 */
	public static String getFilePathWidthStaticUrlAdmin(String url) {
		return getFilePathWidthStaticUrl(url, false);
	}

	/**
	 * 网络文件获取文件地址
	 *
	 * @param url  文件URL
	 * @param user 是否用户文件：true：是用户文件夹；false系统文件夹
	 */
	private static String getFilePathWidthStaticUrl(String url, boolean user) {
		if (StringUtils.isEmpty(url))
			return null;
		Setting setting = SettingUtils.get();
		if (url.startsWith(setting.getStaticUrl()) && (!user || url.matches(USER_UPLOAD_FOLDER)))
			return PathUtils.getRootPath(url.substring(setting.getStaticUrl().length()));
		return null;
	}

	/**
	 * 判断是不是用户上传的文件
	 *
	 * @param path 文件地址
	 * @return
	 */
	public static boolean isUserUpload(String path) {
		return StringUtils.isNotEmpty(path) && path.matches(USER_UPLOAD_FOLDER);
	}
}
