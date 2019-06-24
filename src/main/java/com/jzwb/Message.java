package com.jzwb;

import com.jzwb.component.SpringUtils;

import java.io.Serializable;

/**
 * 消息
 */
public class Message implements Serializable {

	/**
	 * 类型
	 */
	public enum Type {
		success("成功"),
		warn("警告"),
		error("错误");

		Type(String desc) {
			this.desc = desc;
		}

		private String desc;

		public String getDesc() {
			return desc;
		}
	}

	public static final int HOME_SIZE = 10; // 主页显示消息数量

	//类型
	private Type type;
	//内容
	private String content;

	public Message() {

	}

	public Message(Type type, String content) {
		this.type = type;
		this.content = content;
	}

	public Message(Type type, String content, Object... args) {
		this.type = type;
		this.content = SpringUtils.getMessage(content, args);
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return SpringUtils.getMessage(content);
	}

	/**
	 * 返回成功消息
	 *
	 * @param content 内容
	 * @param args    参数
	 * @return
	 */
	public static Message success(String content, Object... args) {
		return new Message(Type.success, content, args);
	}

	/**
	 * 返回警告消息
	 *
	 * @param content 内容
	 * @param args    参数
	 * @return
	 */
	public static Message warn(String content, Object... args) {
		return new Message(Type.warn, content, args);
	}

	/**
	 * 返回错误消息
	 *
	 * @param content 内容
	 * @param args    参数
	 * @return
	 */
	public static Message error(String content, Object... args) {
		return new Message(Type.error, content, args);
	}
}