package com.jzwb.component;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Component - RSA安全
 */
@Component("rsaUtils")
public class RSAUtils {

	//私钥
	private static final String PRIVATE_KEY_ATTRIBUTE_NAME = "privateKey";

	/**
	 * 生成公钥
	 *
	 * @param request
	 * @return
	 */
	public RSAPublicKey generateKey(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		KeyPair keyPair = com.jzwb.util.RSAUtils.generateKeyPair();
		if (keyPair == null) {
			return null;
		}
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		HttpSession session = request.getSession();
		session.setAttribute(PRIVATE_KEY_ATTRIBUTE_NAME, privateKey);
		return publicKey;
	}

	/**
	 * 移除私钥
	 *
	 * @param request
	 */
	public void removePrivateKey(HttpServletRequest request) {
		if (request == null) {
			return;
		}
		HttpSession session = request.getSession();
		session.removeAttribute(PRIVATE_KEY_ATTRIBUTE_NAME);
	}

	/**
	 * 解密参数
	 *
	 * @param name    参数名
	 * @param request
	 * @return
	 */
	public String decryptParameter(String name, HttpServletRequest request) {
		if (StringUtils.isBlank(name) || request == null) {
			return null;
		}
		HttpSession session = request.getSession();
		RSAPrivateKey privateKey = (RSAPrivateKey) session.getAttribute(PRIVATE_KEY_ATTRIBUTE_NAME);
		String parameter = request.getParameter(name);
		if (privateKey == null || StringUtils.isBlank(parameter)) {
			return null;
		}
		return com.jzwb.util.RSAUtils.decrypt(privateKey, parameter);
	}
}