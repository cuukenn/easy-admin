package io.github.cuukenn.easyframework.biz.operationlog.enhancer;

import io.github.cuukenn.easyframework.biz.operationlog.enhancer.context.IgnoreOperationLogContextHolder;
import io.github.cuukenn.easyframework.web.rest.WebRestUriPath;
import io.github.cuukenn.easyframework.web.toolkit.WebUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author changgg
 */
@Aspect
public class OperationLogAdviser {
	private final PathMatcher matcher = new AntPathMatcher(WebRestUriPath.ADMIN.getPrefixPattern());
	@Resource
	private IOperationLogPersistService operationLogService;

	/**
	 * restful类才处理
	 */
	@Pointcut("@target(org.springframework.stereotype.Controller) || @target(org.springframework.web.bind.annotation.RestController)")
	public void isController() {
	}

	/**
	 * uri为修改动作时进行记录
	 */
	@Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.PutMapping) || @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
	public void isChangeOperation() {
	}

	@Around("isController() && isChangeOperation() && @annotation(operation)")
	public Object around(ProceedingJoinPoint invocation, Operation operation) throws Throwable {
		HttpServletRequest request = WebUtil.getRequest();
		//1.1、判断是否需要处理此请求
		if (matcher.isPattern(request.getRequestURI())) {
			return invocation.proceed();
		}
		//1.2、对于可忽略API，不进行处理
		if (IgnoreOperationLogContextHolder.isIgnore()) {
			return invocation.proceed();
		}
		//2、初始化相关数据
		Object ret = null;
		final StopWatch stopWatch = new StopWatch();
		//3、执行API
		Throwable exception = null;
		try {
			stopWatch.start();
			ret = invocation.proceed();
		} catch (Throwable throwable) {
			exception = throwable;
			throw throwable;
		} finally {
			stopWatch.stop();
			//4.将结果和相关信息提交给service生成操作日志
			operationLogService.dealOperationLog(
				request.getRequestURI(),
				HttpMethod.resolve(request.getMethod()), operation, stopWatch.getLastTaskTimeMillis(),
				invocation.getArgs(),
				ret,
				exception
			);
		}
		return ret;
	}
}
