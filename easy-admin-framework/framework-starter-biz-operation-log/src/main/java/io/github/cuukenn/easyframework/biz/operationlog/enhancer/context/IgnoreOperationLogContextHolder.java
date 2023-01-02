package io.github.cuukenn.easyframework.biz.operationlog.enhancer.context;

import io.github.cuukenn.easyframework.core.context.ContextHolder;

/**
 * @author changgg
 */
public final class IgnoreOperationLogContextHolder {
	private static final IgnoreOperationLogContext DEFAULT_CONTEXT = new IgnoreOperationLogContext();
	private static final ContextHolder<IgnoreOperationLogContext> CONTEXT = new ContextHolder<>("operation-log-ignore", DEFAULT_CONTEXT);

	/**
	 * 是否忽略
	 *
	 * @return 是否忽略
	 */
	public static boolean isIgnore() {
		return CONTEXT.peek() != null;
	}

	/**
	 * 设置忽略
	 */
	public static void ignore() {
		CONTEXT.push(DEFAULT_CONTEXT);
	}

	/**
	 * 取消忽略
	 */
	public static void unIgnore() {
		CONTEXT.poll();
	}
}
