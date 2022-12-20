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

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.cuukenn.easyframework.web.jackson.DatePattern;
import io.github.cuukenn.easyframework.web.toolkit.valid.InsertGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author changgg
 */
@Schema(title = "角色")
@Data
@ToString
public class RoleVo {
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

    /**
     * @author changgg
     */
    @Schema(title = "角色创建Vo")
    @Data
    @ToString
    public static class RoleCreateVo extends RoleVo {
    }

    /**
     * @author changgg
     */
    @Schema(title = "角色响应数据Vo")
    @Data
    @ToString
    public static class RoleResVo extends RoleVo {
        @Schema(title = "角色id")
        private String id;
        @Schema(title = "开启状态:true 开启,false 关闭")
        private Boolean status;
        @Schema(title = "创建时间")
        @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
        private Date createdTime;
        @Schema(title = "修改时间")
        @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
        private Date lastModifiedTime;
    }

    /**
     * @author changgg
     */
    @Schema(title = "角色更新Vo")
    @Data
    @ToString
    public static class RoleUpdateVo extends RoleVo {
        @Schema(title = "角色id")
        @NotNull(message = "角色id不应为null")
        private Long id;
    }
}
