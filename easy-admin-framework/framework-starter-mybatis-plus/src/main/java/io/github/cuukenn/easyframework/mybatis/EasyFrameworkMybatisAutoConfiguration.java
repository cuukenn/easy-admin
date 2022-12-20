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
package io.github.cuukenn.easyframework.mybatis;

import io.github.cuukenn.easyframework.core.api.CurrentUserService;
import io.github.cuukenn.easyframework.mybatis.core.configure.MyBatisPlusConfigure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author changgg
 */
@Configuration(proxyBeanMethods = false)
@Import(value = MyBatisPlusConfigure.class)
public class EasyFrameworkMybatisAutoConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(EasyFrameworkMybatisAutoConfiguration.class);

	@Bean
	@ConditionalOnMissingBean
	public CurrentUserService defaultCurrentUser() {
		logger.warn("[defaultCurrentUser][no current user bean found,register default]");
		return () -> -1L;
	}
}
