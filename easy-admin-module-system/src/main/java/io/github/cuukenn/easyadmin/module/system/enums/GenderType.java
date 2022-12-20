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
package io.github.cuukenn.easyadmin.module.system.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author changgg
 */
@RequiredArgsConstructor
@Getter
public enum GenderType {
	/**
	 * 男性
	 */
	MAN(0, "男"),
	/**
	 * 女性
	 */
	WOMAN(1, "女"),
	/**
	 * 保密
	 */
	HIDE(2, "保密");
	private final int code;
	private final String name;

	@JsonCreator
	public static GenderType transform(Integer typeCode) {
		if (typeCode == null) {
			return null;
		}
		for (GenderType type : GenderType.values()) {
			if (type.getCode() == typeCode) {
				return type;
			}
		}
		return null;
	}

	@JsonValue
	public int getCode() {
		return code;
	}
}