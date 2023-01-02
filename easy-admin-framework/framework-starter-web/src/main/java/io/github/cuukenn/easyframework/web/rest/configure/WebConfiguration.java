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
package io.github.cuukenn.easyframework.web.rest.configure;

import io.github.cuukenn.easyframework.web.rest.RestUriPath;
import io.github.cuukenn.easyframework.web.rest.WebRestUriPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author changgg
 */
@SuppressWarnings("NullableProblems")
@Configuration(proxyBeanMethods = false)
public class WebConfiguration implements WebMvcConfigurer {
	private static final Logger logger = LoggerFactory.getLogger(WebConfiguration.class);
	@Resource
	private ObjectProvider<RestUriPath> uriPaths;

	@SuppressWarnings("NullableProblems")
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		this.configurePathMatch(configurer, WebRestUriPath.ADMIN);
		uriPaths.forEach(path -> this.configurePathMatch(configurer, path));
	}

	private void configurePathMatch(PathMatchConfigurer configurer, RestUriPath path) {
		logger.info("[register uri matcher][uriPrefix:[{}],uriPattern:[{}]]", path.getPrefix(), path.getRestPattern());
		AntPathMatcher matcher = new AntPathMatcher(".");
		configurer.addPathPrefix(path.getPrefix(), clazz -> clazz.isAnnotationPresent(RestController.class)
			&& matcher.match(path.getRestPattern(), clazz.getPackage().getName()));
	}
}
