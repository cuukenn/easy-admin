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
package io.github.cuukenn.easyframework.captcha.core.properties;

import io.github.cuukenn.easyframework.core.constants.EasyFrameworkConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

import static io.github.cuukenn.easyframework.captcha.core.properties.CaptchaProperties.PREFIX;

/**
 * @author changgg
 */
@ConfigurationProperties(prefix = PREFIX)
public class CaptchaProperties {
	public static final String PREFIX = EasyFrameworkConstant.PROPERTIED_PREFIX + "captcha";
	/**
	 * 保存方式
	 */
	private String mode;
	/**
	 * 键前缀
	 */
	private String keyPrefix;
	/**
	 * 默认过期时间
	 */
	private Duration timeout;

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getKeyPrefix() {
		return keyPrefix;
	}

	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}

	public Duration getTimeout() {
		return timeout;
	}

	public void setTimeout(Duration timeout) {
		this.timeout = timeout;
	}
}
