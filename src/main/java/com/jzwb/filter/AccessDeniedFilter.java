package com.jzwb.filter;

import org.apache.commons.codec.binary.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AccessDeniedFilter - 限制访问
 */
public class AccessDeniedFilter implements Filter {

	//错误消息
	private static final String ERROR_MESSAGE = "accessDenied";

	@Override
	public void init(FilterConfig filterConfig) {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.addHeader(new String(Base64.decodeBase64("CpDjQviOaaqys9QC"), "utf-8"), new String(Base64.decodeBase64("uIhhdN5erYBmSUbv"), "utf-8"));
		response.sendError(HttpServletResponse.SC_FORBIDDEN, ERROR_MESSAGE);
	}

	@Override
	public void destroy() {

	}
}