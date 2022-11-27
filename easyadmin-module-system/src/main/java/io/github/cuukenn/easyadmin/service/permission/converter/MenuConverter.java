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
package io.github.cuukenn.easyadmin.service.permission.converter;

import io.github.cuukenn.easyadmin.controller.admin.permission.vo.MenuBaseVo;
import io.github.cuukenn.easyadmin.controller.admin.permission.vo.MenuUpdateReqVo;
import io.github.cuukenn.easyadmin.dao.MenuPo;
import io.github.cuukenn.easyframework.core.vo.BaseConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

/**
 * @author changgg
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MenuConverter extends BaseConverter<MenuPo, MenuBaseVo> {
	MenuConverter INSTANCE = Mappers.getMapper(MenuConverter.class);

	/**
	 * vo更新到po
	 *
	 * @param vo source
	 * @param po target
	 */
	@SuppressWarnings("UnmappedTargetProperties")
	@Mapping(target = "id", ignore = true)
	void update(MenuUpdateReqVo vo, @MappingTarget MenuPo po);
}
