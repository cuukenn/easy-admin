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
package io.github.cuukenn.easyframework.web.accesslog.filter;

import io.github.cuukenn.easyframework.web.properties.WebProperties;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * @author changgg
 */
public abstract class AbstractApiPerRequestFilter extends OncePerRequestFilter {
	protected WebProperties properties;

	public AbstractApiPerRequestFilter(WebProperties properties) {
		this.properties = properties;
	}

	protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
		final String uri = request.getRequestURI();
		return properties.getAccessLog().getApiPrefix().stream().anyMatch(apiPrefix -> StringUtils.startsWithIgnoreCase(uri, apiPrefix));
	}
}
