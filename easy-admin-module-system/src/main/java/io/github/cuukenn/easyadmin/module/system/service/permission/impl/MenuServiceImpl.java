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
package io.github.cuukenn.easyadmin.module.system.service.permission.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import io.github.cuukenn.easyadmin.module.system.controller.admin.permission.vo.MenuCreateReqVo;
import io.github.cuukenn.easyadmin.module.system.controller.admin.permission.vo.MenuUpdateReqVo;
import io.github.cuukenn.easyadmin.module.system.converter.permission.MenuConverter;
import io.github.cuukenn.easyadmin.module.system.dao.MenuPo;
import io.github.cuukenn.easyadmin.module.system.dao.repository.MenuRepository;
import io.github.cuukenn.easyadmin.module.system.enums.MenuType;
import io.github.cuukenn.easyadmin.module.system.enums.TreeNode;
import io.github.cuukenn.easyadmin.module.system.service.permission.IMenuService;
import io.github.cuukenn.easyadmin.module.system.service.permission.dto.MenuDto;
import io.github.cuukenn.easyframework.core.exception.BizException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author changgg
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MenuServiceImpl implements IMenuService {
	private final MenuRepository repository;

	@Override
	public MenuDto get(Long id) {
		return MenuConverter.INSTANCE.toMenuDto(repository.findById(id).orElseThrow(() -> {
			throw new BizException(-602, "指定菜单不存在,id=" + id);
		}));
	}

	@Override
	public Boolean existsChildren(Long id) {
		return Optional.ofNullable(id).map(repository::existsByParentId).orElse(null);
	}

	@Override
	public List<MenuDto> list() {
		List<MenuPo> list = CollectionUtil.list(false, repository.findAll());
		return MenuConverter.INSTANCE.toMenuDto(list);
	}

	@Override
	public List<MenuDto> list(Set<MenuType> types, Boolean status) {
		List<MenuPo> list = CollectionUtil.list(false, repository.findAllByStatusAndTypeIn(status, types));
		return MenuConverter.INSTANCE.toMenuDto(list);
	}

	@Override
	public void create(MenuCreateReqVo vo) {
		MenuPo po = MenuConverter.INSTANCE.toMenuPo(vo);
		if (po.getParentId() == null) {
			po.setParentId(TreeNode.ROOT.getId());
		}
		this.checkUnique(po);
		repository.save(po);
	}

	@Override
	public void update(MenuUpdateReqVo vo) {
		MenuPo po = repository.findById(vo.getId()).orElseThrow(() -> {
			throw new BizException(-602, "指定菜单不存在,id=" + vo.getId());
		});
		MenuConverter.INSTANCE.update(vo, po);
		this.checkUnique(po);
		repository.save(po);
	}

	@Override
	public void update(Long menuId, Boolean status) {
		MenuPo po = repository.findById(menuId).orElseThrow(() -> {
			throw new BizException(-602, "指定菜单不存在,id=" + menuId);
		});
		po.setStatus(status);
		repository.save(po);
	}

	@Override
	@Transactional(rollbackFor = RuntimeException.class)
	public void delete(Long menuId) {
		this.checkExist(menuId);
		repository.logicDelete(menuId);
	}

	private void checkExist(Long id) {
		if (!repository.existsById(id)) {
			throw new BizException(-602, "指定菜单不存在,id=" + id);
		}
	}

	private void checkUnique(MenuPo po) {
		if (po == null) {
			return;
		}
		if (po.getId() != null) {
			if (StrUtil.isNotBlank(po.getName())) {
				if (repository.existsByNameAndIdNot(po.getName(), po.getId())) {
					throw new BizException(-602, "菜单名重复");
				}
			}
		} else {
			if (StrUtil.isNotBlank(po.getName())) {
				if (repository.existsByName(po.getName())) {
					throw new BizException(-602, "菜单名重复");
				}
			}
		}
	}
}
