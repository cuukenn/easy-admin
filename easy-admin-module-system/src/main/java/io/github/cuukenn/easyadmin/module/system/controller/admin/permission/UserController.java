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
package io.github.cuukenn.easyadmin.module.system.controller.admin.permission;

import io.github.cuukenn.easyadmin.module.system.controller.admin.permission.vo.UserVo;
import io.github.cuukenn.easyadmin.module.system.controller.vo.UpdateStatus;
import io.github.cuukenn.easyadmin.module.system.converter.permission.UserConverter;
import io.github.cuukenn.easyadmin.module.system.enums.GenderType;
import io.github.cuukenn.easyadmin.module.system.service.permission.IUserService;
import io.github.cuukenn.easyadmin.module.system.service.permission.dto.UserDto;
import io.github.cuukenn.easyframework.core.vo.ApiResult;
import io.github.cuukenn.easyframework.core.vo.LabelTypeResVo;
import io.github.cuukenn.easyframework.core.vo.PageReqVo;
import io.github.cuukenn.easyframework.core.vo.PageWrapper;
import io.github.cuukenn.easyframework.web.toolkit.valid.InsertGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author changgg
 */
@Tag(name = "管理后台-用户管理")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final IUserService service;

	@Operation(summary = "用户列表")
	@GetMapping("/list")
	public ApiResult<PageWrapper<UserVo.ResVo>> list(@Valid PageReqVo vo) {
		PageWrapper<UserDto> wrapper = service.list(vo);
		return ApiResult.success(PageWrapper.transform(wrapper, UserConverter.INSTANCE::toUserResVo));
	}

	@Operation(summary = "获取用户")
	@GetMapping("/get")
	public ApiResult<UserVo.ResVo> get(@Valid @NotNull Long id) {
		return ApiResult.success(UserConverter.INSTANCE.toUserResVo(service.get(id)));
	}

	@Operation(summary = "创建用户")
	@PostMapping("/create")
	public ApiResult<Boolean> create(@Validated(value = {InsertGroup.class, Default.class}) @RequestBody UserVo.CreateReqVo vo) {
		service.create(vo);
		return ApiResult.success(true);
	}

	@Operation(summary = "更新用户")
	@PutMapping("/update")
	public ApiResult<Boolean> update(@Validated @RequestBody UserVo.UpdateReqVo vo) {
		service.update(vo);
		return ApiResult.success(true);
	}

	@Operation(summary = "删除用户")
	@DeleteMapping("/delete")
	public ApiResult<Boolean> delete(@Valid @NotNull @RequestParam("id") Long id) {
		service.delete(id);
		return ApiResult.success(true);
	}

	@Operation(summary = "更新用户状态")
	@PutMapping("/update/status")
	public ApiResult<Boolean> updateStatus(@Valid @RequestBody UpdateStatus status) {
		service.update(status.getId(), status.getStatus());
		return ApiResult.success(true);
	}

	@Operation(summary = "获取用户性别类型枚举")
	@GetMapping("/gender/types")
	public ApiResult<List<LabelTypeResVo<String, Integer>>> getMenuTypes() {
		return ApiResult.success(Arrays.stream(GenderType.values()).map(type -> new LabelTypeResVo<>(type.getName(), type.getCode())).collect(Collectors.toList()));
	}
}
