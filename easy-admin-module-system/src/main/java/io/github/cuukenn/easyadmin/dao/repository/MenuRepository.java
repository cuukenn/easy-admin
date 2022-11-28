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
package io.github.cuukenn.easyadmin.dao.repository;

import io.github.cuukenn.easyadmin.dao.MenuPo;
import io.github.cuukenn.easyframework.jpa.dao.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author changgg
 */
@Repository
public interface MenuRepository extends BaseRepository<MenuPo> {
	/**
	 * 模糊匹配菜单名称
	 *
	 * @param name 菜单名称
	 * @return 数据
	 */
	Iterable<MenuPo> findAllByNameLike(String name);

	/**
	 * 匹配菜单状态
	 *
	 * @param status 菜单状态
	 * @return 数据
	 */
	Iterable<MenuPo> findAllByStatusIs(Boolean status);

	/**
	 * 模糊匹配菜单名称
	 * 匹配菜单状态
	 *
	 * @param name   菜单名称
	 * @param status 菜单状态
	 * @return 数据
	 */
	Iterable<MenuPo> findAllByNameLikeAndStatusIs(String name, Boolean status);
}
