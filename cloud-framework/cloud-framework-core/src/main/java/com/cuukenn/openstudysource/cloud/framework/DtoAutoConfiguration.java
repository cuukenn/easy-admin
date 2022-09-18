package com.cuukenn.openstudysource.cloud.framework;

import com.cuukenn.openstudysource.cloud.framework.dto.GlobalResultAdvice;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author changgg
 */
@Configuration(proxyBeanMethods = false)
@Import({GlobalResultAdvice.class})
public class DtoAutoConfiguration {
}
