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
package io.github.cuukenn.easyframework.biz.operationlog.enhancer.configure;

import io.github.cuukenn.easyframework.biz.operationlog.enhancer.IgnoreOperationLogAdviser;
import io.github.cuukenn.easyframework.biz.operationlog.enhancer.OperationLogAdviser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;

/**
 * @author changgg
 */
@Configuration(proxyBeanMethods = false)
@EnableAspectJAutoProxy
public class OperationLogAopConfiguration {
	@Bean
	@Order(Integer.MAX_VALUE - 1)
	public IgnoreOperationLogAdviser ignoreOperationLogAdviser() {
		return new IgnoreOperationLogAdviser();
	}

	@Bean
	@Order
	public OperationLogAdviser operationLogAdviser() {
		return new OperationLogAdviser();
	}
}
