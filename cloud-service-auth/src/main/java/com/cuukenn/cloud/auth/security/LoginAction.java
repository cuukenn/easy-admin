package com.cuukenn.cloud.auth.security;

/**
 * @author changgg
 */
public enum LoginAction {
    /**
     * 重定向
     */
    REDIRECT(1, "redirect"),
    /**
     * 直接返回数据
     */
    JUST_RETURN(2, "just return");
    private final int code;
    private final String desc;

    LoginAction(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static LoginAction fromValue(String code) {
        if (code == null) {
            return LoginAction.JUST_RETURN;
        }
        for (LoginAction action : LoginAction.values()) {
            if (code.equals(action.getCode() + "")) {
                return action;
            }
        }
        return LoginAction.JUST_RETURN;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}