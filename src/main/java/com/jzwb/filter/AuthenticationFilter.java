package com.jzwb.filter;

import com.jzwb.captcha.SessionCaptchaStore;
import com.jzwb.component.RSAUtils;
import com.jzwb.entity.Admin;
import com.jzwb.shiro.AuthenticationToken;
import com.jzwb.util.Tools;
import com.octo.captcha.service.captchastore.CaptchaAndLocale;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Filter - 权限认证
 */
public class AuthenticationFilter extends FormAuthenticationFilter {

	//加密密码
	private static final String DEFAULT_EN_PASSWORD_PARAM = "enPassword";
	//验证码id
	private static final String DEFAULT_CAPTCHA_ID_PARAM = "captchaId";
	//验证码
	private static final String DEFAULT_CAPTCHA_PARAM = "captcha";
	//加密密码
	private String enPasswordParam = DEFAULT_EN_PASSWORD_PARAM;
	//验证码id
	private String captchaIdParam = DEFAULT_CAPTCHA_ID_PARAM;
	//验证码
	private String captchaParam = DEFAULT_CAPTCHA_PARAM;

	@Resource(name = "rsaUtils")
	private RSAUtils rsaUtils;

	@Override
	protected org.apache.shiro.authc.AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
		String username = getUsername(servletRequest);
		String password = getPassword(servletRequest);
		String captchaId = getCaptchaId(servletRequest);
		String captcha = getCaptcha(servletRequest);
		boolean rememberMe = isRememberMe(servletRequest);
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		String host = Tools.getIp(httpServletRequest);
		//验证码
		boolean passCaptcha = false;
		if (StringUtils.isNotBlank(captcha)) {
			Object captchaAndLocale = httpServletRequest.getSession().getAttribute(SessionCaptchaStore.SESSIONCAPTCHA + captchaId);
			if(captchaAndLocale != null){
				passCaptcha = ((CaptchaAndLocale) captchaAndLocale).getCaptcha().validateResponse(captcha.toUpperCase());
			}
		}
		return new AuthenticationToken(username, password, passCaptcha, rememberMe, host);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String requestType = request.getHeader("X-Requested-With");
		if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
			response.addHeader("loginStatus", "accessDenied");
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return false;
		}
		return super.onAccessDenied(request, response);
	}

	@Override
	protected boolean onLoginSuccess(org.apache.shiro.authc.AuthenticationToken token, Subject subject, ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
		Session session = subject.getSession();
		Map<Object, Object> attributes = new HashMap<>();
		Collection<Object> keys = session.getAttributeKeys();
		for (Object key : keys) {
			attributes.put(key, session.getAttribute(key));
		}
		session.stop();
		session = subject.getSession();
		for (Entry<Object, Object> entry : attributes.entrySet()) {
			session.setAttribute(entry.getKey(), entry.getValue());
		}
		session.setAttribute(Admin.ADMIN_LOGIN_STATUS, Boolean.TRUE.toString());
		return super.onLoginSuccess(token, subject, servletRequest, servletResponse);
	}

	@Override
	protected String getPassword(ServletRequest servletRequest) {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String password = rsaUtils.decryptParameter(enPasswordParam, request);
		rsaUtils.removePrivateKey(request);
		return password;
	}

	/**
	 * 获取验证码id
	 *
	 * @param servletRequest
	 * @return
	 */
	protected String getCaptchaId(ServletRequest servletRequest) {
		String captchaId = WebUtils.getCleanParam(servletRequest, captchaIdParam);
		if (captchaId == null) {
			captchaId = ((HttpServletRequest) servletRequest).getSession().getId();
		}
		return captchaId;
	}

	/**
	 * 获取验证码
	 *
	 * @param servletRequest
	 * @return
	 */
	protected String getCaptcha(ServletRequest servletRequest) {
		return WebUtils.getCleanParam(servletRequest, captchaParam);
	}

	public String getEnPasswordParam() {
		return enPasswordParam;
	}

	public void setEnPasswordParam(String enPasswordParam) {
		this.enPasswordParam = enPasswordParam;
	}

	public String getCaptchaIdParam() {
		return captchaIdParam;
	}

	public void setCaptchaIdParam(String captchaIdParam) {
		this.captchaIdParam = captchaIdParam;
	}

	public String getCaptchaParam() {
		return captchaParam;
	}

	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}
}