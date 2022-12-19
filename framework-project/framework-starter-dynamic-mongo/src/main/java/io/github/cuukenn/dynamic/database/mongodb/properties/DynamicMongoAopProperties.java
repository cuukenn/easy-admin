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
package io.github.cuukenn.dynamic.database.mongodb.properties;

import org.springframework.core.Ordered;

/**
 * 多数据源aop相关配置
 *
 * @author TaoYu
 */
public class DynamicMongoAopProperties {

	/**
	 * enabled default DS annotation default true
	 */
	private Boolean enabled = true;
	/**
	 * aop order
	 */
	private Integer order = Ordered.HIGHEST_PRECEDENCE;
	/**
	 * aop allowedPublicOnly
	 */
	private Boolean allowedPublicOnly = true;

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Boolean getAllowedPublicOnly() {
		return allowedPublicOnly;
	}

	public void setAllowedPublicOnly(Boolean allowedPublicOnly) {
		this.allowedPublicOnly = allowedPublicOnly;
	}
}
