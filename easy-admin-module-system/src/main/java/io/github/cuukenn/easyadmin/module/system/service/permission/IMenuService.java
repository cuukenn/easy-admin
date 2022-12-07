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

import io.github.cuukenn.easyadmin.module.system.controller.admin.permission.vo.MenuCreateReqVo;
import io.github.cuukenn.easyadmin.module.system.controller.admin.permission.vo.MenuUpdateReqVo;
import io.github.cuukenn.easyadmin.module.system.enums.MenuType;
import io.github.cuukenn.easyadmin.module.system.service.permission.dto.MenuDto;

import java.util.List;
import java.util.Set;

/**
 * @author changgg
 */
public interface IMenuService {
	/**
	 * 获取单个菜单数据
	 *
	 * @param id id
	 * @return 数据
	 */
	MenuDto get(Long id);

	/**
	 * 查询指定菜单是否存在子菜单
	 *
	 * @param id 菜单id
	 * @return 是否存在
	 */
	Boolean existsChildren(Long id);

	/**
	 * 获取所有菜单数据
	 *
	 * @return 数据
	 */
	List<MenuDto> list();

	/**
	 * 获取菜单数据
	 *
	 * @param types  菜单类型
	 * @param status 状态
	 * @return 数据
	 */
	List<MenuDto> list(Set<MenuType> types, Boolean status);

	/**
	 * 创建菜单
	 *
	 * @param vo 创建数据
	 */
	void create(MenuCreateReqVo vo);

	/**
	 * 更新菜单
	 *
	 * @param vo 更新数据
	 */
	void update(MenuUpdateReqVo vo);

	/**
	 * 更新菜单状态
	 *
	 * @param menuId 菜单id
	 * @param status 菜单状态
	 */
	void update(Long menuId, Boolean status);

	/**
	 * 删除菜单
	 *
	 * @param menuId 菜单id
	 */
	void delete(Long menuId);
}
