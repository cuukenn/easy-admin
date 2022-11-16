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
package io.github.cuukenn.easyframework.tenant.core.aop.context;

import io.github.cuukenn.easyframework.core.context.ContextHolder;

/**
 * @author changgg
 */
public final class TenantIgnoreContextHolder {
	private static final ContextHolder<TenantIgnoreContext> LOOKUP_KEY_HOLDER = new ContextHolder<>("tenant-ignore", new TenantIgnoreContext());

	private TenantIgnoreContextHolder() {
	}

	/**
	 * 是否忽略租户
	 *
	 * @return 忽略租户
	 */
	public static boolean ignoreTenant() {
		TenantIgnoreContext context = peek();
		if (context == null) {
			return false;
		}
		return context.isIgnore();
	}

	/**
	 * 获得当前线程数据源
	 *
	 * @return 数据源名称
	 */
	public static TenantIgnoreContext peek() {
		return LOOKUP_KEY_HOLDER.peek();
	}

	/**
	 * 设置当前线程数据源
	 * <p>
	 * 如非必要不要手动调用，调用后确保最终清除
	 * </p>
	 *
	 * @param context 数据源名称
	 */
	public static TenantIgnoreContext push(TenantIgnoreContext context) {
		return LOOKUP_KEY_HOLDER.push(context);
	}

	/**
	 * 清空当前线程数据源
	 * <p>
	 * 如果当前线程是连续切换数据源 只会移除掉当前线程的数据源名称
	 * </p>
	 */
	public static void poll() {
		LOOKUP_KEY_HOLDER.poll();
	}

	/**
	 * 强制清空本地线程
	 * <p>
	 * 防止内存泄漏，如手动调用了push可调用此方法确保清除
	 * </p>
	 */
	public static void clear() {
		LOOKUP_KEY_HOLDER.clear();
	}
}
