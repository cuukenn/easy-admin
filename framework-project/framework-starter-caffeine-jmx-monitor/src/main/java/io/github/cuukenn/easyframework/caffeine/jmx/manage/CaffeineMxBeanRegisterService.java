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
package io.github.cuukenn.easyframework.caffeine.jmx.manage;

import io.github.cuukenn.easyframework.caffeine.jmx.mxbean.CaffeineMxBeanRegister;
import io.github.cuukenn.easyframework.caffeine.jmx.mxbean.CaffeineSamplerMxBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.management.JMException;

/**
 * @author changgg
 */
@Service
public class CaffeineMxBeanRegisterService implements InitializingBean {
	private static final Logger logger = LoggerFactory.getLogger(CaffeineMxBeanRegisterService.class);
	@Resource
	private CacheManager cacheManager;
	@Resource
	private CaffeineSamplerMxBeanBuilder mxBeanBuilder;

	@Override
	public void afterPropertiesSet() throws Exception {
		cacheManager.getCacheNames().forEach(cacheName -> {
			try {
				Cache cache = cacheManager.getCache(cacheName);
				if (cache instanceof CaffeineCache) {
					CaffeineMxBeanRegister.register(mxBeanBuilder.build(cacheManager, cacheName));
				}
			} catch (JMException e) {
				logger.error("register caffeine sampler failed", e);
			}
		});
	}
}
