package io.github.cuukenn.easyframework.biz.operationlog.enhancer;

import io.github.cuukenn.easyframework.biz.operationlog.enhancer.context.IgnoreOperationLogContextHolder;
import io.github.cuukenn.easyframework.core.annotation.IgnoreOperationLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author changgg
 */
@Aspect
public class IgnoreOperationLogAdviser {
	@Around("@annotation(ignore)")
	public Object around(ProceedingJoinPoint invocation, IgnoreOperationLog ignore) throws Throwable {
		IgnoreOperationLogContextHolder.ignore();
		try {
			return invocation.proceed();
		} finally {
			IgnoreOperationLogContextHolder.unIgnore();
		}
	}
}
