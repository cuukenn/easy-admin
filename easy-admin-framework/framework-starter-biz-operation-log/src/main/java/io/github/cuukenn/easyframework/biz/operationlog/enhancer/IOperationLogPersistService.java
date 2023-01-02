package io.github.cuukenn.easyframework.biz.operationlog.enhancer;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpMethod;

/**
 * @author changgg
 */
public interface IOperationLogPersistService {
	/**
	 * 处理操作记录
	 *
	 * @param uri              资源定位符
	 * @param method           URI请求方法
	 * @param operation        文档注解
	 * @param spendTimeInMills 执行耗时
	 * @param params           URI请求参数
	 * @param result           URI请求结果
	 * @param throwable        异常
	 */
	void dealOperationLog(String uri,
						  HttpMethod method,
						  Operation operation,
						  long spendTimeInMills,
						  Object[] params,
						  Object result,
						  Throwable throwable);
}
