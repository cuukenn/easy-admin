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
package io.github.cuukenn.dynamic.database.mongodb.support;

import io.github.cuukenn.easyframework.core.context.Context;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author changgg
 */
public interface DynamicMongoContext extends Context {
	/**
	 * 数值转化处理器
	 *
	 * @param invocation  函数上下文
	 * @param valueParser 执行器
	 */
	default void parseValue(MethodInvocation invocation, DynamicContextValueParser valueParser) {
	}

	/**
	 * 获取实例ID
	 *
	 * @return 实例ID
	 */
	String getInstanceId();

	/**
	 * 获取库名
	 *
	 * @return 库名
	 */
	String getDatabase();
}
