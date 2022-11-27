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
package io.github.cuukenn.easyframework.core.dao;

import java.io.Serializable;

/**
 * 逻辑删除支持
 *
 * @author changgg
 * @since 0.1.0
 */
public interface ILogicDelete<T> extends Serializable {
	/**
	 * 逻辑删除状态
	 *
	 * @return deleted
	 */
	T getDeleted();

	/**
	 * 逻辑删除状态
	 *
	 * @param status 状态
	 */
	void setDeleted(T status);

	/**
	 * 是否被删除
	 *
	 * @return delete status
	 */
	boolean deleted();
}
