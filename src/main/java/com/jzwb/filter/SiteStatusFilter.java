package com.jzwb.filter;

import com.jzwb.common.Setting;
import com.jzwb.util.SettingUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SiteStatusFilter - 网站状态
 */
@Component("siteStatusFilter")
public class SiteStatusFilter extends OncePerRequestFilter {

	//默认忽略URL
	private static final String[] DEFAULT_IGNORE_URL_PATTERNS = new String[]{"/**"};
	//默认重定向URL
	private static final String DEFAULT_REDIRECT_URL = "/";
	//antPathMatcher
	private static AntPathMatcher antPathMatcher = new AntPathMatcher();
	//忽略URL
	private String[] ignoreUrlPatterns = DEFAULT_IGNORE_URL_PATTERNS;
	//重定向URL
	private String redirectUrl = DEFAULT_REDIRECT_URL;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		Setting setting = SettingUtils.get();
		if (setting.getIsSiteEnabled()) {
			filterChain.doFilter(request, response);
		} else {
			String path = request.getServletPath();
			if (path.equals(redirectUrl)) {
				filterChain.doFilter(request, response);
				return;
			}
			if (ignoreUrlPatterns != null) {
				for (String ignoreUrlPattern : ignoreUrlPatterns) {
					if (antPathMatcher.match(ignoreUrlPattern, path)) {
						filterChain.doFilter(request, response);
						return;
					}
				}
			}
			response.sendRedirect(request.getContextPath() + redirectUrl);
		}
	}

	public String[] getIgnoreUrlPatterns() {
		return ignoreUrlPatterns;
	}

	public void setIgnoreUrlPatterns(String[] ignoreUrlPatterns) {
		this.ignoreUrlPatterns = ignoreUrlPatterns;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
}