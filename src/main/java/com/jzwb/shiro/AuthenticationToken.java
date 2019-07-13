package com.jzwb.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 登录令牌
 */
public class AuthenticationToken extends UsernamePasswordToken {

	//通过验证码
	private boolean passCaptcha;

	/**
	 * @param username    用户名
	 * @param password    密码
	 * @param passCaptcha 通过验证码
	 * @param rememberMe  记住我
	 * @param host        IP
	 */
	public AuthenticationToken(String username, String password, boolean passCaptcha, boolean rememberMe, String host) {
		super(username, password, rememberMe, host);
		this.passCaptcha = passCaptcha;
	}

	public boolean getPassCaptcha() {
		return passCaptcha;
	}

	public void setPassCaptcha(boolean passCaptcha) {
		this.passCaptcha = passCaptcha;
	}
}