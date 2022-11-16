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
package io.github.cuukenn.easyframework.caffeine;

import io.github.cuukenn.easyframework.caffeine.jmx.configure.JmxConfiguration;
import io.github.cuukenn.easyframework.caffeine.spring.configure.SpringConfiguration;
import io.github.cuukenn.easyframework.caffeine.spring.processor.CaffeineStatsPostProcessor;
import io.github.cuukenn.easyframework.core.EasyFrameworkConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author changgg
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(CaffeineCache.class)
@ConditionalOnProperty(prefix = EasyFrameworkConstant.PROPERTIED_PREFIX + "caffeine.mbean", name = "enable", havingValue = "true")
@Import({JmxConfiguration.class, SpringConfiguration.class})
public class EasyFrameworkCaffeineSamplerMxBeanAutoConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(EasyFrameworkCaffeineSamplerMxBeanAutoConfiguration.class);

	@Bean
	public BeanPostProcessor caffeineStatProcessor() {
		logger.info("register caffeineStatProcessor");
		return new CaffeineStatsPostProcessor();
	}
}
