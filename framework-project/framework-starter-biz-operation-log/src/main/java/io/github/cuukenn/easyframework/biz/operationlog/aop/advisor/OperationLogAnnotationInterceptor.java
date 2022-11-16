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
package io.github.cuukenn.easyframework.biz.operationlog.aop.advisor;

import io.github.cuukenn.easyframework.biz.operationlog.core.pojo.dao.OperationLog;
import io.github.cuukenn.easyframework.web.toolkit.IpUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author changgg
 */
public class OperationLogAnnotationInterceptor implements MethodInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(OperationLogAnnotationInterceptor.class);

	@Override
	public Object invoke(@NotNull MethodInvocation invocation) throws Throwable {
		final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		//数据操作请求才进行记录
		if (!request.getMethod().equalsIgnoreCase(HttpMethod.POST.name())
			&& !request.getMethod().equalsIgnoreCase(HttpMethod.PUT.name())
			&& !request.getMethod().equalsIgnoreCase(HttpMethod.DELETE.name())
		) {
			return invocation.proceed();
		}
		//TODO 管理员用户才进行记录
		final long start = System.currentTimeMillis();
		Object result = null;
		try {
			result = invocation.proceed();
		} finally {
			final OperationLog operationLog = buildOperationLog(request, invocation, start);
			//TODO recordOperationLog
		}
		return result;
	}

	protected OperationLog buildOperationLog(HttpServletRequest request, MethodInvocation invocation, long start) {
		final long end = System.currentTimeMillis();
		final long duration = end - start;
		final String userIp = IpUtil.getAddress(request);
		final String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
		OperationLog operationLog = new OperationLog();
		operationLog.setUserIp(userIp);
		operationLog.setUserAgent(userAgent);
		operationLog.setStartTime(start);
		operationLog.setUri(request.getRequestURI());
		operationLog.setEndTime(end);
		operationLog.setDuration(duration);
		return operationLog;
	}
}
