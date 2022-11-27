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
package io.github.cuukenn.easyadmin.service.permission.impl;

import io.github.cuukenn.easyadmin.controller.admin.permission.vo.MenuCreateReqVo;
import io.github.cuukenn.easyadmin.controller.admin.permission.vo.MenuUpdateReqVo;
import io.github.cuukenn.easyadmin.dao.MenuPo;
import io.github.cuukenn.easyadmin.dao.repository.MenuRepository;
import io.github.cuukenn.easyadmin.service.permission.IMenuService;
import io.github.cuukenn.easyadmin.service.permission.converter.MenuConverter;
import io.github.cuukenn.easyframework.core.exception.BizException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author changgg
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MenuServiceImpl implements IMenuService {
	private final MenuRepository repository;

	@Override
	public void create(MenuCreateReqVo vo) {
		MenuPo po = MenuConverter.INSTANCE.toSource(vo);
		repository.save(po);
	}

	@Override
	public void update(MenuUpdateReqVo vo) {
		MenuPo po = repository.findById(vo.getId()).orElseThrow(() -> {
			throw new BizException(-602, "指定菜单不存在,id=" + vo.getId());
		});
		MenuConverter.INSTANCE.update(vo, po);
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
	public void delete(Long menuId) {
		this.checkExist(menuId);
		repository.logicDelete(menuId);
	}

	private void checkExist(Long id) {
		if (!repository.existsById(id)) {
			throw new BizException(-602, "指定菜单不存在,id=" + id);
		}
	}
}
