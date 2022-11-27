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
package io.github.cuukenn.easyframework.web.rest.handler;

import io.github.cuukenn.easyframework.core.exception.BizException;
import io.github.cuukenn.easyframework.core.exception.ServerException;
import io.github.cuukenn.easyframework.core.exception.enums.GlobalResultCode;
import io.github.cuukenn.easyframework.core.vo.ApiPageResult;
import io.github.cuukenn.easyframework.core.vo.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.nio.file.AccessDeniedException;

/**
 * @author changgg
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * 处理所有异常，主要是提供给 Filter 使用
	 * 因为 Filter 不走 SpringMVC 的流程，但是我们又需要兜底处理异常，所以这里提供一个全量的异常处理过程，保持逻辑统一。
	 *
	 * @param request 请求
	 * @param ex      异常
	 * @return 通用返回
	 */
	public ApiResult<Void> allExceptionHandler(HttpServletRequest request, Throwable ex) {
		if (ex instanceof MissingServletRequestParameterException) {
			return handleException((MissingServletRequestParameterException) ex);
		}
		if (ex instanceof MethodArgumentTypeMismatchException) {
			return handleException((MethodArgumentTypeMismatchException) ex);
		}
		if (ex instanceof MethodArgumentNotValidException) {
			return handleException((MethodArgumentNotValidException) ex);
		}
		if (ex instanceof BindException) {
			return handleException((BindException) ex);
		}
		if (ex instanceof ConstraintViolationException) {
			return handleException((ConstraintViolationException) ex);
		}
		if (ex instanceof ValidationException) {
			return handleException((ValidationException) ex);
		}
		if (ex instanceof NoHandlerFoundException) {
			return handleException((NoHandlerFoundException) ex);
		}
		if (ex instanceof HttpRequestMethodNotSupportedException) {
			return handleException((HttpRequestMethodNotSupportedException) ex);
		}
		if (ex instanceof BizException) {
			return handleException((BizException) ex);
		}
		if (ex instanceof AccessDeniedException) {
			return handleException(request, (AccessDeniedException) ex);
		}
		return defaultHandleException(request, ex);
	}

	/**
	 * 处理 SpringMVC 请求参数缺失
	 * <p>
	 * 例如说，接口上设置了 @RequestParam("xx") 参数，结果并未传递 xx 参数
	 */
	@ExceptionHandler(value = MissingServletRequestParameterException.class)
	public ApiResult<Void> handleException(MissingServletRequestParameterException ex) {
		logger.error("[missingServletRequestParameterExceptionHandler]", ex);
		return ApiResult.error(GlobalResultCode.BAD_REQUEST.getCode(), String.format("请求参数缺失:%s", ex.getParameterName()));
	}

	/**
	 * 处理 SpringMVC 请求参数类型错误
	 * <p>
	 * 例如说，接口上设置了 @RequestParam("xx") 参数为 Integer，结果传递 xx 参数类型为 String
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ApiResult<Void> handleException(MethodArgumentTypeMismatchException ex) {
		logger.error("[missingServletRequestParameterExceptionHandler]", ex);
		return ApiResult.error(GlobalResultCode.BAD_REQUEST.getCode(), String.format("请求参数类型错误:%s", ex.getMessage()));
	}

	/**
	 * 处理 SpringMVC 参数校验不正确
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ApiResult<Void> handleException(MethodArgumentNotValidException ex) {
		logger.error("[methodArgumentNotValidExceptionExceptionHandler]", ex);
		FieldError fieldError = ex.getBindingResult().getFieldError();
		return ApiResult.error(GlobalResultCode.BAD_REQUEST.getCode(), String.format("请求参数不正确:%s", fieldError != null ? fieldError.getDefaultMessage() : null));
	}

	/**
	 * 处理 SpringMVC 参数绑定不正确，本质上也是通过 Validator 校验
	 */
	@ExceptionHandler(BindException.class)
	public ApiResult<Void> handleException(BindException ex) {
		logger.error("[handleBindException]", ex);
		FieldError fieldError = ex.getFieldError();
		return ApiResult.error(GlobalResultCode.BAD_REQUEST.getCode(), String.format("请求参数不正确:%s", fieldError != null ? fieldError.getDefaultMessage() : null));
	}

	/**
	 * 处理 Validator 校验不通过产生的异常
	 */
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ApiResult<Void> handleException(ConstraintViolationException ex) {
		logger.error("[constraintViolationExceptionHandler]", ex);
		ConstraintViolation<?> constraintViolation = ex.getConstraintViolations().iterator().next();
		return ApiResult.error(GlobalResultCode.BAD_REQUEST.getCode(), String.format("请求参数不正确:%s", constraintViolation.getMessage()));
	}

	/**
	 * 处理 ValidationException 异常
	 */
	@ExceptionHandler(value = ValidationException.class)
	public ApiResult<Void> handleException(ValidationException ex) {
		logger.error("[constraintViolationExceptionHandler]", ex);
		// 无法拼接明细的错误信息，因为 Dubbo Consumer 抛出 ValidationException 异常时，是直接的字符串信息，且人类不可读
		return ApiResult.error(GlobalResultCode.BAD_REQUEST);
	}

	/**
	 * 处理 SpringMVC 请求地址不存在
	 * 注意，它需要设置如下两个配置项：
	 * 1. spring.mvc.throw-exception-if-no-handler-found 为 true
	 * 2. spring.mvc.static-path-pattern 为 /statics/**
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public ApiResult<Void> handleException(NoHandlerFoundException ex) {
		logger.error("[noHandlerFoundExceptionHandler]", ex);
		return ApiResult.error(GlobalResultCode.NOT_FOUND.getCode(), String.format("请求地址不存在:%s", ex.getRequestURL()));
	}

	/**
	 * 处理 SpringMVC 请求方法不正确
	 * <p>
	 * 例如说，A 接口的方法为 GET 方式，结果请求方法为 POST 方式，导致不匹配
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ApiResult<Void> handleException(HttpRequestMethodNotSupportedException ex) {
		logger.error("[httpRequestMethodNotSupportedExceptionHandler]", ex);
		return ApiResult.error(GlobalResultCode.METHOD_NOT_ALLOWED.getCode(), String.format("请求方法不正确:%s", ex.getMessage()));
	}

	/**
	 * 处理 Spring Security 权限不足的异常
	 * <p>
	 * 来源是，使用 @PreAuthorize 注解，AOP 进行权限拦截
	 */
	@ExceptionHandler(value = AccessDeniedException.class)
	public ApiResult<Void> handleException(HttpServletRequest req, AccessDeniedException ex) {
		logger.error("[accessDeniedExceptionHandler][userId({}) 无法访问 url({})]", 1L, req.getRequestURL(), ex);
		return ApiResult.error(GlobalResultCode.FORBIDDEN);
	}

	/**
	 * 处理业务异常
	 *
	 * @param exception exception
	 * @return 响应数据
	 */
	@ExceptionHandler(BizException.class)
	public ApiResult<Void> handleException(BizException exception) {
		logger.error("[biz exception][code:[{}], message:[{}]]", exception.getCode(), exception.getMessage());
		return ApiPageResult.error(exception);
	}

	/**
	 * 处理服务异常
	 *
	 * @param exception exception
	 * @return 响应数据
	 */
	@ExceptionHandler(ServerException.class)
	public ApiResult<Void> handleException(ServerException exception) {
		logger.error("[serverExceptionHandler][message:[{}]]", exception.getMessage(), exception);
		return ApiPageResult.error(GlobalResultCode.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 处理未知异常
	 *
	 * @param req       req
	 * @param exception exception
	 * @return 响应数据
	 */
	@ExceptionHandler(Exception.class)
	public ApiResult<Void> defaultHandleException(HttpServletRequest req, Throwable exception) {
		logger.error("[defaultHandleException][message:[{}]]", exception.getMessage(), exception);
		return ApiPageResult.error(GlobalResultCode.UNKNOWN);
	}
}
