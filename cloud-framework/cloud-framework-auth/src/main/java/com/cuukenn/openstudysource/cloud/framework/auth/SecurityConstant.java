package com.cuukenn.openstudysource.cloud.framework.auth;

/**
 * @author changgg
 */
public final class SecurityConstant {
    private SecurityConstant() {
    }

    public static final String LOGIN_URL = "/login";
    public static final String LOGIN_FAIL_URL = LOGIN_URL + "?error";
    /**
     * 登录后的动作
     */
    public static final String LOGIN_LOGIN_ACTION_PARAM = "login_action";
    /**
     * 重定向url
     */
    public static final String LOGIN_REDIRECT_URL_PARAM = "redirect_url";
    /**
     * 用户名
     */
    public static final String LOGIN_USERNAME_PARAM = "username";
    /**
     * 密码
     */
    public static final String LOGIN_PASSWORD_PARAM = "password";
    /**
     * 记住我
     */
    public static final String LOGIN_REMEMBER_ME_PARAM = "rememberMe";
    /**
     * 验证码id
     */
    public static final String LOGIN_CAPTCHA_ID_PARAM = "captcha_id";
    /**
     * 验证码code
     */
    public static final String LOGIN_CAPTCHA_CODE_PARAM = "captcha_code";
}
