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
package io.github.cuukenn.easyadmin.module.system.controller.admin.auth;

import cn.hutool.core.collection.CollectionUtil;
import io.github.cuukenn.easyadmin.module.system.controller.admin.auth.vo.AuthMenuResVo;
import io.github.cuukenn.easyadmin.module.system.converter.auth.AuthConverter;
import io.github.cuukenn.easyadmin.module.system.enums.MenuType;
import io.github.cuukenn.easyadmin.module.system.service.permission.IPermissionService;
import io.github.cuukenn.easyadmin.module.system.service.permission.dto.MenuDto;
import io.github.cuukenn.easyadmin.module.system.service.permission.dto.RoleDto;
import io.github.cuukenn.easyframework.core.vo.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author changgg
 */
@Tag(name = "管理后台-后台认证")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final IPermissionService permissionService;

	@Operation(summary = "获取当前用户菜单列表")
	@GetMapping("/list/menus")
	public ApiResult<List<AuthMenuResVo>> listMenus() {
		//TODO 获取实际登录id
		Set<Long> roleIds = permissionService.getUserRoles(1L, true).stream().map(RoleDto::getId).collect(Collectors.toSet());
		List<MenuDto> roleMenus = new ArrayList<>();
		roleIds.forEach(id -> {
			List<MenuDto> menus = permissionService.getRoleMenus(id, CollectionUtil.set(false, MenuType.DIR, MenuType.MENU), true);
			roleMenus.addAll(menus);
		});
		return ApiResult.success(AuthConverter.INSTANCE.toMenuTree(roleMenus));
	}
}
