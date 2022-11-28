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
package io.github.cuukenn.easyadmin.service.permission.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * @author changgg
 */
@Data
@EqualsAndHashCode(of = "id")
@ToString
public class RoleDto {
	/**
	 * id
	 */
	private Long id;
	/**
	 * 角色名称
	 */
	private String name;
	/**
	 * 角色权限标识
	 */
	private String permission;
	/**
	 * 角色状态(开启/关闭)
	 */
	private Boolean status;
	/**
	 * 创建时间
	 */
	private Date createdTime;
	/**
	 * 修改时间
	 */
	private Date lastModifiedTime;
}
