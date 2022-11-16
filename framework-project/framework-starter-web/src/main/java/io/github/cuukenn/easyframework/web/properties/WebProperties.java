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
package io.github.cuukenn.easyframework.web.properties;

import io.github.cuukenn.easyframework.core.EasyFrameworkConstant;
import io.github.cuukenn.easyframework.web.accesslog.properties.AccessLogProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import static io.github.cuukenn.easyframework.web.properties.WebProperties.PREFIX;

/**
 * @author changgg
 */
@ConfigurationProperties(prefix = PREFIX)
public class WebProperties {
	public static final String PREFIX = EasyFrameworkConstant.PROPERTIED_PREFIX + "web";
	@NestedConfigurationProperty
	private AccessLogProperties accessLog = new AccessLogProperties();

	public AccessLogProperties getAccessLog() {
		return accessLog;
	}

	public void setAccessLog(AccessLogProperties accessLog) {
		this.accessLog = accessLog;
	}
}
