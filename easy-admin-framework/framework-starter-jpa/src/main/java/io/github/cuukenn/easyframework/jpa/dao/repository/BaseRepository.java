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
package io.github.cuukenn.easyframework.jpa.dao.repository;

import io.github.cuukenn.easyframework.core.dao.IEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author changgg
 */
@NoRepositoryBean
public interface BaseRepository<T extends IEntity> extends PagingAndSortingRepository<T, Long> {
	/**
	 * 查询指定id的数据是否存在
	 *
	 * @param id id
	 * @return 是否存在
	 */
	boolean existsById(Long id);

	/**
	 * 逻辑删除指定id数据
	 *
	 * @param id id
	 */
	@Query("update #{#entityName} e set e.deleted = true where e.id = ?1")
	@Modifying
	void logicDelete(Long id);

	/**
	 * 根据id逻辑删除指定实体
	 *
	 * @param entity 实体
	 */
	default void logicDelete(T entity) {
		logicDelete(entity.getId());
	}

	/**
	 * 逻辑删除指定实体数组
	 *
	 * @param entities 实例数组
	 */
	default void logicDelete(Iterable<? extends T> entities) {
		entities.forEach(entity -> logicDelete(entity.getId()));
	}

	/**
	 * 逻辑删除全部数据
	 */
	@Query("update #{#entityName} e set e.deleted = true ")
	@Modifying
	void logicDeleteAll();
}