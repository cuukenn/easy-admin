package com.cuukenn.openstudysource.cloud.common.auth;

import com.cuukenn.openstudysource.cloud.user.api.client.IUserFeignClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author changgg
 */
@Configuration(proxyBeanMethods = false)
@EnableFeignClients(clients = IUserFeignClient.class)
@Import({SaTokenConfigure.class, StpInterfaceImpl.class})
public class AuthAutoconfiguration {
}
