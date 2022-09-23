package com.cuukenn.openstudysource.cloud.framework.auth.enums;

/**
 * @author changgg
 **/
public enum RequestMethodEnum {
    /**
     * 搜寻 @AnonymousGetMapping
     */
    GET("GET"),
    /**
     * 搜寻 @AnonymousPostMapping
     */
    POST("POST"),
    /**
     * 搜寻 @AnonymousPutMapping
     */
    PUT("PUT"),
    /**
     * 搜寻 @AnonymousPatchMapping
     */
    PATCH("PATCH"),
    /**
     * 搜寻 @AnonymousDeleteMapping
     */
    DELETE("DELETE"),
    /**
     * 否则就是所有 Request 接口都放行
     */
    ALL("All");
    /**
     * Request 类型
     */
    private final String type;

    RequestMethodEnum(String type) {
        this.type = type;
    }

    public static RequestMethodEnum find(String type) {
        for (RequestMethodEnum value : RequestMethodEnum.values()) {
            if (type.equals(value.getType())) {
                return value;
            }
        }
        return ALL;
    }

    public String getType() {
        return type;
    }
}
