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
package io.github.cuukenn.easyframework.biz.operationlog.aop.configure;

import io.github.cuukenn.easyframework.biz.operationlog.aop.advisor.OperationLogAnnotationInterceptor;
import io.github.cuukenn.easyframework.biz.operationlog.properties.OperationLogProperties;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * @author changgg
 */
@Configuration(proxyBeanMethods = false)
public class OperationLogAopConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(OperationLogAopConfiguration.class);

	@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
	@Bean
	public Advisor operationLogAdvisor(OperationLogProperties properties) {
		logger.info("[register operationLogAdvisor aop]");
		OperationLogAnnotationInterceptor interceptor = new OperationLogAnnotationInterceptor();
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new AnnotationMatchingPointcut(Operation.class), interceptor);
		advisor.setOrder(properties.getAop().getOrder());
		return advisor;
	}
}
