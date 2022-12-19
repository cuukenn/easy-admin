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
package io.github.cuukenn.easyframework.tenant.core.aop.configure;

import io.github.cuukenn.easyframework.tenant.core.aop.TenantIgnore;
import io.github.cuukenn.easyframework.tenant.core.aop.advisor.TenantIgnoreInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author changgg
 */
@Configuration(proxyBeanMethods = false)
public class AopConfiguration {
	@Bean
	public Advisor tenantIgnoreAdvisor() {
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(
			new AnnotationMatchingPointcut(TenantIgnore.class),
			new TenantIgnoreInterceptor());
		advisor.setOrder(1);
		return advisor;
	}
}
