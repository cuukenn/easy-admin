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
package io.github.cuukenn.easyadmin.module.system.controller.admin.permission.vo;

import io.github.cuukenn.easyadmin.module.system.enums.MenuType;
import io.github.cuukenn.easyframework.web.toolkit.valid.InsertGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author changgg
 */
@Schema(title = "菜单基础信息Vo")
@Data
@ToString
public class MenuBaseVo implements Serializable {
	@Schema(title = "父菜单id")
	@NotNull(message = "菜单名称长度不符合要求")
	private Long parentId;
	@Schema(title = "菜单名称")
	@NotBlank(groups = InsertGroup.class, message = "菜单名称不能为空")
	@Length(max = 20, message = "菜单名称长度不符合要求")
	private String name;
	@Schema(title = "菜单图标")
	@Length(max = 50, message = "菜单图标长度不符合要求")
	private String icon;
	@Schema(title = "菜单显示顺序")
	@NotNull(groups = InsertGroup.class, message = "菜单显示顺序不能为null")
	private Integer number;
	@Schema(title = "访问路径")
	@Length(max = 50, message = "菜单访问路径长度不符合要求")
	private String routerPath;
	@Schema(title = "组件路径")
	@Length(max = 50, message = "菜单组件路径长度不符合要求")
	private String componentPath;
	@Schema(title = "菜单权限")
	private String permission;
	@Schema(title = "菜单类型")
	@NotNull(groups = InsertGroup.class, message = "菜单类型不为为null")
	private MenuType type;
}
