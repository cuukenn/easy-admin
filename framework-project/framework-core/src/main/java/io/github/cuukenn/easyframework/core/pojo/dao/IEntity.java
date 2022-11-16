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
package io.github.cuukenn.easyframework.core.pojo.dao;

import java.io.Serializable;
import java.util.Date;

/**
 * 标准实体
 *
 * @author changgg
 * @since 0.1.0
 */
public interface IEntity extends Serializable {
	/**
	 * 标识
	 *
	 * @return id
	 */
	Long getId();

	/**
	 * 创建人
	 *
	 * @return create_by
	 */
	Long getCreatedBy();

	/**
	 * 修改人
	 *
	 * @return last_modified_by
	 */
	Long getLastModifiedBy();

	/**
	 * 创建时间
	 *
	 * @return create_time
	 */
	Date getCreatedTime();

	/**
	 * 修改时间
	 *
	 * @return last_modified_time
	 */
	Date getLastModifiedTime();

	/**
	 * 标识
	 *
	 * @param id 标识
	 */
	void setId(Long id);

	/**
	 * 创建人
	 *
	 * @param createdBy 创建人
	 */
	void setCreatedBy(Long createdBy);

	/**
	 * 修改人
	 *
	 * @@param lastModifiedBy 修改人
	 */
	void setLastModifiedBy(Long lastModifiedBy);

	/**
	 * 创建时间
	 *
	 * @@param createdTime  create_time
	 */
	void setCreatedTime(Date createdTime);

	/**
	 * 修改时间
	 *
	 * @@param lastModifiedTime   修改时间
	 */
	void setLastModifiedTime(Date lastModifiedTime);
}
