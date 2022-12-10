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

import io.github.cuukenn.easyadmin.module.system.controller.admin.permission.vo.RoleVo;
import io.github.cuukenn.easyadmin.module.system.service.permission.dto.RoleDto;
import io.github.cuukenn.easyframework.core.vo.PageReqVo;
import io.github.cuukenn.easyframework.core.vo.PageWrapper;

import java.util.List;

/**
 * @author changgg
 */
public interface IRoleService {
	/**
	 * 获取单个角色数据
	 *
	 * @param id id
	 * @return 数据
	 */
	RoleDto get(Long id);

	/**
	 * 获取所有角色数据
	 *
	 * @return 数据
	 */
	List<RoleDto> list();

	/**
	 * 获取角色数据
	 *
	 * @param status 状态
	 * @return 数据
	 */
	List<RoleDto> list(Boolean status);

	/**
	 * 获取角色数据
	 *
	 * @param vo vo
	 * @return 数据
	 */
	PageWrapper<RoleDto> list(PageReqVo vo);

	/**
	 * 创建角色
	 *
	 * @param vo 创建数据
	 */
	void create(RoleVo.RoleCreateVo vo);

	/**
	 * 更新角色
	 *
	 * @param vo 更新数据
	 */
	void update(RoleVo.RoleUpdateVo vo);

	/**
	 * 更新角色状态
	 *
	 * @param roleId 角色id
	 * @param status 角色状态
	 */
	void update(Long roleId, Boolean status);

	/**
	 * 删除角色
	 *
	 * @param roleId 角色id
	 */
	void delete(Long roleId);
}
