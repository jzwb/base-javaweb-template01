package com.jzwb;

import java.io.Serializable;

/**
 * 系统设置
 */
public class Setting implements Serializable {

    //缓存名称
    public static final String CACHE_NAME = "setting";
    //缓存key
    public static final Integer CACHE_KEY = 0;

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
}