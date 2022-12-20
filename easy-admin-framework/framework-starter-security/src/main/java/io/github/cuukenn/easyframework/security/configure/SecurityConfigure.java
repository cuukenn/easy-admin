/*
 * Copyright 2022 changgg.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.cuukenn.easyframework.security.configure;

import io.github.cuukenn.easyframework.core.api.CurrentUserService;
import io.github.cuukenn.easyframework.security.core.AuthorizeRequestsCustomizer;
import io.github.cuukenn.easyframework.security.core.JwtAuthenticationFailureHandler;
import io.github.cuukenn.easyframework.security.core.JwtAuthenticationSuccessHandler;
import io.github.cuukenn.easyframework.security.core.SecurityConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author changgg
 */
@Configuration(proxyBeanMethods = false)
public class SecurityConfigure {
	@Bean
	@ConditionalOnMissingBean
	public SecurityConfigurer securityConfigurer() {
		return new SecurityConfigurer();
	}

	@Bean
	public AuthorizeRequestsCustomizer webAuthorizeRequestsCustomizer() {
		return new AuthorizeRequestsCustomizer() {
			@Override
			public void customize(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
				registry.antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll();
				registry.antMatchers("/actuator/**").permitAll();
			}
		};
	}

	/**
	 * 无感知多密码器共存并更新到默认算法
	 *
	 * @return 密码加密器
	 */
	@Bean
	@ConditionalOnMissingBean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	/**
	 * 授权前缀
	 *
	 * @return 授权前缀实例
	 */
	@Bean
	@ConditionalOnMissingBean
	public GrantedAuthorityDefaults grantedAuthorityDefaults() {
		return new GrantedAuthorityDefaults("");
	}

	@Bean
	@ConditionalOnMissingBean
	public AuthenticationSuccessHandler successHandler() {
		return new JwtAuthenticationSuccessHandler();
	}

	@Bean
	@ConditionalOnMissingBean
	public AuthenticationFailureHandler failureHandler() {
		return new JwtAuthenticationFailureHandler();
	}

	@Bean
	public CurrentUserService currentUserService() {
		return () -> 1L;
	}
}
