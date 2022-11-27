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
package io.github.cuukenn.easyadmin.controller.admin.permission;

import io.github.cuukenn.easyadmin.controller.admin.permission.vo.MenuCreateReqVo;
import io.github.cuukenn.easyadmin.controller.admin.permission.vo.MenuResVo;
import io.github.cuukenn.easyadmin.controller.admin.permission.vo.MenuUpdateReqVo;
import io.github.cuukenn.easyadmin.pojo.vo.UpdateStatus;
import io.github.cuukenn.easyadmin.service.permission.IMenuService;
import io.github.cuukenn.easyframework.core.vo.ApiPageResult;
import io.github.cuukenn.easyframework.core.vo.ApiResult;
import io.github.cuukenn.easyframework.core.vo.PageReqVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author changgg
 */
@Tag(name = "管理后台-菜单管理")
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {
	private final IMenuService service;

	@Operation(summary = "菜单列表")
	@GetMapping("/list")
	public ApiResult<ApiPageResult<MenuResVo>> list(@Valid PageReqVo vo) {
		return ApiPageResult.success(null);
	}

	@Operation(summary = "子菜单列表")
	@GetMapping("/list/child")
	public ApiResult<List<MenuResVo>> listChild(@Valid @NotNull Long parentId) {
		return ApiResult.success(null);
	}

	@Operation(summary = "获取菜单项")
	@GetMapping("/get")
	public ApiResult<MenuResVo> get(@Valid @NotNull Long menuId) {
		return ApiResult.success(null);
	}

	@Operation(summary = "创建菜单项")
	@PostMapping("/create")
	public ApiResult<Boolean> create(@Valid @RequestBody MenuCreateReqVo vo) {
		service.create(vo);
		return ApiResult.success(true);
	}

	@Operation(summary = "更新菜单项")
	@PutMapping("/update")
	public ApiResult<Boolean> update(@Valid @RequestBody MenuUpdateReqVo vo) {
		service.update(vo);
		return ApiResult.success(true);
	}

	@Operation(summary = "删除菜单项")
	@DeleteMapping("/delete")
	public ApiResult<Boolean> delete(@Valid @NotNull Long menuId) {
		service.delete(menuId);
		return ApiResult.success(true);
	}

	@Operation(summary = "更新菜单状态")
	@PutMapping("/update/status")
	public ApiResult<Boolean> updateStatus(@Valid @RequestBody UpdateStatus status) {
		service.update(status.getId(), status.getStatus());
		return ApiResult.success(true);
	}
}
