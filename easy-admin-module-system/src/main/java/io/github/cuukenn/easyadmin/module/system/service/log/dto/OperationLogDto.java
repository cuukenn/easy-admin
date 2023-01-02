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
package io.github.cuukenn.easyadmin.module.system.service.log.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author changgg
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationLogDto {
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 用户ip
	 */
	private String userIp;
	/**
	 * 用户agent
	 */
	private String userAgent;
	/**
	 * 资源定位符
	 */
	private String uri;
	/**
	 * 资源方法
	 */
	private String method;
	/**
	 * 资源描述
	 */
	private String uriDescription;
	/**
	 * 资源处理时间
	 */
	private Long duration;
	/**
	 * 资源请求参数
	 */
	private String params;
	/**
	 * 资源响应结果
	 */
	private String result;
}
