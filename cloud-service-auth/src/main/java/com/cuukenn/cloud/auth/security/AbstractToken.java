package com.cuukenn.cloud.auth.security;

/**
 * @author changgg
 */
public abstract class AbstractToken implements Token {
    private String token;

    @Override
    public String getToken() {
        return token;
    }

    protected void setToken(String token) {
        this.token = token;
    }
}
