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
package io.github.cuukenn.easyframework.biz.operationlog;

import io.github.cuukenn.easyframework.biz.operationlog.enhancer.configure.OperationLogAopConfiguration;
import io.github.cuukenn.easyframework.biz.operationlog.properties.OperationLogProperties;
import io.github.cuukenn.easyframework.core.constants.AutoConfigurationOrderConstant;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author changgg
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = OperationLogProperties.PREFIX, name = "enable", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(OperationLogProperties.class)
@Import({OperationLogAopConfiguration.class})
@AutoConfigureOrder(AutoConfigurationOrderConstant.BIZ_OPERATION_LOG)
public class EasyFrameworkOperationLogAutoConfiguration {
}
