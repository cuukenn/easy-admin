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

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author changgg
 */
public class HeaderValueParser extends AbstractChainValueParser {
	/**
	 * header prefix
	 */
	public static final String HEADER_PREFIX = "#header";

	@Override
	public boolean match(String value) {
		return value.startsWith(HEADER_PREFIX);
	}

	@Override
	public String doParse(MethodInvocation invocation, String value) {
		HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		return request.getHeader(value.substring(HEADER_PREFIX.length()));
	}
}
