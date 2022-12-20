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
package io.github.cuukenn.easyadmin.module.system.converter.auth;

import io.github.cuukenn.easyadmin.module.system.controller.admin.auth.vo.AuthMenuResVo;
import io.github.cuukenn.easyadmin.module.system.enums.TreeNode;
import io.github.cuukenn.easyadmin.module.system.service.permission.dto.MenuDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author changgg
 */
@SuppressWarnings("UnmappedTargetProperties")
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthConverter {
	AuthConverter INSTANCE = Mappers.getMapper(AuthConverter.class);

	/**
	 * dto2vo
	 *
	 * @param dto dto
	 * @return vo
	 */
	AuthMenuResVo toAuthMenuResVo(MenuDto dto);

	/**
	 * 树形转换
	 *
	 * @param resVos res
	 * @return 菜单树
	 */
	default List<AuthMenuResVo> toMenuTree(List<MenuDto> resVos) {
		//排序、保证菜单顺序
		resVos.sort(Comparator.comparing(MenuDto::getNumber));
		Map<Long, AuthMenuResVo> map = new LinkedHashMap<>();
		map.put(TreeNode.ROOT.getId(), new AuthMenuResVo());
		resVos.stream().map(this::toAuthMenuResVo).forEach(item -> map.put(item.getId(), item));
		map.values().stream().filter(item -> item.getId() != null).forEach(node -> {
			AuthMenuResVo parentNode = map.get(node.getParentId());
			//父节点菜单关闭时会找不到
			if (parentNode != null) {
				if (parentNode.getChildren() == null) {
					parentNode.setChildren(new ArrayList<>());
				}
				parentNode.getChildren().add(node);
			}
		});
		return map.get(TreeNode.ROOT.getId()).getChildren();
	}
}
