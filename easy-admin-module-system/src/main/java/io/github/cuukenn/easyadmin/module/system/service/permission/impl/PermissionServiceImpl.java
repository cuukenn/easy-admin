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

import cn.hutool.core.collection.CollectionUtil;
import io.github.cuukenn.easyadmin.module.system.enums.MenuType;
import io.github.cuukenn.easyadmin.module.system.service.permission.IMenuService;
import io.github.cuukenn.easyadmin.module.system.service.permission.IPermissionService;
import io.github.cuukenn.easyadmin.module.system.service.permission.IRoleService;
import io.github.cuukenn.easyadmin.module.system.service.permission.dto.MenuDto;
import io.github.cuukenn.easyadmin.module.system.service.permission.dto.RoleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
	private final IRoleService roleService;
	private final IMenuService menuService;

	@Override
	public Set<Long> getUserRoleIds(Long userId, Boolean status) {
		return CollectionUtil.list(false, roleService.list(status)).stream().map(RoleDto::getId).collect(Collectors.toSet());
	}

	@Override
	public List<MenuDto> getRoleMenus(Set<Long> roleIds, Set<MenuType> types, Boolean status) {
		return new ArrayList<>(CollectionUtil.list(false, menuService.list(types, status)));
	}
}
