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
package io.github.cuukenn.easyframework.tenant.core.properties;

import io.github.cuukenn.easyframework.core.EasyFrameworkConstant;
import io.github.cuukenn.easyframework.tenant.core.aop.properties.TenantAopProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import static io.github.cuukenn.easyframework.tenant.core.properties.TenantProperties.PREFIX;

/**
 * @author changgg
 */
@ConfigurationProperties(prefix = PREFIX)
public class TenantProperties {
	public static final String PREFIX = EasyFrameworkConstant.PROPERTIED_PREFIX + "tenant";
	@NestedConfigurationProperty
	private TenantAopProperties aop = new TenantAopProperties();

	public TenantAopProperties getAop() {
		return aop;
	}

	public void setAop(TenantAopProperties aop) {
		this.aop = aop;
	}
}
