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
package io.github.cuukenn.easyframework.caffeine.jmx.configure;

import io.github.cuukenn.easyframework.caffeine.jmx.manage.CaffeineMxBeanRegisterService;
import io.github.cuukenn.easyframework.caffeine.jmx.mxbean.CaffeineSamplerBuilder;
import io.github.cuukenn.easyframework.caffeine.jmx.mxbean.CaffeineSamplerMxBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author changgg
 */
@Configuration(proxyBeanMethods = false)
public class JmxConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(JmxConfiguration.class);

	@Bean
	@ConditionalOnMissingBean(CaffeineSamplerMxBeanBuilder.class)
	public CaffeineSamplerMxBeanBuilder caffeineSamplerMxBeanBuilder() {
		logger.info("register default mxBeanBuilder");
		return new CaffeineSamplerBuilder();
	}

	@Bean
	public CaffeineMxBeanRegisterService caffeineMxBeanRegisterService() {
		logger.info("register caffeineMXBeanRegisterService");
		return new CaffeineMxBeanRegisterService();
	}
}
