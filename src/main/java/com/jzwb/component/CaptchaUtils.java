package com.jzwb.component;

import com.jzwb.common.Setting;
import com.jzwb.util.SettingUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;

/**
 * Component - 验证码工具类
 */
@Component
public class CaptchaUtils {

	@Resource(name = "imageCaptchaService")
	private com.octo.captcha.service.CaptchaService imageCaptchaService;

	/**
	 * 生成
	 *
	 * @param captchaId
	 * @return
	 */
	public BufferedImage buildImage(String captchaId) {
		return (BufferedImage) imageCaptchaService.getChallengeForID(captchaId);
	}

	/**
	 * 校验
	 *
	 * @param captchaType
	 * @param captchaId
	 * @param captcha
	 * @return
	 */
	public boolean valid(Setting.CaptchaType captchaType, String captchaId, String captcha) {
		if (captchaType == null || StringUtils.isBlank(captchaId) || StringUtils.isBlank(captcha)) {
			return true;
		}
		Setting setting = SettingUtils.get();
		if (ArrayUtils.contains(setting.getCaptchaTypes(), captchaType)) {
			try {
				return imageCaptchaService.validateResponseForID(captchaId, captcha.toUpperCase());
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}
}