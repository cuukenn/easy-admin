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

import io.github.cuukenn.easyadmin.module.system.controller.admin.permission.vo.UserVo;
import io.github.cuukenn.easyadmin.module.system.dao.UserPo;
import io.github.cuukenn.easyadmin.module.system.service.permission.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author changgg
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserConverter {
	UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

	/**
	 * dto2vo
	 *
	 * @param dto dto
	 * @return vo
	 */
	UserVo.ResVo toUserResVo(UserDto dto);

	/**
	 * po2dto
	 *
	 * @param po po
	 * @return dto
	 */
	UserDto toUserDto(UserPo po);

	/**
	 * po2dto
	 *
	 * @param po po
	 * @return dto
	 */
	List<UserDto> toUserDto(List<UserPo> po);

	/**
	 * vo2po
	 *
	 * @param vo vo
	 * @return po
	 */
	UserPo toUserPo(UserVo.CreateReqVo vo);

	/**
	 * vo更新到po
	 *
	 * @param vo source
	 * @param po target
	 */
	@Mapping(target = "id", ignore = true)
	void update(UserVo.UpdateReqVo vo, @MappingTarget UserPo po);
}
