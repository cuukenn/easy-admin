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
package io.github.cuukenn.easyframework.tenant.core.aop.advisor;


import io.github.cuukenn.easyframework.tenant.core.aop.TenantIgnore;
import io.github.cuukenn.easyframework.tenant.core.aop.context.TenantIgnoreContext;
import io.github.cuukenn.easyframework.tenant.core.aop.context.TenantIgnoreContextHolder;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.lang.NonNull;

/**
 * @author changgg
 */
public class TenantIgnoreInterceptor implements MethodInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(TenantIgnoreInterceptor.class);

	@Override
	public Object invoke(@NonNull MethodInvocation invocation) throws Throwable {
		final TenantIgnoreContext context = TenantIgnoreContextHolder.push(determineDatabaseInstance(invocation));
		if (logger.isDebugEnabled()) {
			logger.debug("tenantIgnoreContext is [{}]", context);
		}
		try {
			return invocation.proceed();
		} finally {
			TenantIgnoreContextHolder.poll();
		}
	}

	protected TenantIgnoreContext determineDatabaseInstance(MethodInvocation invocation) {
		TenantIgnore tenantIgnore = AnnotatedElementUtils.findMergedAnnotation(invocation.getMethod(), TenantIgnore.class);
		if (tenantIgnore == null) {
			tenantIgnore = AnnotatedElementUtils.findMergedAnnotation(invocation.getMethod().getDeclaringClass(), TenantIgnore.class);
		}
		TenantIgnoreContext context = new TenantIgnoreContext();
		if (tenantIgnore != null) {
			context.setIgnore(true);
		}
		return context;
	}
}
