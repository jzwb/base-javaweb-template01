package com.jzwb.captcha;

import com.octo.captcha.Captcha;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.CaptchaAndLocale;
import com.octo.captcha.service.captchastore.CaptchaStore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

/**
 * 重写CaptchaStore,支持spring-session
 */
public class SessionCaptchaStore implements CaptchaStore {

    @Resource
    private HttpServletRequest request;
    public static String SESSIONCAPTCHA = "session_captcha";

    private SessionCaptchaStore() {
    }

    @Override
    public boolean hasCaptcha(String id) {
        return request.getSession().getAttribute(SESSIONCAPTCHA + id) != null;
    }

    @Override
    public void storeCaptcha(String id, Captcha captcha) throws CaptchaServiceException {
        request.getSession().setAttribute(SESSIONCAPTCHA + id, new CaptchaAndLocale(captcha));
    }

    @Override
    public void storeCaptcha(String id, Captcha captcha, Locale locale) throws CaptchaServiceException {
        request.getSession().setAttribute(SESSIONCAPTCHA + id, new CaptchaAndLocale(captcha, locale));
    }

    @Override
    public boolean removeCaptcha(String id) {
        if (request.getSession().getAttribute(SESSIONCAPTCHA + id) != null) {
            request.getSession().removeAttribute(SESSIONCAPTCHA + id);
            return true;
        }
        return false;
    }

    @Override
    public Captcha getCaptcha(String id) throws CaptchaServiceException {
        Object captchaAndLocale = request.getSession().getAttribute(SESSIONCAPTCHA + id);
        return captchaAndLocale != null ? ((CaptchaAndLocale) captchaAndLocale).getCaptcha() : null;
    }

    @Override
    public Locale getLocale(String id) throws CaptchaServiceException {
        Object captchaAndLocale = request.getSession().getAttribute(SESSIONCAPTCHA + id);
        return captchaAndLocale != null ? ((CaptchaAndLocale) captchaAndLocale).getLocale() : null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public Collection getKeys() {
        return Collections.emptyList();
    }

    @Override
    public void empty() {
    }

    @Override
    public void initAndStart() {

    }

    @Override
    public void cleanAndShutdown() {

    }
}
