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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author changgg
 */
@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
	@Resource
	private List<AuthorizeRequestsCustomizer> authorizeRequestsCustomizers;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement(AbstractHttpConfigurer::disable)
			.csrf(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.rememberMe(AbstractHttpConfigurer::disable)
			// 放行OPTIONS请求
			.authorizeRequests(auth -> auth.antMatchers(HttpMethod.OPTIONS, "/**").permitAll())
			// 模块自定义配置
			.authorizeRequests(auth -> authorizeRequestsCustomizers.forEach(customizer -> customizer.customize(auth)))
			// 其他接口默认拦截
			.authorizeRequests(auth -> auth.anyRequest().authenticated());
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
