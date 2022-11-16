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
package io.github.cuukenn.easyframework.captcha.rest.pojo.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 验证码请求
 *
 * @author changgg
 */
public class CaptchaCodeReqVO implements Serializable {
	private static final long serialVersionUID = -3783166218296538723L;
	@NotNull
	@Max(value = 192)
	@Min(value = 130)
	private Integer captchaWidth;
	@NotNull
	@Max(value = 108)
	@Min(value = 48)
	private Integer captchaHeight;

	public CaptchaCodeReqVO(Integer captchaWidth, Integer captchaHeight) {
		this.captchaWidth = captchaWidth;
		this.captchaHeight = captchaHeight;
	}

	public Integer getCaptchaWidth() {
		return captchaWidth;
	}

	public void setCaptchaWidth(Integer captchaWidth) {
		this.captchaWidth = captchaWidth;
	}

	public Integer getCaptchaHeight() {
		return captchaHeight;
	}

	public void setCaptchaHeight(Integer captchaHeight) {
		this.captchaHeight = captchaHeight;
	}

	@Override
	public String toString() {
		return "CaptchaCodeReqVO{" + "captchaWidth=" + captchaWidth + ", captchaHeight=" + captchaHeight + '}';
	}
}
