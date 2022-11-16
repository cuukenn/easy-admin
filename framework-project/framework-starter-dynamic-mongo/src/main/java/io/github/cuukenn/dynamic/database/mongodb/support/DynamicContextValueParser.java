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
package io.github.cuukenn.dynamic.database.mongodb.support;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author changgg
 */
public interface DynamicContextValueParser {
	/**
	 * 是否可以处理此类型
	 *
	 * @param value 待处理字符串
	 * @return 是否支持
	 */
	boolean match(String value);

	/**
	 * 转化数值
	 *
	 * @param invocation 执行器
	 * @param value      待处理字符串
	 * @return 处理完毕的字符串
	 */
	String parse(MethodInvocation invocation, String value);
}
