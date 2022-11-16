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
package io.github.cuukenn.easyframework.core.exception.enums;

import io.github.cuukenn.easyframework.core.pojo.IResult;

/**
 * @author changgg
 */
public enum GlobalResultCode implements IResult {
	/**
	 * OK
	 */
	SUCCESS(200, "成功"),

	// ========== 客户端错误段 ==========
	/**
	 * 请求参数不正确
	 */
	BAD_REQUEST(400, "请求参数不正确"),
	/***
	 *账号未登录
	 */
	UNAUTHORIZED(401, "账号未登录"),
	/***
	 *没有该操作权限
	 */
	FORBIDDEN(403, "没有该操作权限"),
	/***
	 *请求未找到
	 */
	NOT_FOUND(404, "请求未找到"),
	/***
	 *请求方法不正确
	 */
	METHOD_NOT_ALLOWED(405, "请求方法不正确"),
	/***
	 *请求失败，请稍后重试
	 */
	REQUEST_ERROR(423, "请求失败，请稍后重试"),
	/***
	 *请求过于频繁，请稍后重试
	 */
	TOO_MANY_REQUESTS(429, "请求过于频繁，请稍后重试"),
	// ========== 服务端错误段 ==========
	/***
	 *系统异常
	 */
	INTERNAL_SERVER_ERROR(500, "系统异常"),
	/***
	 *重复请求，请稍后重试
	 */
	// ========== 自定义错误段 ==========
	REPEATED_REQUESTS(900, "重复请求，请稍后重试"),
	/***
	 *演示模式，禁止写操作
	 */
	DEMO_DENY(901, "演示模式，禁止写操作"),
	/***
	 *未知错误
	 */
	UNKNOWN(999, "未知错误");
	private final Integer code;
	private final String message;

	GlobalResultCode(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * 创建自定义错误
	 *
	 * @param code    代码
	 * @param message 消息
	 * @return result code
	 */
	public static IResult buildResultCode(Integer code, String message) {
		return new ResultCodeWrapper(code, message);
	}

	public static class ResultCodeWrapper implements IResult {
		private final Integer code;
		private final String message;

		public ResultCodeWrapper(Integer code, String message) {
			this.code = code;
			this.message = message;
		}

		@Override
		public Integer getCode() {
			return code;
		}

		@Override
		public String getMessage() {
			return message;
		}
	}

	@Override
	public Integer getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
