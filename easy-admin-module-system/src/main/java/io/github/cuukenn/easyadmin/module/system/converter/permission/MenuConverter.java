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

import io.github.cuukenn.easyadmin.module.system.controller.admin.permission.vo.MenuVo;
import io.github.cuukenn.easyadmin.module.system.controller.admin.permission.vo.MenuTreeResVo;
import io.github.cuukenn.easyadmin.module.system.controller.vo.SelectTreeResVo;
import io.github.cuukenn.easyadmin.module.system.dao.MenuPo;
import io.github.cuukenn.easyadmin.module.system.dao.RolePo;
import io.github.cuukenn.easyadmin.module.system.enums.TreeNode;
import io.github.cuukenn.easyadmin.module.system.service.permission.dto.MenuDto;
import io.github.cuukenn.easyadmin.module.system.service.permission.dto.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
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
public interface MenuConverter {
	MenuConverter INSTANCE = Mappers.getMapper(MenuConverter.class);

	/**
	 * dto2po
	 *
	 * @param dto dto
	 * @return po
	 */
	MenuPo toMenuPo(MenuDto dto);
	/**
	 * vo2po
	 *
	 * @param vo vo
	 * @return po
	 */
	MenuPo toMenuPo(MenuVo.MenuCreateReqVo vo);

	/**
	 * po2dto
	 *
	 * @param po po
	 * @return dto
	 */
	MenuDto toMenuDto(MenuPo po);

	/**
	 * po2dto
	 *
	 * @param po po
	 * @return dto
	 */
	List<MenuDto> toMenuDto(List<MenuPo> po);

	/**
	 * dto2vo
	 *
	 * @param dto dto
	 * @return vo
	 */
	@Named("toMenuTreeResVo")
	MenuTreeResVo toMenuTreeResVo(MenuDto dto);

	/**
	 * dto2vo
	 *
	 * @param dto dto
	 * @return vo
	 */
	MenuVo.MenuResVo toMenuResVo(MenuDto dto);

	/**
	 * dto2vo
	 *
	 * @param dto dto
	 * @return vo
	 */
	@Mapping(source = "id", target = "value")
	@Mapping(source = "name", target = "label")
	SelectTreeResVo<String, Long> toSelectTreeResVo(MenuDto dto);

	/**
	 * dto2vo
	 *
	 * @param dto dto
	 * @return vo
	 */
	List<MenuVo.MenuResVo> toMenuResVo(List<MenuDto> dto);

	/**
	 * vo更新到po
	 *
	 * @param vo source
	 * @param po target
	 */
	@Mapping(target = "id", ignore = true)
	void update(MenuVo.MenuUpdateReqVo vo, @MappingTarget MenuPo po);

	/**
	 * 树形转换
	 *
	 * @param resVos res
	 * @return 菜单树
	 */
	default List<MenuTreeResVo> toMenuTree(List<MenuDto> resVos) {
		//排序、保证菜单顺序
		resVos.sort(Comparator.comparing(MenuDto::getNumber));
		Map<Long, MenuTreeResVo> map = new LinkedHashMap<>();
		MenuTreeResVo root = new MenuTreeResVo();
		root.setId(TreeNode.ROOT.getId());
		root.setName(TreeNode.ROOT.getName());
		map.put(TreeNode.ROOT.getId(), root);
		resVos.stream().map(this::toMenuTreeResVo).forEach(item -> map.put(item.getId(), item));
		map.values().stream().filter(item -> !item.getId().equals(TreeNode.ROOT.getId())).forEach(node -> {
			MenuTreeResVo parentNode = map.get(node.getParentId());
			if (parentNode.getChildren() == null) {
				parentNode.setChildren(new ArrayList<>());
			}
			parentNode.getChildren().add(node);
		});
		return map.get(TreeNode.ROOT.getId()).getChildren();
	}

	/**
	 * 树形转换
	 *
	 * @param resVos res
	 * @return 菜单树(包含虚拟根节点)
	 */
	default SelectTreeResVo<String, Long> toSelectTreeResVo(List<MenuDto> resVos) {
		//排序、保证菜单顺序
		resVos.sort(Comparator.comparing(MenuDto::getNumber));
		Map<Long, SelectTreeResVo<String, Long>> map = new LinkedHashMap<>();
		SelectTreeResVo<String, Long> root = new SelectTreeResVo<>();
		root.setValue(TreeNode.ROOT.getId());
		root.setLabel(TreeNode.ROOT.getName());
		map.put(TreeNode.ROOT.getId(), root);
		resVos.stream().map(this::toSelectTreeResVo).forEach(item -> map.put(item.getValue(), item));
		map.values().stream().filter(item -> !item.getValue().equals(TreeNode.ROOT.getId())).forEach(node -> {
			SelectTreeResVo<String, Long> parentNode = map.get(node.getParentId());
			if (parentNode.getChildren() == null) {
				parentNode.setChildren(new ArrayList<>());
			}
			parentNode.getChildren().add(node);
		});
		return map.get(TreeNode.ROOT.getId());
	}
}
