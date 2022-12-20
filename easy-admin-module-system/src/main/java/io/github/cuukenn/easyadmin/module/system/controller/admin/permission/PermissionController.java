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
package io.github.cuukenn.easyadmin.module.system.controller.admin.permission;

import cn.hutool.core.collection.CollectionUtil;
import io.github.cuukenn.easyadmin.module.system.controller.admin.permission.vo.InvokeReqVo;
import io.github.cuukenn.easyadmin.module.system.enums.MenuType;
import io.github.cuukenn.easyadmin.module.system.service.permission.IPermissionService;
import io.github.cuukenn.easyadmin.module.system.service.permission.dto.MenuDto;
import io.github.cuukenn.easyadmin.module.system.service.permission.dto.RoleDto;
import io.github.cuukenn.easyframework.core.vo.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author changgg
 */
@Tag(name = "管理后台-授权管理")
@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
public class PermissionController {
	private final IPermissionService service;

	@Operation(summary = "获取角色菜单权限列表")
	@GetMapping("/list/role/menu")
	public ApiResult<Set<Long>> listRoleMenuPermission(@Valid @NotNull Long id) {
		return ApiResult.success(service.getRoleMenus(id, CollectionUtil.set(false, MenuType.DIR, MenuType.MENU, MenuType.BUTTON), true)
			.stream().map(MenuDto::getId).collect(Collectors.toSet()));
	}

	@Operation(summary = "授权角色菜单权限")
	@PostMapping("/invoke/role/menu")
	public ApiResult<Boolean> completeInvokePermission(@Valid @RequestBody InvokeReqVo vo) {
		service.completeInvokeRoleMenus(vo.getId(), vo.getIds());
		return ApiResult.success(true);
	}

	@Operation(summary = "获取用户角色列表")
	@GetMapping("/list/user/role")
	public ApiResult<Set<Long>> listUserRoles(@Valid @NotNull Long id) {
		return ApiResult.success(service.getUserRoles(id, true).stream().map(RoleDto::getId).collect(Collectors.toSet()));
	}

	@Operation(summary = "授权用户角色")
	@PostMapping("/invoke/user/role")
	public ApiResult<Boolean> completeInvokeUserRoles(@Valid @RequestBody InvokeReqVo vo) {
		service.completeInvokeUserRoles(vo.getId(), vo.getIds());
		return ApiResult.success(true);
	}
}
