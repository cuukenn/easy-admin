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
package io.github.cuukenn.easyframework.redis.cache.configure;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.LoggingCacheErrorHandler;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author changgg
 */
@Configuration(proxyBeanMethods = false)
public class RedisCacheConfiguration extends CachingConfigurerSupport {
	private static final Logger logger = LoggerFactory.getLogger(RedisCacheConfiguration.class);

	@Override
	public KeyGenerator keyGenerator() {
		logger.info("[register keyGenerator]");
		return (target, method, params) -> {
			Map<String, Object> container = new HashMap<>(3);
			Class<?> targetClassClass = AopUtils.isAopProxy(target) ? AopUtils.getTargetClass(target) : target.getClass();
			// 类名
			container.put("class", targetClassClass.toGenericString());
			// 方法名称
			container.put("methodName", method.getName());
			// 参数列表
			for (int i = 0; i < params.length; i++) {
				container.put(String.valueOf(i), params[i]);
			}
			// 转为字符串
			String jsonString = JSONUtil.toJsonStr(container);
			// 做SHA256 Hash计算，得到一个SHA256摘要作为Key
			return DigestUtil.sha256Hex(jsonString);
		};
	}

	@Override
	public CacheErrorHandler errorHandler() {
		logger.info("[register errorHandler]");
		return new LoggingCacheErrorHandler();
	}
}
