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
package io.github.cuukenn.easyframework.core.context;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author changgg
 */
public final class ContextHolder<Context extends io.github.cuukenn.easyframework.core.context.Context> {
	private final Context defaultContext;
	/**
	 * 为什么要用链表存储(准确的是栈)
	 * <pre>
	 * 为了支持嵌套切换，如ABC三个service都是不同的数据源
	 * 其中A的某个业务要调B的方法，B的方法需要调用C的方法。一级一级调用切换，形成了链。
	 * 传统的只设置当前线程的方式不能满足此业务需求，必须使用栈，后进先出。
	 * </pre>
	 */
	private final ThreadLocal<Deque<Context>> contexts;

	public ContextHolder(String name, Context defaultContext) {
		contexts = new ThreadLocal<Deque<Context>>() {
			@Override
			protected Deque<Context> initialValue() {
				return new ArrayDeque<>();
			}

			@Override
			public String toString() {
				return name;
			}
		};
		this.defaultContext = defaultContext;
	}

	/**
	 * 获得当前线程数据源
	 *
	 * @return 数据源名称
	 */
	public Context peek() {
		return contexts.get().peek();
	}

	/**
	 * 设置当前线程数据源
	 * <p>
	 * 如非必要不要手动调用，调用后确保最终清除
	 * </p>
	 *
	 * @param context 数据源名称
	 */
	public Context push(Context context) {
		if (context == null) {
			context = defaultContext;
		}
		contexts.get().push(context);
		return context;
	}

	/**
	 * 清空当前线程数据源
	 * <p>
	 * 如果当前线程是连续切换数据源 只会移除掉当前线程的数据源名称
	 * </p>
	 */
	public void poll() {
		Deque<Context> deque = contexts.get();
		deque.poll();
		if (deque.isEmpty()) {
			contexts.remove();
		}
	}

	/**
	 * 强制清空本地线程
	 * <p>
	 * 防止内存泄漏，如手动调用了push可调用此方法确保清除
	 * </p>
	 */
	public void clear() {
		contexts.remove();
	}
}
