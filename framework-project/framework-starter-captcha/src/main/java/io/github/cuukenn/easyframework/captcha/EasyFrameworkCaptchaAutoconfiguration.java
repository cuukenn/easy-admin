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
package io.github.cuukenn.easyframework.captcha;

import io.github.cuukenn.easyframework.captcha.core.configure.CaptchaServiceConfiguration;
import io.github.cuukenn.easyframework.captcha.core.properties.CaptchaProperties;
import io.github.cuukenn.easyframework.captcha.rest.configure.RestConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author changgg
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = CaptchaProperties.PREFIX, value = "enable", havingValue = "true", matchIfMissing = true)
@Import({CaptchaServiceConfiguration.class, RestConfiguration.class})
public class EasyFrameworkCaptchaAutoconfiguration {
}
