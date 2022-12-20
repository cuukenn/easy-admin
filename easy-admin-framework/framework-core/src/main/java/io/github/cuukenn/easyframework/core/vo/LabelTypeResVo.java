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
package io.github.cuukenn.easyframework.core.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * 用于前端选项内容包装
 *
 * @author changgg
 */
@Schema(title = "选项封装")
public class LabelTypeResVo<L, V> implements Serializable {
	@Schema(title = "选项描述")
	private L label;
	@Schema(title = "选项值")
	private V value;

	public LabelTypeResVo() {
	}

	public LabelTypeResVo(L label, V value) {
		this.label = label;
		this.value = value;
	}

	public L getLabel() {
		return label;
	}

	public void setLabel(L label) {
		this.label = label;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "LabelTypeResVo{" +
			"label='" + label + '\'' +
			", value='" + value + '\'' +
			'}';
	}
}
