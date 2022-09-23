package com.cuukenn.openstudysource.cloud.framework.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author changgg
 */
@ConfigurationProperties(prefix = "app.token")
public class TokenProperties {
    /**
     * 请求头名称
     */
    private String tokenHeader = "X-Auth-Token";
    /**
     * 请求头值前缀
     */
    private String tokenPrefix = "";
    /**
     * 记住我时的session过期时间
     */
    private int rememberMeInvalidTimeOfHours;
    /**
     * 一个用户最大同时登录的次数(负数表示无限制)
     */
    private int maximumSessions;

    public int getMaximumSessions() {
        return maximumSessions;
    }

    public void setMaximumSessions(int maximumSessions) {
        this.maximumSessions = maximumSessions;
    }

    public int getRememberMeInvalidTimeOfHours() {
        return rememberMeInvalidTimeOfHours;
    }

    public void setRememberMeInvalidTimeOfHours(int rememberMeInvalidTimeOfHours) {
        this.rememberMeInvalidTimeOfHours = rememberMeInvalidTimeOfHours;
    }

    public String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }
}
