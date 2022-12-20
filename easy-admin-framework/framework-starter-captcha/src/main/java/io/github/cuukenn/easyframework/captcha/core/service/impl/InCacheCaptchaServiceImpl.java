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
package io.github.cuukenn.easyframework.captcha.core.service.impl;


import io.github.cuukenn.easyframework.captcha.core.service.ICaptchaService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.UUID;

/**
 * 内存验证码存储器
 *
 * @author changgg
 */
public class InCacheCaptchaServiceImpl implements ICaptchaService {
	private static final String CACHE_NAME = "captcha-code";

	@Override
	@Caching(
		cacheable = @Cacheable(cacheNames = CACHE_NAME, key = "#id", condition = "false"),
		evict = @CacheEvict(cacheNames = CACHE_NAME, key = "#id")
	)
	public String removeIfAbstract(String id) {
		return null;
	}

	@Override
	@CachePut(cacheNames = "captcha-code", key = "#result", value = "#code")
	public String save(String code) {
		return UUID.randomUUID().toString();
	}
}
