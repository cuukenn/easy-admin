/*
 * Copyright 2022 the original author or authors.
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
package io.github.cuukenn.easyframework.captcha.core.configure;

import io.github.cuukenn.easyframework.captcha.core.properties.CaptchaProperties;
import io.github.cuukenn.easyframework.captcha.core.service.ICaptchaService;
import io.github.cuukenn.easyframework.captcha.core.service.impl.InCacheCaptchaServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author changgg
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(CaptchaProperties.class)
public class CaptchaServiceConfiguration {
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = CaptchaProperties.PREFIX, name = "mode", value = "cache", matchIfMissing = true)
	public ICaptchaService captchaService() {
		return new InCacheCaptchaServiceImpl();
	}
}
