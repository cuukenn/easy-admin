package com.cuukenn.openstudysource.cloud.framework.dto;


import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.support.SpringDecoder;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * feign client结果转换
 *
 * @author changgg
 */
@RequiredArgsConstructor
public class GlobalFeignResponseDecoder implements Decoder {
    private final SpringDecoder decoder;

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        Method method = response.request().requestTemplate().methodMetadata().method();
        //如果Feign接口的返回值不是自定义统一响应类型
        boolean notTheSame = !method.getReturnType().isAssignableFrom(Result.class);
        if (notTheSame) {
            //构造一个这个结构类型
            Type newType = new ParameterizedType() {
                @Override
                public Type[] getActualTypeArguments() {
                    return new Type[]{type};
                }

                @Override
                public Type getRawType() {
                    return Result.class;
                }

                @Override
                public Type getOwnerType() {
                    return null;
                }
            };
            Result<?> result = (Result<?>) this.decoder.decode(response, newType);
            result.check();
            //只返回data
            return result.getData();
        }
        return this.decoder.decode(response, type);
    }
}
