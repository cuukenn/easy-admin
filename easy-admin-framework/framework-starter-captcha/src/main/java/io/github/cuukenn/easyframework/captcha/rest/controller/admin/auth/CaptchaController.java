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
package io.github.cuukenn.easyframework.captcha.rest.controller.admin.auth;

import com.wf.captcha.base.Captcha;
import io.github.cuukenn.easyframework.captcha.core.service.ICaptchaService;
import io.github.cuukenn.easyframework.captcha.core.util.CaptchaUtil;
import io.github.cuukenn.easyframework.captcha.rest.controller.admin.auth.vo.CaptchaResVo;
import io.github.cuukenn.easyframework.captcha.rest.controller.admin.auth.vo.CaptchaCodeReqVo;
import io.github.cuukenn.easyframework.core.vo.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

/**
 * 验证码API
 *
 * @author changgg
 */
@RestController
@RequestMapping("auth/captcha")
@Tag(name = "管理后台-验证码")
public class CaptchaController {
	private final Pattern dotPattern = Pattern.compile(",");
	private final ICaptchaService captchaService;

	public CaptchaController(ICaptchaService captchaService) {
		this.captchaService = captchaService;
	}

	/**
	 * 获取验证码(长130,宽48)
	 *
	 * @param response response
	 * @return 验证码
	 */
	@Operation(summary = "获取验证码")
	@GetMapping(value = "/captchaCode")
	public ApiResult<CaptchaResVo> getCaptchaCode(HttpServletResponse response) {
		return getCaptchaCode(new CaptchaCodeReqVo(130, 48), response);
	}

	/**
	 * 获取验证码
	 *
	 * @param vo       查询对象
	 * @param response response
	 * @return 验证码
	 */
	@Operation(summary = "获取验证码")
	@GetMapping(value = "/captchaCode/{captchaWidth}/{captchaHeight}")
	public ApiResult<CaptchaResVo> getCaptchaCode(CaptchaCodeReqVo vo, HttpServletResponse response) {
		// 获取运算的结果
		Captcha captcha = CaptchaUtil.getCaptcha(vo.getCaptchaWidth(), vo.getCaptchaHeight());
		//当验证码类型为 arithmetic时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
		String captchaValue = captcha.text();
		if (captcha.getCharType() - 1 == Captcha.TYPE_DEFAULT && captchaValue.contains(".")) {
			captchaValue = dotPattern.split(captchaValue)[0];
		}
		String id = captchaService.save(captchaValue);
		//add no cache headers
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0L);
		// 验证码信息
		return ApiResult.success(new CaptchaResVo(id, captcha.toBase64()));
	}
}
