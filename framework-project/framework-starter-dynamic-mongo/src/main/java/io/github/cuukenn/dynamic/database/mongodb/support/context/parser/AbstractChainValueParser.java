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
package io.github.cuukenn.dynamic.database.mongodb.support.context.parser;

import io.github.cuukenn.dynamic.database.mongodb.support.DynamicContextValueParser;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author changgg
 */
public abstract class AbstractChainValueParser implements DynamicContextValueParser {
	private static final Logger logger = LoggerFactory.getLogger(AbstractChainValueParser.class);
	private DynamicContextValueParser nextParser;

	public void setNextParser(DynamicContextValueParser nextParser) {
		this.nextParser = nextParser;
	}

	@Override
	public String parse(MethodInvocation invocation, String value) {
		final boolean isDebugEnabled = logger.isDebugEnabled();
		if (isDebugEnabled) {
			logger.debug("invocation:{},value:{}", invocation, value);
		}
		String parsedValue = null;
		if (match(value)) {
			parsedValue = doParse(invocation, value);
		}
		if (parsedValue == null && nextParser != null) {
			return nextParser.parse(invocation, value);
		} else {
			return parsedValue;
		}
	}

	/**
	 * 转化数据
	 *
	 * @param invocation invocation
	 * @param value      value
	 * @return 数据
	 */
	protected abstract String doParse(MethodInvocation invocation, String value);
}
