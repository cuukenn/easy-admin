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
package io.github.cuukenn.easyadmin.module.system.service.permission.dto;

import io.github.cuukenn.easyadmin.module.system.enums.MenuType;
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
public class MenuDto {
	/**
	 * id
	 */
	private Long id;
	/**
	 * 父节点id
	 */
	private Long parentId;
	/**
	 * 菜单名称
	 */
	private String name;
	/**
	 * 菜单图标
	 */
	private String icon;
	/**
	 * 菜单顺序
	 */
	private Integer number;
	/**
	 * 路由路径
	 */
	private String routerPath;
	/**
	 * 组件路径
	 */
	private String componentPath;
	/**
	 * 菜单权限
	 */
	private String permission;
	/**
	 * 菜单状态(开启/关闭)
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
	/**
	 * 菜单类型
	 */
	private MenuType type;
}
