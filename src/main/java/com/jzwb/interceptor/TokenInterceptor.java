package com.jzwb.interceptor;

import com.jzwb.util.WebUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * TokenInterceptor - 令牌拦截器
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	//令牌属性名
	private static final String TOKEN_ATTRIBUTE_NAME = "token";
	//令牌cookie名称
	private static final String TOKEN_COOKIE_NAME = "token";
	//令牌参数名称
	private static final String TOKEN_PARAMETER_NAME = "token";
	//错误消息
	private static final String ERROR_MESSAGE = "Bad or missing token!";

	/**
	 * post请求校验token
	 *
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String token = WebUtils.getCookie(request, TOKEN_COOKIE_NAME);
		String requestUri = request.getRequestURI();
		if (requestUri != null && request.getMethod().equalsIgnoreCase("POST")) {
			//post请求
			if (WebUtils.isAjax(request)) {
				//ajax请求
				if (token != null && token.equals(request.getHeader(TOKEN_PARAMETER_NAME))) {
					request.setAttribute(TOKEN_ATTRIBUTE_NAME, token);
					return true;
				}
			} else {
				//普通请求
				if (token != null && token.equals(request.getParameter(TOKEN_PARAMETER_NAME))) {
					request.setAttribute(TOKEN_ATTRIBUTE_NAME, token);
					return true;
				}
			}
			//token校验不通过
			if (token == null) {
				token = UUID.randomUUID().toString();
				WebUtils.addCookie(request, response, TOKEN_COOKIE_NAME, token);
			}
			response.addHeader("tokenStatus", "accessDenied");
			response.sendError(HttpServletResponse.SC_FORBIDDEN, ERROR_MESSAGE);
			return false;
		} else {
			if (token == null) {
				token = UUID.randomUUID().toString();
				WebUtils.addCookie(request, response, TOKEN_COOKIE_NAME, token);
			}
			request.setAttribute(TOKEN_ATTRIBUTE_NAME, token);
			return true;
		}
	}
}