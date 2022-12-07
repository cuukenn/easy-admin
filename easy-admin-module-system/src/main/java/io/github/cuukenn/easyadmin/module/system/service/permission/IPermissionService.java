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
package io.github.cuukenn.easyadmin.module.system.service.permission;

import io.github.cuukenn.easyadmin.module.system.enums.MenuType;
import io.github.cuukenn.easyadmin.module.system.service.permission.dto.MenuDto;

import java.util.List;
import java.util.Set;

/**
 * @author changgg
 */
public interface IPermissionService {
	/**
	 * 获取当前用户指定状态的角色集合
	 *
	 * @param userId 用户id
	 * @param status 角色状态
	 * @return 角色集合
	 */
	Set<Long> getUserRoleIds(Long userId, Boolean status);

	/**
	 * 获取角色集合对于的菜单
	 *
	 * @param roleIds 角色id集合
	 * @param types   菜单类型
	 * @param status  角色状态
	 * @return 角色集合
	 */
	List<MenuDto> getRoleMenus(Set<Long> roleIds, Set<MenuType> types, Boolean status);
}
