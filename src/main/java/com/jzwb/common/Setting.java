package com.jzwb.common;

import java.io.Serializable;

/**
 * 系统设置
 */
public class Setting implements Serializable {

    //缓存名称
    public static final String CACHE_NAME = "setting";
    //缓存key
    public static final Integer CACHE_KEY = 0;

    /**
     * 验证码类型
     */
    public enum CaptchaType {
        adminLogin("管理员登陆"),
        other("其他");
        private String desc;

        CaptchaType(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 账号锁定类型
     */
    public enum AccountLockType {
        admin("管理员");
        private String desc;

        AccountLockType(String desc) {
            this.desc = desc;
        }
    }

    //网站是否启用
    private Boolean isSiteEnabled;
    //静态资源路径
    private String staticUrl;
    //项目根路径
    private String rootPath;
    //cookie路径
    private String cookiePath;
    //cookie作用域
    private String cookieDomain;
    //验证码类型
    private CaptchaType[] captchaTypes;
    //账号锁定类型
    private AccountLockType[] accountLockTypes;
    //账号锁定时长（分钟）
    private Integer accountLockTime;
    //账号锁定最大失败次数
    private Integer accountLockCount;

    public Boolean getIsSiteEnabled() {
        return isSiteEnabled;
    }

    public void setIsSiteEnabled(Boolean isSiteEnabled) {
        this.isSiteEnabled = isSiteEnabled;
    }

    public String getCookiePath() {
        return cookiePath;
    }

    public void setCookiePath(String cookiePath) {
        this.cookiePath = cookiePath;
    }

    public String getCookieDomain() {
        return cookieDomain;
    }

    public void setCookieDomain(String cookieDomain) {
        this.cookieDomain = cookieDomain;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getStaticUrl() {
        return staticUrl;
    }

    public void setStaticUrl(String staticUrl) {
        this.staticUrl = staticUrl;
    }

    public CaptchaType[] getCaptchaTypes() {
        return captchaTypes;
    }

    public void setCaptchaTypes(CaptchaType[] captchaTypes) {
        this.captchaTypes = captchaTypes;
    }

    public AccountLockType[] getAccountLockTypes() {
        return accountLockTypes;
    }

    public void setAccountLockTypes(AccountLockType[] accountLockTypes) {
        this.accountLockTypes = accountLockTypes;
    }

    public Integer getAccountLockTime() {
        return accountLockTime;
    }

    public void setAccountLockTime(Integer accountLockTime) {
        this.accountLockTime = accountLockTime;
    }

    public Integer getAccountLockCount() {
        return accountLockCount;
    }

    public void setAccountLockCount(Integer accountLockCount) {
        this.accountLockCount = accountLockCount;
    }
}