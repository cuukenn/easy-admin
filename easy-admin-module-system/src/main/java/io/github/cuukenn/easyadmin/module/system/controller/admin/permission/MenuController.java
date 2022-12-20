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

import io.github.cuukenn.easyadmin.module.system.controller.admin.permission.vo.MenuVo;
import io.github.cuukenn.easyadmin.module.system.controller.admin.permission.vo.MenuTreeResVo;
import io.github.cuukenn.easyadmin.module.system.controller.vo.SelectTreeResVo;
import io.github.cuukenn.easyadmin.module.system.controller.vo.UpdateStatus;
import io.github.cuukenn.easyadmin.module.system.converter.permission.MenuConverter;
import io.github.cuukenn.easyadmin.module.system.enums.MenuType;
import io.github.cuukenn.easyadmin.module.system.service.permission.IMenuService;
import io.github.cuukenn.easyadmin.module.system.service.permission.dto.MenuDto;
import io.github.cuukenn.easyframework.core.vo.ApiResult;
import io.github.cuukenn.easyframework.core.vo.LabelTypeResVo;
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
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
	public ApiResult<List<MenuTreeResVo>> tree() {
		List<MenuDto> list = service.list();
		list.sort(Comparator.comparing(MenuDto::getNumber));
		return ApiResult.success(MenuConverter.INSTANCE.toMenuTree(list));
	}

	@Operation(summary = "获取菜单项")
	@GetMapping("/get")
	public ApiResult<MenuVo.MenuResVo> get(@Valid @NotNull Long menuId) {
		return ApiResult.success(MenuConverter.INSTANCE.toMenuResVo(service.get(menuId)));
	}

	@Operation(summary = "创建菜单项")
	@PostMapping("/create")
	public ApiResult<Boolean> create(@Validated(value = {InsertGroup.class, Default.class}) @RequestBody MenuVo.MenuCreateReqVo vo) {
		service.create(vo);
		return ApiResult.success(true);
	}

	@Operation(summary = "更新菜单项")
	@PutMapping("/update")
	public ApiResult<Boolean> update(@Validated @RequestBody MenuVo.MenuUpdateReqVo vo) {
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

	@Operation(summary = "获取菜单类型枚举")
	@GetMapping("/types")
	public ApiResult<List<LabelTypeResVo<String, Integer>>> getMenuTypes() {
		return ApiResult.success(Arrays.stream(MenuType.values()).map(type -> new LabelTypeResVo<>(type.getName(), type.getCode())).collect(Collectors.toList()));
	}

	@Operation(summary = "获取树形select数据")
	@GetMapping("/tree-select")
	public ApiResult<List<SelectTreeResVo<String, Long>>> getTreeSelectMenus() {
		List<MenuDto> list = service.list();
		list.sort(Comparator.comparing(MenuDto::getNumber));
		return ApiResult.success(Collections.singletonList(MenuConverter.INSTANCE.toSelectTreeResVo(list)));
	}
}
