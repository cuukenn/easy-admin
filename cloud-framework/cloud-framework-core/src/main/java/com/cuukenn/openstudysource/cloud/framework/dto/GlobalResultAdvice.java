package com.cuukenn.openstudysource.cloud.framework.dto;

import cn.dev33.satoken.util.SaResult;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author changgg
 */
@SuppressWarnings("NullableProblems")
@Slf4j
@ControllerAdvice
public class GlobalResultAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return !returnType.getDeclaringClass().getPackageName().startsWith("org.springdoc");
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Result || body instanceof SaResult) {
            return body;
        }
        if (body instanceof String) {
            return JSONUtil.toJsonStr(Result.build(ResultCode.SUCCESS, body));
        }
        return Result.build(ResultCode.SUCCESS, body);
    }
}
