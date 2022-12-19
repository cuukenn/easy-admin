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
package io.github.cuukenn.easyframework.caffeine.spring.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.lang.Nullable;

/**
 * 增强配置，开启caffeine的监控
 *
 * @author changgg
 */
public class CaffeineStatsPostProcessor implements BeanPostProcessor {
    private static final String STATS = "recordStats";

    @Override
    public Object postProcessAfterInitialization(@Nullable Object bean, @Nullable String beanName) throws BeansException {
        if (bean instanceof CacheProperties) {
            String spec = ((CacheProperties) bean).getCaffeine().getSpec();
            if (spec != null && !spec.contains(STATS)) {
                ((CacheProperties) bean).getCaffeine().setSpec(spec + "," + STATS);
            }
        }
        return bean;
    }
}
