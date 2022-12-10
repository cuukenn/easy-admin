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
package io.github.cuukenn.easyadmin.module.system.service.permission;

import io.github.cuukenn.easyadmin.module.system.controller.admin.permission.vo.UserVo;
import io.github.cuukenn.easyadmin.module.system.service.permission.dto.UserDto;
import io.github.cuukenn.easyframework.core.vo.PageReqVo;
import io.github.cuukenn.easyframework.core.vo.PageWrapper;

import java.util.List;

/**
 * @author changgg
 */
public interface IUserService {
	/**
	 * 获取单个用户数据
	 *
	 * @param id id
	 * @return 数据
	 */
	UserDto get(Long id);

	/**
	 * 获取所有用户数据
	 *
	 * @return 数据
	 */
	List<UserDto> list();

	/**
	 * 获取用户数据
	 *
	 * @param status 状态
	 * @return 数据
	 */
	List<UserDto> list(Boolean status);

	/**
	 * 获取用户数据
	 *
	 * @param vo vo
	 * @return 数据
	 */
	PageWrapper<UserDto> list(PageReqVo vo);

	/**
	 * 创建用户
	 *
	 * @param vo 创建数据
	 */
	void create(UserVo.CreateReqVo vo);

	/**
	 * 更新用户
	 *
	 * @param vo 更新数据
	 */
	void update(UserVo.UpdateReqVo vo);

	/**
	 * 更新用户状态
	 *
	 * @param id     用户id
	 * @param status 用户状态
	 */
	void update(Long id, Boolean status);

	/**
	 * 删除用户
	 *
	 * @param id 用户id
	 */
	void delete(Long id);
}
