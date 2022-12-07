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

import io.github.cuukenn.easyframework.web.toolkit.valid.InsertGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author changgg
 */
@Schema(title = "角色")
@Data
@ToString
public class RoleBaseVo {
	@Schema(title = "角色名称")
	@NotBlank(groups = InsertGroup.class, message = "角色名称不能为空")
	@Length(max = 10, message = "角色名称长度不符合要求")
	private String name;
	@Schema(title = "角色权限标识")
	@NotBlank(groups = InsertGroup.class, message = "角色权限标识不能为空")
	@Length(max = 20, message = "角色权限标识长度不符合要求")
	private String permission;
	@Schema(title = "角色描述")
	@Length(max = 50, message = "角色描述信息长度不符合要求")
	private String description;
}
