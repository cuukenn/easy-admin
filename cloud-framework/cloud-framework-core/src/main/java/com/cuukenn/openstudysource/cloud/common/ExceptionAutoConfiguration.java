package com.cuukenn.openstudysource.cloud.common;

import com.cuukenn.openstudysource.cloud.common.exception.GlobalExceptionAdvice;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author changgg
 */
@Configuration(proxyBeanMethods = false)
@Import(GlobalExceptionAdvice.class)
public class ExceptionAutoConfiguration {
}