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

import io.github.cuukenn.easyadmin.module.system.dao.UserPo;
import io.github.cuukenn.easyframework.jpa.dao.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author changgg
 */
@Repository
public interface UserRepository extends BaseRepository<UserPo> {
	/**
	 * 用户名已存在
	 *
	 * @param name 用户名称
	 * @return 是否存在
	 */
	boolean existsByUsername(String name);

	/**
	 * 用户名已存在
	 *
	 * @param name 用户名称
	 * @param id   id
	 * @return 是否存在
	 */
	boolean existsByUsernameAndIdNot(String name, Long id);

	/**
	 * 查询指定状态的用户
	 *
	 * @param status 用户状态
	 * @return 用户
	 */
	Iterable<UserPo> findAllByStatus(Boolean status);
}
