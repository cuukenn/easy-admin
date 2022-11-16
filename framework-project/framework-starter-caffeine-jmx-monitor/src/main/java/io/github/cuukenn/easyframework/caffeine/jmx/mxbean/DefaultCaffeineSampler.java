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
package io.github.cuukenn.easyframework.caffeine.jmx.mxbean;

import com.github.benmanes.caffeine.cache.stats.CacheStats;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;

/**
 * 默认的caffeine监控信息mbean实现
 *
 * @author changgg
 */
public class DefaultCaffeineSampler implements CaffeineSamplerMxBean {
	private final String cacheName;
	private final CacheManager cacheManager;

	public DefaultCaffeineSampler(String cacheName, CacheManager cacheManager) {
		this.cacheName = cacheName;
		this.cacheManager = cacheManager;
	}

	/**
	 * 获取caffeine cache实例
	 *
	 * @return caffeine cache状态管理器,非空
	 */
	private CacheStats getCacheStats() {
		org.springframework.cache.Cache wrappedCache = this.cacheManager.getCache(cacheName);
		if (wrappedCache instanceof CaffeineCache) {
			return ((CaffeineCache) wrappedCache).getNativeCache().stats();
		}
		return CacheStats.empty();
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public String getCacheName() {
		return cacheName;
	}

	@Override
	public long getRequestCount() {
		return this.getCacheStats().requestCount();
	}

	@Override
	public long getHitCount() {
		return this.getCacheStats().hitCount();
	}

	@Override
	public long getMissCount() {
		return this.getCacheStats().missCount();
	}

	@Override
	public long getLoadSuccessCount() {
		return this.getCacheStats().loadSuccessCount();
	}

	@Override
	public long getLoadFailureCount() {
		return this.getCacheStats().loadFailureCount();
	}

	@Override
	public double getLoadFailureRate() {
		return this.getCacheStats().loadFailureRate();
	}

	@Override
	public long getTotalLoadTime() {
		return this.getCacheStats().totalLoadTime();
	}

	@Override
	public long getEvictionCount() {
		return this.getCacheStats().evictionCount();
	}

	@Override
	public long getEvictionWeight() {
		return this.getCacheStats().evictionWeight();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException("清除操作暂未实现");
	}
}
