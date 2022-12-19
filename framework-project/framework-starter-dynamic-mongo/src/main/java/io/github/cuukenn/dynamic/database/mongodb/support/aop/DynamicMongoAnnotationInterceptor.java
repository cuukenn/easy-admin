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
package io.github.cuukenn.dynamic.database.mongodb.support.aop;

import io.github.cuukenn.dynamic.database.mongodb.support.DynamicContextValueParser;
import io.github.cuukenn.dynamic.database.mongodb.support.DynamicMongoContext;
import io.github.cuukenn.dynamic.database.mongodb.support.DynamicMongoContextResolver;
import io.github.cuukenn.dynamic.database.mongodb.support.context.DynamicMongoDatabaseContextHolder;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author changgg
 */
public class DynamicMongoAnnotationInterceptor implements MethodInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(DynamicMongoAnnotationInterceptor.class);
	private final DynamicMongoContextResolver resolver;
	private final DynamicContextValueParser valueParser;

	public DynamicMongoAnnotationInterceptor(DynamicMongoContextResolver resolver, DynamicContextValueParser valueParser) {
		this.resolver = resolver;
		this.valueParser = valueParser;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		final DynamicMongoContext context = DynamicMongoDatabaseContextHolder.push(determineDatabaseInstance(invocation));
		if (logger.isDebugEnabled()) {
			logger.debug("excepted dynamic mongodb instance is [{}]", context);
		}
		try {
			return invocation.proceed();
		} finally {
			DynamicMongoDatabaseContextHolder.poll();
		}
	}

	private DynamicMongoContext determineDatabaseInstance(MethodInvocation invocation) {
		DynamicMongoContext context = resolver.resolve(invocation.getMethod(), invocation.getThis());
		if (context != null) {
			context.parseValue(invocation, valueParser);
		}
		return context;
	}
}
