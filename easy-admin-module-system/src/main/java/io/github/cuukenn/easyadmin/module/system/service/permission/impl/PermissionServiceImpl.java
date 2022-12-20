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
package io.github.cuukenn.easyadmin.module.system.service.permission.impl;

import io.github.cuukenn.easyadmin.module.system.enums.InnerRoleType;
import io.github.cuukenn.easyadmin.module.system.enums.MenuType;
import io.github.cuukenn.easyadmin.module.system.service.permission.IMenuService;
import io.github.cuukenn.easyadmin.module.system.service.permission.IPermissionService;
import io.github.cuukenn.easyadmin.module.system.service.permission.IRoleService;
import io.github.cuukenn.easyadmin.module.system.service.permission.IUserService;
import io.github.cuukenn.easyadmin.module.system.service.permission.dto.MenuDto;
import io.github.cuukenn.easyadmin.module.system.service.permission.dto.RoleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author changgg
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PermissionServiceImpl implements IPermissionService {
	private final IUserService userService;
	private final IRoleService roleService;
	private final IMenuService menuService;

	@Override
	public List<RoleDto> getUserRoles(Long userId, Boolean status) {
		return userService.getInvokedRoles(userId).stream().filter(item -> item.getStatus().equals(status)).collect(Collectors.toList());
	}

	@Override
	public List<MenuDto> getRoleMenus(Long roleId, Set<MenuType> types, Boolean status) {
		String permission = roleService.get(roleId).getPermission();
		if (InnerRoleType.ADMIN.getPermission().equals(permission)) {
			return menuService.list(types, status);
		} else {
			return roleService.getInvokedMenus(roleId).stream()
				.filter(item -> item.getStatus().equals(status))
				.filter(item -> types.contains(item.getType()))
				.collect(Collectors.toList());
		}
	}

	@SuppressWarnings("DuplicatedCode")
	@Override
	@Transactional(rollbackFor = RuntimeException.class)
	public void completeInvokeUserRoles(Long id, Set<Long> roleIds) {
		if (roleIds == null) {
			return;
		}
		//过滤符合条件的角色
		Set<Long> filteredRoles = roleIds.stream().filter(roleId -> roleService.exist(roleId, true)).collect(Collectors.toSet());
		if (filteredRoles.isEmpty()) {
			return;
		}
		//提取待撤销的授权和待新增的授权
		Set<Long> invoked = userService.getInvokedRoles(id).stream().map(RoleDto::getId).collect(Collectors.toSet());
		Set<Long> invokes = roleIds.stream().filter(roleId -> !invoked.contains(roleId)).collect(Collectors.toSet());
		Set<Long> revokes = invoked.stream().filter(roleId -> !roleIds.contains(roleId)).collect(Collectors.toSet());
		userService.revokeRoles(id, revokes);
		userService.invokeRoles(id, invokes);
	}

	@SuppressWarnings("DuplicatedCode")
	@Override
	@Transactional(rollbackFor = RuntimeException.class)
	public void completeInvokeRoleMenus(Long id, Set<Long> menuIds) {
		if (menuIds == null) {
			return;
		}
		//过滤符合条件的角色
		Set<Long> filteredRoles = menuIds.stream().filter(roleId -> roleService.exist(id, true)).collect(Collectors.toSet());
		if (filteredRoles.isEmpty()) {
			return;
		}
		//提取待撤销的授权和待新增的授权
		Set<Long> invoked = roleService.getInvokedMenus(id).stream().map(MenuDto::getId).collect(Collectors.toSet());
		Set<Long> invokes = menuIds.stream().filter(menuId -> !invoked.contains(menuId)).collect(Collectors.toSet());
		Set<Long> revokes = invoked.stream().filter(menuId -> !menuIds.contains(menuId)).collect(Collectors.toSet());
		roleService.revokeMenus(id, revokes);
		roleService.invokeMenus(id, invokes);
	}
}
