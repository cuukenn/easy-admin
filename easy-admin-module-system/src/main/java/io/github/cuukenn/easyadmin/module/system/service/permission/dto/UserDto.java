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

import io.github.cuukenn.easyadmin.module.system.enums.GenderType;
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
public class UserDto {
	/**
	 * id
	 */
	private Long id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 性别
	 */
	private GenderType gender;
	/**
	 * 头像地址
	 */
	private String avatar;
	/**
	 * 地址
	 */
	private String location;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 状态(开启/关闭)
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
