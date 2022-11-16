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
package io.github.cuukenn.easyframework.captcha.core.service;

import io.github.cuukenn.easyframework.core.exception.BizException;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 验证码字符串存取服务
 *
 * @author changgg
 */
public interface ICaptchaService {
	/**
	 * 校验验证码
	 *
	 * @param id   验证码ID
	 * @param code 输入的验证码
	 */
	default void validate(String id, String code) {
		final String exceptedCode = removeIfAbstract(id);
		if (StringUtils.hasText(code)) {
			throw new BizException(-500, "请输入验证码");
		}
		if (exceptedCode == null) {
			throw new BizException(-501, "验证码已过期");
		}
		if (!Objects.equals(code, exceptedCode)) {
			throw new BizException(-502, "验证码不正确");
		}
	}

	/**
	 * 使用指定ID获取对应的验证码结果
	 * 存在结果将数据清空后进行返回
	 *
	 * @param id 验证码ID
	 * @return 验证码内容{可以为null}
	 */
	String removeIfAbstract(String id);

	/**
	 * 将验证码进行保存
	 *
	 * @param code 验证码
	 * @return 验证码ID
	 */
	String save(String code);
}
