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
package io.github.cuukenn.easyframework.core.vo;

import io.github.cuukenn.easyframework.core.exception.BizException;
import io.github.cuukenn.easyframework.core.exception.enums.GlobalResultCode;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author changgg
 */
@Schema(title = "api统一响应载体")
public class ApiResult<T> implements IResult {
	@Schema(title = "状态码")
	private Integer code;
	@Schema(title = "消息")
	private String message;
	@Schema(title = "消息体")
	private T data;

	/**
	 * 成功
	 *
	 * @return ok status
	 */
	public static ApiResult<Void> success() {
		return new ApiResult<>(GlobalResultCode.SUCCESS, null);
	}

	/**
	 * 成功
	 *
	 * @return ok status
	 */
	public static <T> ApiResult<T> success(T data) {
		return new ApiResult<>(GlobalResultCode.SUCCESS, data);
	}

	/**
	 * 失败
	 *
	 * @return failed status
	 */
	public static ApiResult<Void> error() {
		return new ApiResult<>(GlobalResultCode.REQUEST_ERROR, null);
	}

	/**
	 * 失败
	 *
	 * @return ok status
	 */
	public static <T> ApiResult<T> error(T data) {
		return new ApiResult<>(GlobalResultCode.REQUEST_ERROR, data);
	}

	public static ApiResult<Void> error(IResult resultCode) {
		return new ApiResult<>(resultCode, null);
	}

	public static <T> ApiResult<T> error(IResult resultCode, T data) {
		return new ApiResult<>(resultCode, data);
	}

	public static <T> ApiResult<Void> error(Integer code, String message) {
		return ApiResult.error(new GlobalResultCode.ResultCodeWrapper(code, message));
	}

	protected ApiResult(IResult resultCode, T data) {
		this.code = resultCode.getCode();
		this.message = resultCode.getMessage();
		this.data = data;
	}

	/**
	 * 校验结果
	 */
	public void check() {
		if (!getCode().equals(GlobalResultCode.SUCCESS.getCode())) {
			throw new BizException(getCode(), getMessage());
		}
	}

	@Override
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
