package com.jzwb.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * EncodingConvertFilter - 编码格式转换
 */
public class EncodingConvertFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(EncodingConvertFilter.class);

	//原编码格式
	private String fromEncoding = "ISO-8859-1";
	//目标编码格式
	private String toEncoding = "UTF-8";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if (request.getMethod().equalsIgnoreCase("GET")) {
			for (String[] parames : request.getParameterMap().values()) {
				for (int i = 0; i < parames.length; i++) {
					try {
						parames[i] = new String(parames[i].getBytes(fromEncoding), toEncoding);
					} catch (UnsupportedEncodingException e) {
						LOGGER.error("error catch:", e);
					}
				}
			}
		}
		filterChain.doFilter(request, response);
	}

	public String getFromEncoding() {
		return fromEncoding;
	}

	public void setFromEncoding(String fromEncoding) {
		this.fromEncoding = fromEncoding;
	}

	public String getToEncoding() {
		return toEncoding;
	}

	public void setToEncoding(String toEncoding) {
		this.toEncoding = toEncoding;
	}
}