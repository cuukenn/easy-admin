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
package io.github.cuukenn.easyframework.tenant;

import io.github.cuukenn.easyframework.core.constants.AutoConfigurationOrderConstant;
import io.github.cuukenn.easyframework.tenant.core.aop.configure.AopConfiguration;
import io.github.cuukenn.easyframework.tenant.core.properties.TenantProperties;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author changgg
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = TenantProperties.PREFIX, name = "enable", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(TenantProperties.class)
@Import(AopConfiguration.class)
@AutoConfigureOrder(AutoConfigurationOrderConstant.BIZ_TENANT)
public class TenantAutoConfiguration {
}
