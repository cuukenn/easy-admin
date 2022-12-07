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

import io.github.cuukenn.easyadmin.module.system.dao.MenuPo;
import io.github.cuukenn.easyadmin.module.system.enums.MenuType;
import io.github.cuukenn.easyframework.jpa.dao.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author changgg
 */
@Repository
public interface MenuRepository extends BaseRepository<MenuPo> {
	/**
	 * 查询菜单列表
	 *
	 * @param status 状态
	 * @param types  类型
	 * @return 过滤的数据
	 */
	Iterable<MenuPo> findAllByStatusAndTypeIn(Boolean status, Set<MenuType> types);

	/**
	 * 是否存在子菜单项目
	 *
	 * @param parentId 父亲菜单id
	 * @return 是否存在
	 */
	Boolean existsByParentId(Long parentId);

	/**
	 * 获取父菜单的子集菜单
	 *
	 * @param parentId 父亲菜单id
	 * @return 数据
	 */
	Iterable<MenuPo> findAllByParentId(Long parentId);

	/**
	 * 菜单已存在
	 *
	 * @param name 菜单名称
	 * @return 是否存在
	 */
	boolean existsByName(String name);

	/**
	 * 菜单已存在
	 *
	 * @param name 菜单名称
	 * @param id   id
	 * @return 是否存在
	 */
	boolean existsByNameAndIdNot(String name, Long id);
}
