package com.cuukenn.openstudysource.cloud.common;

import com.cuukenn.openstudysource.cloud.common.dto.GlobalFeignResponseDecoder;
import feign.codec.Decoder;
import feign.optionals.OptionalDecoder;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;

/**
 * @author changgg
 */
public class FeignClientConfiguration {
    @Bean
    public Decoder feignDecoder(ObjectProvider<HttpMessageConverters> messageConverters) {
        return new OptionalDecoder((new ResponseEntityDecoder(new GlobalFeignResponseDecoder(new SpringDecoder(messageConverters)))));
    }
}
