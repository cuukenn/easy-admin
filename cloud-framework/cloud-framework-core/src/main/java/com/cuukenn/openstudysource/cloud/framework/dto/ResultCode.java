package com.cuukenn.openstudysource.cloud.framework.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author changgg
 */
@RequiredArgsConstructor
@Getter
@ToString
public enum ResultCode implements IResult {
    /**
     * 参数错误
     */
    PARAM_ERROR(-300, "PARAM_ERROR"),
    /**
     * 请求失败
     */
    ERROR(-200, "ERROR"),
    /**
     * 请求成功
     */
    SUCCESS(200, "OK");
    private final Integer code;
    private final String message;

    /**
     * 创建自定义错误
     *
     * @param code    代码
     * @param message 消息
     * @return result code
     */
    public static IResult buildResultCode(Integer code, String message) {
        return new ResultCodeWrapper(code, message);
    }

    @RequiredArgsConstructor
    @Data
    @ToString
    public static class ResultCodeWrapper implements IResult {
        private final Integer code;
        private final String message;
    }
}
