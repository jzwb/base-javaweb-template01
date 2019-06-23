package com.jzwb.controller;

import net.sf.json.JSONObject;
import net.shopxx.DateEditor;
import net.shopxx.HtmlCleanEditor;
import net.shopxx.Message;
import net.shopxx.Setting;
import net.shopxx.entity.CvResumeMg;
import net.shopxx.entity.Resume;
import net.shopxx.entity.ResumeBank;
import net.shopxx.template.directive.FlashMessageDirective;
import net.shopxx.util.JsonUtils;
import net.shopxx.util.SettingUtils;
import net.shopxx.util.SpringUtils;
import net.shopxx.util.Tools;
import net.shopxx.util.validate.BeanValidationUtil;
import org.apache.commons.lang.ArrayUtils;
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
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

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
	protected final static String SUCCESS_MSG = "successmsg";
	//错误信息
	protected final static String ERROR_MSG = "errormsg";
	//成功消息
	protected static final Message SUCCESS_MESSAGE = Message.success("shop.message.success");
	//错误消息
	protected static final Message ERROR_MESSAGE = Message.error("shop.message.error");

	@Resource
	protected BeanValidationUtil beanValidationUtil;

	/**
	 * 数据绑定
	 * 
	 * @param binder WebDataBinder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new HtmlCleanEditor(true, true));
		binder.registerCustomEditor(Date.class, new DateEditor(true));
	}

	/**
	 * 301跳转永久跳转
	 * 主要用于更新后一些链接不再使用，跳转到新的连接
	 * @param url 跳转的连接地址
	 */
	public String goToUrl(String url, HttpServletRequest request, HttpServletResponse response){
		response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		response.setHeader("Location", request.getContextPath() + url);  
		response.setHeader("Connection", "close");
		return null;
	}
	
	/**
	 * 去登陆
	 * @param redirectUrl 跳转连接
	 * @return 登录地址
	 */
	protected String goLogin(String redirectUrl, HttpServletRequest request, HttpServletResponse response) {
		try {
			if(redirectUrl == null)
				redirectUrl = "";
			redirectUrl = Tools.getUrlEncode(redirectUrl);
			response.sendRedirect(request.getContextPath() + LOGIN_URI + "?redirectUrl=" + redirectUrl);
		} catch (IOException e) {
			LOGGER.error("去登录失败,error catch:", e);
		}
		return null;
	}
	
	/**
	 * 成功
	 */
	protected String goSuccess() {
		return SUCCESS_VIEW;
	}
	
	/**
	 * 成功
	 * @param msg 成功信息
	 */
	protected String goSuccess(String msg, ModelMap modelMap) {
		modelMap.addAttribute(SUCCESS_MSG, msg);
		return SUCCESS_VIEW;
	}
	
	/**
	 * 错误
	 */
	protected String goError(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return ERROR_VIEW;
	}
	
	/**
	 * 错误
	 * @param msg 错误信息
	 */
	protected String goError(String msg, ModelMap modelMap, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		if(StringUtils.isNotEmpty(msg))
			modelMap.addAttribute(ERROR_MSG, msg);
		return ERROR_VIEW;
	}

	/**
	 * 错误
	 */
	protected String goServerError(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return ERROR_VIEW;
	}

	/**
	 * 错误
	 * @param msg 错误信息
	 */
	protected String goServerError(String msg, ModelMap modelMap, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		if(StringUtils.isNotEmpty(msg))
			modelMap.addAttribute(ERROR_MSG, msg);
		return ERROR_VIEW;
	}

	/**
	 * 错误
	 * @param msg 错误信息
	 */
	protected String goForbidden(String msg, ModelMap modelMap, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		if(StringUtils.isNotEmpty(msg))
			modelMap.addAttribute(ERROR_MSG, msg);
		return ERROR_VIEW;
	}

	/**
	 * 错误
	 */
	protected String goForbidden(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		return ERROR_VIEW;
	}
	
	/**
	 * 去注册
	 * @return 注册地址
	 */
	protected String goRegister(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect(request.getContextPath() + REGISTER_URI);
		} catch (IOException e) {
			LOGGER.error("去注册失败,error catch:", e);
		}
		return null;
	}
	
	/***
	 * 获取返回url
	 * @param request 请求
	 * @return 返回url
	 */
	protected String getReferer(HttpServletRequest request, boolean...removeContextPath) {
		String referer = request.getHeader("Referer");
		if(StringUtils.isEmpty(referer))
			return "/";
		try {
			URL url = new URL(referer);
			String path = url.getPath();
			String host = url.getHost();
			String query = url.getQuery();
			Setting setting = SettingUtils.get();
			int contextPathLength = request.getContextPath().length();
			if(host.equals(setting.getSiteHost())) {
				if(LOGIN_URI.equals(path.substring(contextPathLength)) || REGISTER_URI.equals(path.substring(contextPathLength)))
					return "/";
				else {
					String requestFullPath = url.getPath() + (StringUtils.isEmpty(query) ? "" : "?" + url.getQuery());
					if(ArrayUtils.isNotEmpty(removeContextPath) && removeContextPath[0])
						requestFullPath = requestFullPath.substring(request.getContextPath().length());
					return requestFullPath;
				}
			} else {
				if(referer.startsWith(setting.getAboutSiteUrl()) || // 跳转其他页面
					referer.startsWith(setting.getJobSiteUrl()) ||
					referer.startsWith(setting.getHelpUrl()))
					return referer;
				return "/";
			}
		} catch (MalformedURLException e) {
			return "/";
		}
	}
	
	/**
	 * 获取登录后跳转URL
	 */
	protected String getLoginReturnUrl(HttpServletRequest request, boolean...removeContextPath) {
		String returnUrl = getReferer(request, removeContextPath);
		if(StringUtils.isEmpty(returnUrl))
			return "/";
		return returnUrl;
	}
	
	/**
	 * 判断是不是ajax
	 * @param request 请求
	 * @return true：是；false：不是；
	 */
	protected boolean isAjax(HttpServletRequest request) {
		if(request == null)
			return false;
		return AJAX_HEADER_VALUE.equals(request.getHeader(AJAX_HEADER_NAME));
	}

	/**
	 * 货币格式化
	 * 
	 * @param amount 金额
	 * @param showSign 显示标志
	 * @param showUnit 显示单位
	 * @return 货币格式化
	 */
	protected String currency(BigDecimal amount, boolean showSign, boolean showUnit) {
		Setting setting = SettingUtils.get();
		String price = setting.setScale(amount).toString();
		if (showSign) {
			price = setting.getCurrencySign() + price;
		}
		if (showUnit) {
			price += setting.getCurrencyUnit();
		}
		return price;
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
	 * @param message 消息
	 */
	protected void addFlashMessage(RedirectAttributes redirectAttributes, Message message) {
		if (redirectAttributes != null && message != null) {
			redirectAttributes.addFlashAttribute(FlashMessageDirective.FLASH_MESSAGE_ATTRIBUTE_NAME, message);
		}
	}

	/**
	 * 简历json转简历对象
	 * @param jsonObject
	 * @param resume
	 */
	public void json2Object(JSONObject jsonObject, Resume resume, ResumeBank.ResumeBankType resumeBankType) {
		if(resume != null) {
			if (jsonObject.containsKey("resume_headType")) {
					resume.setHeadType(Tools.jsonEscape(jsonObject.getString("resume_headType")));
				}else{
					resume.setHeadType(null);
				}
			if (jsonObject.containsKey("resume_title")) {//简历标题
				resume.setTitle(jsonObject.getString("resume_title"));
			}
			if (jsonObject.containsKey("resume_scale")) {//简历制作进度
				resume.setScale(jsonObject.getLong("resume_scale"));
			}
			if (jsonObject.containsKey("resume_language")) {//中英文
				resume.setLanguage(jsonObject.getString("resume_language"));
			}
			//简历风格设置
			if (jsonObject.containsKey("resume_set")) {
				JSONObject resume_set = jsonObject.getJSONObject("resume_set");
				if (resume_set.containsKey("color")) {//颜色
					resume.setColor(resume_set.getString("color"));
				}
				if (resume_set.containsKey("font")) {//字体
					resume.setFont(resume_set.getString("font"));
				}
				if (resume_set.containsKey("fontSize")) {//字体大小
					resume.setFontSize(resume_set.getString("fontSize"));
				}
				if (resume_set.containsKey("background")) {
					resume.setBackground(resume_set.getString("background"));
				}
				if (resume_set.containsKey("padding")) {//行距
					resume.setPadding(resume_set.getString("padding"));
				}
				if (resume_set.containsKey("margin")) {//块距
					resume.setMargin(resume_set.getString("margin"));
				}
				if (resume_set.containsKey("pageMargin")) {//页边距
					resume.setPageMargin(resume_set.getString("pageMargin"));
				}
				if (resume_set.containsKey("fontType")) {//简繁体
					resume.setFontType(resume_set.getInt("fontType"));
				}
			}
			//模块管理信息设置
			if (jsonObject.containsKey("modul_show")) {
				JSONObject modul_show = jsonObject.getJSONObject("modul_show");
				if (modul_show.containsKey("coverShow")) {//封面
					resume.setIsCoverShow(modul_show.getBoolean("coverShow"));
				}
				if (modul_show.containsKey("letterShow")) {//自荐信
					resume.setIsLetterShow(modul_show.getBoolean("letterShow"));
				}
				if (modul_show.containsKey("headShow")) {//头像
					resume.setIsHeadShow(modul_show.getBoolean("headShow"));
				}
				if(modul_show.containsKey("contactShow")){//联系我
					CvResumeMg cvResumeMg = new CvResumeMg(resume.getContactMg());
					cvResumeMg.setIsShow(modul_show.getBoolean("contactShow"));
					resume.setContactMg(Tools.jsonEscape(JsonUtils.toJson(cvResumeMg)));
				}
				if (modul_show.containsKey("resume_skill")) {//技能特长
					resume.setSkillMg(Tools.jsonEscape(modul_show.getString("resume_skill")));
				}
				if (modul_show.containsKey("resume_hobby")) {//兴趣爱好
					resume.setHobbyMg(Tools.jsonEscape(modul_show.getString("resume_hobby")));
				}
				if (modul_show.containsKey("resume_job_preference")) {//求职意向
					resume.setJobPreferencesMg(Tools.jsonEscape(modul_show.getString("resume_job_preference")));
				}
				if (modul_show.containsKey("resume_edu")) {//教育背景
					resume.setEduMg(Tools.jsonEscape(modul_show.getString("resume_edu")));
				}
				if (modul_show.containsKey("resume_work")) {//工作经验
					resume.setWorkMg(Tools.jsonEscape(modul_show.getString("resume_work")));
				}
				if (modul_show.containsKey("resume_internship")) {//实习经验
					resume.setInternshipMg(Tools.jsonEscape(modul_show.getString("resume_internship")));
				}
				if (modul_show.containsKey("resume_volunteer")) {//自愿者经历
					resume.setVolunteerMg(Tools.jsonEscape(modul_show.getString("resume_volunteer")));
				}
				if (modul_show.containsKey("resume_project")) {//项目经验
					resume.setProjectMg(Tools.jsonEscape(modul_show.getString("resume_project")));
				}
				if (modul_show.containsKey("resume_honor")) {//荣誉奖项
					resume.setHonorMg(Tools.jsonEscape(modul_show.getString("resume_honor")));
				}
				if (modul_show.containsKey("resume_summary")) {//自我评价
					resume.setSummaryMg(Tools.jsonEscape(modul_show.getString("resume_summary")));
				}
				if (modul_show.containsKey("resume_portfolio")) {//作品展示
					resume.setPortfolioMg(Tools.jsonEscape(modul_show.getString("resume_portfolio")));
				}
				if (modul_show.containsKey("resume_recoment")) {//推荐信
					resume.setRecommentMg(Tools.jsonEscape(modul_show.getString("resume_recoment")));
				}
				if (modul_show.containsKey("resume_contact")) {//联系我
					resume.setContactMg(Tools.jsonEscape(modul_show.getString("resume_contact")));
				}
				if (modul_show.containsKey("resume_qrcode")) {//二维码
					resume.setQrcodeMg(Tools.jsonEscape(modul_show.getString("resume_qrcode")));
				}
			}
			if (jsonObject.containsKey("iconFontMap")) {//图标管理
				resume.setIconFontMap(Tools.jsonEscape(jsonObject.getString("iconFontMap")));
			}
			//信息保存
			if (jsonObject.containsKey("resume_cover")) {//封面信息
				resume.setCover(Tools.jsonEscape(jsonObject.getString("resume_cover")));
			}else{
				resume.setCover(null);
			}
			if (jsonObject.containsKey("resume_letter")) {//自荐信
				resume.setLetter(Tools.jsonEscape(jsonObject.getString("resume_letter")));
			}else{
				resume.setLetter(null);
			}
			if (jsonObject.containsKey("resume_head")) {//头像
				resume.setHead(Tools.jsonEscape(jsonObject.getString("resume_head")));
			}else{
				resume.setHead(null);
			}
			if (jsonObject.containsKey("resume_base_info")) {//基本信息
				JSONObject resume_base_info = jsonObject.getJSONObject("resume_base_info");
				if (resume_base_info.containsKey("name")) {//姓名
					resume.setName(resume_base_info.getString("name"));
				} else if (resume.getId() != null) {
					resume.setName(null);
				}
				if (resume_base_info.containsKey("minSummary")) {//一句话描述
					resume.setMinSummary(resume_base_info.getString("minSummary"));
				} else if (resume.getId() != null) {
					resume.setMinSummary(null);
				}
				if (resume_base_info.containsKey("birth")) {//生日
					try {
						String[] birth = resume_base_info.getString("birth").split("\\.");
						if (birth != null && birth.length == 2) {
							int year = Integer.parseInt(birth[0].trim());
							int currYear = Calendar.getInstance().get(Calendar.YEAR);
							resume.setBirth(resume_base_info.getString("birth"));
							resume.setAge(Long.parseLong(String.valueOf(currYear - year)));
						} else {
							resume.setBirth(null);
							resume.setAge(null);
						}
					}catch (Exception e) {
						LOGGER.error("简历基本信息生日保存失败,参数(birth:[{}]),error catch:", resume_base_info.getString("birth"), e);
					}
				} else if (resume.getId() != null) {
					resume.setBirth(null);
					resume.setAge(null);
				}
				if (resume_base_info.containsKey("city")) {//城市id，城市名
					resume.setCity(resume_base_info.getLong("city"));
				} else if (resume.getId() != null) {
					resume.setCity(null);
				}
				if(resume_base_info.containsKey("cityName")){
					resume.setCityName(resume_base_info.getString("cityName"));
				} else if (resume.getId() != null) {
					resume.setCityName(null);
				}
				
				if (resume_base_info.containsKey("jobYear")) {//工作年限
					resume.setJobYear(Resume.JobYear.valueOf(resume_base_info.getString("jobYear")));
				} else if (resume.getId() != null) {
					resume.setJobYear(null);
				}
				if (resume_base_info.containsKey("mobile")) {//电话
					resume.setMobile(resume_base_info.getString("mobile"));
				} else if (resume.getId() != null) {
					resume.setMobile(null);
				}
				if (resume_base_info.containsKey("email")) {//电子邮件
					resume.setEmail(resume_base_info.getString("email"));
				} else if (resume.getId() != null) {
					resume.setEmail(null);
				}
				if (resume_base_info.containsKey("sex")) {//性别
					resume.setSex(Resume.Sex.valueOf(resume_base_info.getString("sex")));
				} else if (resume.getId() != null) {
					resume.setSex(null);
				}
				if (resume_base_info.containsKey("education")) {//最高学历
					resume.setEducation(Resume.Education.valueOf(resume_base_info.getString("education")));
				} else if (resume.getId() != null) {
					resume.setEducation(null);
				}
				if (resume_base_info.containsKey("nation")) {//民族
					resume.setNation(resume_base_info.getString("nation"));
				} else if (resume.getId() != null) {
					resume.setNation(null);
				}
				if (resume_base_info.containsKey("marriageStatus")) {//婚姻状态
					resume.setMarriageStatus(Resume.MarriageStatus.valueOf(resume_base_info.getString("marriageStatus")));
				} else if (resume.getId() != null) {
					resume.setMarriageStatus(null);
				}
				if (resume_base_info.containsKey("politicalStatus")) {//政治面貌
					resume.setPoliticalStatus(Resume.PoliticalStatus.valueOf(resume_base_info.getString("politicalStatus")));
				} else if (resume.getId() != null) {
					resume.setPoliticalStatus(null);
				}
				if (resume_base_info.containsKey("height")) {//身高
					resume.setHeight(resume_base_info.getLong("height"));
				} else if (resume.getId() != null) {
					resume.setHeight(null);
				}
				if (resume_base_info.containsKey("weight")) {//体重
					resume.setWeight(resume_base_info.getLong("weight"));
				} else if (resume.getId() != null) {
					resume.setWeight(null);
				}
				if (resume_base_info.containsKey("customMsg")) {//个人自定义信息
					resume.setCustomMsg(resume_base_info.getString("customMsg"));
				} else if (resume.getId() != null) {
					resume.setCustomMsg(null);
				}
				if (resume_base_info.containsKey("customWebsite")) {//个人网站
					resume.setCustomWebsite(resume_base_info.getString("customWebsite"));
				} else if (resume.getId() != null) {
					resume.setCustomWebsite(null);
				}
			}
			if (jsonObject.containsKey("resume_skill")) {//技能特长
				resume.setSkillJson(jsonObject.getString("resume_skill"));
			} else if (resume.getId() != null) {
				resume.setSkillJson(null);
			}
			if (jsonObject.containsKey("resume_hobby")) {//兴趣爱好
				resume.setHobbyJson(jsonObject.getString("resume_hobby"));
			} else if (resume.getId() != null) {
				resume.setHobbyJson(null);
			}
			//模块信息保存
			if (jsonObject.containsKey("resume_job_preference")) {//求职意向
				JSONObject job_preference = jsonObject.getJSONObject("resume_job_preference");
				if (job_preference.containsKey("jobCity")) {//城市id，城市名称
					resume.setJobCity(job_preference.getLong("jobCity"));
				} else if (resume.getId() != null) {
					resume.setJobCity(null);
				}
				if(job_preference.containsKey("jobCityName")){
					resume.setJobCityName(job_preference.getString("jobCityName"));
				} else if (resume.getId() != null) {
					resume.setJobCityName(null);
				} 
				if (job_preference.containsKey("jobFunction")) {//求职岗位
					resume.setJobFunction(job_preference.getString("jobFunction"));
				} else if (resume.getId() != null) {
					resume.setJobFunction(null);
				}
				if (job_preference.containsKey("jobMinSalary")) {//薪资要求
					resume.setJobMinSalary(job_preference.getLong("jobMinSalary"));
				} else if (resume.getId() != null) {
					resume.setJobMinSalary(null);
				}
				if (job_preference.containsKey("jobMaxSalary")) {//薪资要求
					resume.setJobMaxSalary(job_preference.getLong("jobMaxSalary"));
				} else if (resume.getId() != null) {
					resume.setJobMaxSalary(null);
				}
				if (job_preference.containsKey("jobTime")) {//工作年限
					resume.setJobTime(Resume.JobTime.valueOf(job_preference.getString("jobTime")));
				} else if (resume.getId() != null) {
					resume.setJobTime(null);
				}
				if (job_preference.containsKey("jobType")) {//工作类型
					resume.setJobType(Resume.JobType.valueOf(job_preference.getString("jobType")));
				} else if (resume.getId() != null) {
					resume.setJobType(null);
				}
			}
			if (jsonObject.containsKey("resume_edu")) {//教育背景
				resume.setEduJson(Tools.jsonEscape(jsonObject.getString("resume_edu")));
			} else if (resume.getId() != null) {
				resume.setEduJson(null);
			}
			if (jsonObject.containsKey("resume_work")) {//工作经历
				resume.setWorkJson(Tools.jsonEscape(jsonObject.getString("resume_work")));
			} else if (resume.getId() != null) {
				resume.setWorkJson(null);
			}
			if (jsonObject.containsKey("resume_internship")) {//实习经历
				resume.setInternshipJson(Tools.jsonEscape(jsonObject.getString("resume_internship")));
			} else if (resume.getId() != null) {
				resume.setInternshipJson(null);
			}
			if (jsonObject.containsKey("resume_volunteer")) {//志愿者经历
				resume.setVolunteerJson(Tools.jsonEscape(jsonObject.getString("resume_volunteer")));
			} else if (resume.getId() != null) {
				resume.setVolunteerJson(null);
			}
			if (jsonObject.containsKey("resume_project")) {//项目经验
				resume.setProjectJson(Tools.jsonEscape(jsonObject.getString("resume_project")));
			} else if (resume.getId() != null) {
				resume.setProjectJson(null);
			}
			if (jsonObject.containsKey("resume_honor")) {//奖项荣誉
				resume.setHonor(Tools.jsonEscape(jsonObject.getString("resume_honor")));
			} else if (resume.getId() != null) {
				resume.setHonor(null);
			}
			if (jsonObject.containsKey("resume_summary")) {//自我评价
				resume.setSummary(Tools.jsonEscape(jsonObject.getString("resume_summary")));
			} else if (resume.getId() != null) {
				resume.setSummary(null);
			}
			if (jsonObject.containsKey("resume_portfolio")) {//作品展示
				resume.setPortfolio(jsonObject.getString("resume_portfolio"));
			} else if (resume.getId() != null) {
				resume.setPortfolio(null);
			}
			//推荐信不用保存在简历表中
			if (jsonObject.containsKey("resume_contact")) {//联系我
				resume.setContactJson(Tools.jsonEscape(jsonObject.getString("resume_contact")));
			} else if (resume.getId() != null) {
				resume.setContactJson(null);
			}
			if (jsonObject.containsKey("resume_qrcode")) {//二维码
				JSONObject resume_qrcode = jsonObject.getJSONObject("resume_qrcode");
			/*if (resume_qrcode.containsKey("qrcodeImg")) {//二维码图片
				resume.setQrcodeImg(resume_qrcode.getString("qrcodeImg"));
			}*/
				if (resume_qrcode.containsKey("qrcodeTips")) {//二维码提示
					resume.setQrcodeTips(Tools.jsonEscape(resume_qrcode.getString("qrcodeTips")));
				} else if (resume.getId() != null) {
					resume.setQrcodeTips(null);
				}
			}
			if (jsonObject.containsKey("custom")) {//自定义模块
				resume.setCustom(Tools.jsonEscape(jsonObject.getString("custom")));
			} else if (resume.getId() != null) {
				resume.setCustom(null);
			}
			if (jsonObject.containsKey("sort")) {//模块排序信息
				if (ResumeBank.ResumeBankType.wap.equals(resumeBankType)) {
					resume.setWapResumeSort(jsonObject.getString("sort"));
				} else {
					resume.setResumeSort(jsonObject.getString("sort"));
				}
			}
			if(jsonObject.containsKey("module_tags")) {
				resume.setModuleTags(jsonObject.getString("module_tags"));
			} else if (resume.getId() != null) {
				resume.setModuleTags(null);
			}
		}
	}
}