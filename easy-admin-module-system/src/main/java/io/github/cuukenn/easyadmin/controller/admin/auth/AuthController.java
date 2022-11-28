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
package io.github.cuukenn.easyadmin.controller.admin.auth;

import io.github.cuukenn.easyadmin.controller.admin.auth.vo.AuthMenuResVo;
import io.github.cuukenn.easyframework.core.vo.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @author changgg
 */
@Tag(name = "管理后台-后台认证")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	@Operation(summary = "获取当前用户菜单列表")
	@GetMapping("/list/menus")
	public ApiResult<List<AuthMenuResVo>> listMenus() {
		return ApiResult.success(Collections.emptyList());
	}
}
