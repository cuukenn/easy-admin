package com.cuukenn.openstudysource.cloud.framework.auth;

import com.cuukenn.openstudysource.cloud.framework.auth.config.FrameworkSecurityConfig;
import com.cuukenn.openstudysource.cloud.framework.auth.config.WebMvcConfig;
import com.cuukenn.openstudysource.cloud.user.api.client.UserFeignClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author changgg
 */
@Configuration(proxyBeanMethods = false)
@EnableFeignClients(clients = UserFeignClient.class)
@Import({FrameworkSecurityConfig.class, WebMvcConfig.class})
public class AuthAutoconfiguration {
}
