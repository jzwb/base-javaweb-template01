package com.jzwb.controller;

import com.jzwb.Message;
import com.jzwb.component.BeanValidationUtils;
import com.jzwb.component.SpringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller - 基类
 */
public class BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

	//错误视图
	protected static final String ERROR_VIEW = "/common/error";
	//成功视图
	protected static final String SUCCESS_VIEW = "/common/success";
	//404视图
	protected static final String NOT_FOUND_VIEW = "/common/404";
	//成功信息
	protected final static String SUCCESS_MSG = "successMsg";
	//错误信息
	protected final static String ERROR_MSG = "errorMsg";
	//成功消息
	protected static final Message SUCCESS_MESSAGE = Message.success("common.message.success");
	//错误消息
	protected static final Message ERROR_MESSAGE = Message.error("common.message.error");

	@Resource
	protected BeanValidationUtils beanValidationUtils;

	/**
	 * 数据绑定
	 *
	 * @param binder WebDataBinder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		//binder.registerCustomEditor(String.class, new HtmlCleanEditor(true, true));
		//binder.registerCustomEditor(Date.class, new DateEditor(true));
	}

	/**
	 * 301跳转永久跳转
	 *
	 * @param url
	 * @param request
	 * @param response
	 * @return
	 */
	public String goToUrl(String url, HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		response.setHeader("Location", request.getContextPath() + url);
		response.setHeader("Connection", "close");
		return null;
	}

	/**
	 * 跳转成功页面
	 *
	 * @return
	 */
	protected String goSuccess() {
		return SUCCESS_VIEW;
	}

	/**
	 * 跳转成功页面
	 *
	 * @param msg      错误信息
	 * @param modelMap
	 * @return
	 */
	protected String goSuccess(String msg, ModelMap modelMap) {
		modelMap.addAttribute(SUCCESS_MSG, msg);
		return SUCCESS_VIEW;
	}

	/**
	 * 404错误页面
	 *
	 * @param response
	 * @return
	 */
	protected String goError(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return ERROR_VIEW;
	}

	/**
	 * 404错误页面
	 *
	 * @param msg      错误信息
	 * @param modelMap
	 * @param response
	 * @return
	 */
	protected String goError(String msg, ModelMap modelMap, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		if (StringUtils.isNotEmpty(msg))
			modelMap.addAttribute(ERROR_MSG, msg);
		return ERROR_VIEW;
	}

	/**
	 * 500错误页面
	 *
	 * @param response
	 * @return
	 */
	protected String goServerError(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return ERROR_VIEW;
	}

	/**
	 * 500错误页面
	 *
	 * @param msg      错误信息
	 * @param modelMap
	 * @param response
	 * @return
	 */
	protected String goServerError(String msg, ModelMap modelMap, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		if (StringUtils.isNotEmpty(msg))
			modelMap.addAttribute(ERROR_MSG, msg);
		return ERROR_VIEW;
	}

	/**
	 * 403错误页面
	 *
	 * @param msg      错误信息
	 * @param modelMap
	 * @param response
	 * @return
	 */
	protected String goForbidden(String msg, ModelMap modelMap, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		if (StringUtils.isNotEmpty(msg))
			modelMap.addAttribute(ERROR_MSG, msg);
		return ERROR_VIEW;
	}

	/**
	 * 403错误页面
	 *
	 * @param response
	 * @return
	 */
	protected String goForbidden(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		return ERROR_VIEW;
	}

	/**
	 * 获取国际化消息
	 *
	 * @param code 代码
	 * @param args 参数
	 * @return 国际化消息
	 */
	protected String message(String code, Object... args) {
		return SpringUtils.getMessage(code, args);
	}

	/**
	 * 添加瞬时消息
	 *
	 * @param redirectAttributes RedirectAttributes
	 * @param message            消息
	 */
	protected void addFlashMessage(RedirectAttributes redirectAttributes, Message message) {
		/*if (redirectAttributes != null && message != null) {
			redirectAttributes.addFlashAttribute(FlashMessageDirective.FLASH_MESSAGE_ATTRIBUTE_NAME, message);
		}*/
	}
}