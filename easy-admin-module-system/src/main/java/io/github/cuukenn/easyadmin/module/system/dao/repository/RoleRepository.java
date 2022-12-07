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
package io.github.cuukenn.easyadmin.module.system.dao.repository;

import io.github.cuukenn.easyadmin.module.system.dao.RolePo;
import io.github.cuukenn.easyframework.jpa.dao.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author changgg
 */
@Repository
public interface RoleRepository extends BaseRepository<RolePo> {
	/**
	 * 查询指定状态的角色
	 *
	 * @param status 角色状态
	 * @return 角色
	 */
	Iterable<RolePo> findAllByStatus(Boolean status);

	/**
	 * 角色名已存在
	 *
	 * @param name 角色名称
	 * @return 是否存在
	 */
	boolean existsByName(String name);

	/**
	 * 角角色权限标识已存在
	 *
	 * @param permission 角色权限标识
	 * @return 是否存在
	 */
	boolean existsByPermission(String permission);

	/**
	 * 角色名已存在
	 *
	 * @param name 角色名称
	 * @param id   id
	 * @return 是否存在
	 */
	boolean existsByNameAndIdNot(String name, Long id);

	/**
	 * 角角色权限标识已存在
	 *
	 * @param permission 角色权限标识
	 * @param id         id
	 * @return 是否存在
	 */
	boolean existsByPermissionAndIdNot(String permission, Long id);
}
