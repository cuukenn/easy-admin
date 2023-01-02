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
package io.github.cuukenn.easyframework.security.core;

import io.github.cuukenn.easyframework.security.toolkit.SecurityFrameworkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author changgg
 */
@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfigurer.class);
	@Resource
	private List<AuthorizeRequestsCustomizer> authorizeRequestsCustomizers;
	@Resource
	private RequestMappingHandlerMapping requestMappingHandlerMapping;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		Map<HttpMethod, List<String>> ignoreUris = SecurityFrameworkUtil.getPermitAllUrlsFromAnnotations(requestMappingHandlerMapping);
		for (Map.Entry<HttpMethod, List<String>> entry : ignoreUris.entrySet()) {
			logger.info("[security][black uris][method:[{}],uris:[{}]", entry.getKey(), entry.getValue());
		}
		http.sessionManagement(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable).formLogin(AbstractHttpConfigurer::disable).rememberMe(AbstractHttpConfigurer::disable)
			// 放行OPTIONS请求
			.authorizeRequests(auth -> auth.antMatchers(HttpMethod.OPTIONS, "/**").permitAll())
			// 放行无需授权得api
			.authorizeRequests(auth -> auth.antMatchers(HttpMethod.GET, ignoreUris.get(HttpMethod.GET).toArray(new String[0])).permitAll())
			.authorizeRequests(auth -> auth.antMatchers(HttpMethod.POST, ignoreUris.get(HttpMethod.POST).toArray(new String[0])).permitAll())
			.authorizeRequests(auth -> auth.antMatchers(HttpMethod.PUT, ignoreUris.get(HttpMethod.PUT).toArray(new String[0])).permitAll())
			.authorizeRequests(auth -> auth.antMatchers(HttpMethod.DELETE, ignoreUris.get(HttpMethod.DELETE).toArray(new String[0])).permitAll())
			// 模块自定义配置
			.authorizeRequests(auth -> authorizeRequestsCustomizers.forEach(customizer -> {
				logger.info("[security][module customizer setting:[{}]]", customizer.getClass().getSimpleName());
				customizer.customize(auth);
			}))
			// 其他接口默认拦截
			.authorizeRequests(auth -> auth.anyRequest().authenticated());
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
