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
package io.github.cuukenn.easyframework.captcha.rest.controller.admin.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 验证码响应vo
 *
 * @author changgg
 */
@Schema(title = "验证码响应vo")
public class CaptchaResVo {
	/**
	 * 验证码ID
	 */
	@JsonProperty("id")
	@Schema(title = "验证码id")
	private final String id;
	/**
	 * 验证码图片base64数据
	 */
	@JsonProperty("img_base64")
	@Schema(title = "验证码图片base64")
	private final String imgBase64;

	public CaptchaResVo(String id, String imgBase64) {
		this.id = id;
		this.imgBase64 = imgBase64;
	}

	public String getId() {
		return id;
	}

	public String getImgBase64() {
		return imgBase64;
	}

	@Override
	public String toString() {
		return "CaptchaDTO{" + "id='" + id + '\'' + ", imgBase64='" + imgBase64 + '\'' + '}';
	}
}
