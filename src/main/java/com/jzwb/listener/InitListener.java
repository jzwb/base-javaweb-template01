package com.jzwb.listener;

import com.jzwb.util.PathUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.logging.Logger;

/**
 * Listener - 初始化
 */
@Component("initListener")
public class InitListener implements ServletContextAware, ApplicationListener<ContextRefreshedEvent> {

	private static final Logger LOGGER = Logger.getLogger(InitListener.class.getName());
	private ServletContext servletContext;
	@Value("${system.version}")
	private String systemVersion;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		if (servletContext != null && contextRefreshedEvent.getApplicationContext().getParent() == null) {
			String info = "I|n|i|t|i|a|l|i|z|i|n|g| |b|a|s|e|-|j|a|v|a|w|e|b|-|t|e|m|p|l|a|t|e| |" + systemVersion;
			LOGGER.info(info.replace("|", ""));
			PathUtils.init();
		}
	}
}