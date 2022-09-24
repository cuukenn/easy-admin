package com.cuukenn.cloud.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * @author changgg
 */
@ConfigurationProperties(prefix = "app.captcha")
public class CaptchaProperties {
    /**
     * 保存方式
     */
    private String mode;
    /**
     * 键前缀
     */
    private String keyPrefix;
    /**
     * 默认过期时间
     */
    private Duration timeout;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public Duration getTimeout() {
        return timeout;
    }

    public void setTimeout(Duration timeout) {
        this.timeout = timeout;
    }
}
