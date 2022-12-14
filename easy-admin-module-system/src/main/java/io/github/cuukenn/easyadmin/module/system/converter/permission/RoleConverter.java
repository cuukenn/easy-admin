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
package io.github.cuukenn.easyadmin.module.system.converter.permission;

import io.github.cuukenn.easyadmin.module.system.controller.admin.permission.vo.RoleVo;
import io.github.cuukenn.easyadmin.module.system.dao.RolePo;
import io.github.cuukenn.easyadmin.module.system.service.permission.dto.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author changgg
 */
@SuppressWarnings("UnmappedTargetProperties")
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleConverter {
	RoleConverter INSTANCE = Mappers.getMapper(RoleConverter.class);

	/**
	 * dto2po
	 *
	 * @param dto dto
	 * @return po
	 */
	RolePo toRolePo(RoleDto dto);

	/**
	 * vo2po
	 *
	 * @param vo vo
	 * @return po
	 */
	RolePo toRolePo(RoleVo.RoleCreateVo vo);

	/**
	 * dto2vo
	 *
	 * @param dto dto
	 * @return vo
	 */
	RoleVo.RoleResVo toRoleResVo(RoleDto dto);

	/**
	 * po2dto
	 *
	 * @param po po
	 * @return dto
	 */
	RoleDto toRoleDto(RolePo po);

	/**
	 * po2dto
	 *
	 * @param po po
	 * @return dto
	 */
	List<RoleDto> toRoleDto(List<RolePo> po);

	/**
	 * vo更新到po
	 *
	 * @param vo source
	 * @param po target
	 */
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "permission", expression = "java(java.util.Optional.ofNullable(po.getPermission()).map(String::toUpperCase).orElse(null))")
	void update(RoleVo.RoleUpdateVo vo, @MappingTarget RolePo po);
}
