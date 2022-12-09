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
package io.github.cuukenn.easyadmin.module.system.controller.admin.log.vo;

import io.github.cuukenn.easyframework.web.toolkit.valid.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.logging.LogLevel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author changgg
 */
@Schema(title = "日志级别基础Vo")
@Data
@ToString
public class LogLevelBaseVo {
	@Schema(title = "日志名称")
	@NotBlank(groups = {UpdateGroup.class}, message = "日志名称不能为空")
	private String name;
	@Schema(title = "日志级别")
	@NotNull(groups = {UpdateGroup.class}, message = "日志级别不能为空")
	private LogLevel level;
}