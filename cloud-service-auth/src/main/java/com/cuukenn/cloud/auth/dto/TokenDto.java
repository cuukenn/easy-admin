package com.cuukenn.cloud.auth.dto;

import com.cuukenn.openstudysource.cloud.framework.dto.Dto;

/**
 * @author changgg
 */
public class TokenDto extends Dto {
    private static final long serialVersionUID = -3414509553446023554L;
    /**
     * 令牌header前缀
     */
    private String tokenHeaderName;
    /**
     * 令牌值前缀
     */
    private String tokenPrefix;
    /**
     * 令牌
     */
    private String token;

    public TokenDto() {
    }

    public TokenDto(String tokenHeaderName, String tokenPrefix, String token) {
        this.tokenHeaderName = tokenHeaderName;
        this.tokenPrefix = tokenPrefix;
        this.token = token;
    }

    public String getTokenHeaderName() {
        return tokenHeaderName;
    }

    public void setTokenHeaderName(String tokenHeaderName) {
        this.tokenHeaderName = tokenHeaderName;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
