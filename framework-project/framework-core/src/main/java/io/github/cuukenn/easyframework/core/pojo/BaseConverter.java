/*
 * Copyright 2022 the original author or authors.
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
package io.github.cuukenn.easyframework.core.pojo;


import io.github.cuukenn.easyframework.core.pojo.dao.IEntity;
import io.github.cuukenn.easyframework.core.pojo.dto.AbstractDto;

/**
 * @author changgg
 */
public interface BaseConverter<D extends AbstractDto, E extends IEntity> {
	/**
	 * dto2entity
	 *
	 * @param dto dto
	 * @return entity
	 */
	E toEntity(D dto);

	/**
	 * entity2dto
	 *
	 * @param entity entity
	 * @return dto
	 */
	D toDto(E entity);
}
