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

import io.github.cuukenn.easyadmin.controller.admin.permission.vo.RoleCreateVo;
import io.github.cuukenn.easyadmin.controller.admin.permission.vo.RoleUpdateVo;
import io.github.cuukenn.easyadmin.dao.RolePo;
import io.github.cuukenn.easyadmin.dao.repository.RoleRepository;
import io.github.cuukenn.easyadmin.service.permission.IRoleService;
import io.github.cuukenn.easyadmin.service.permission.converter.RoleConverter;
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
public class RoleServiceImpl implements IRoleService {
	private final RoleRepository repository;

	@Override
	public void create(RoleCreateVo vo) {
		RolePo po = RoleConverter.INSTANCE.toSource(vo);
		repository.save(po);
	}

	@Override
	public void update(RoleUpdateVo vo) {
		RolePo rolePo = repository.findById(vo.getId()).orElseThrow(() -> {
			throw new BizException(-602, "指定角色不存在,id=" + vo.getId());
		});
		RoleConverter.INSTANCE.update(vo, rolePo);
		repository.save(rolePo);
	}

	@Override
	public void update(Long roleId, Boolean status) {
		RolePo po = repository.findById(roleId).orElseThrow(() -> {
			throw new BizException(-602, "指定角色不存在,id=" + roleId);
		});
		po.setStatus(status);
		repository.save(po);
	}

	@Override
	public void delete(Long roleId) {
		this.checkExist(roleId);
		repository.logicDelete(roleId);
	}


	private void checkExist(Long id) {
		if (!repository.existsById(id)) {
			throw new BizException(-602, "指定角色不存在,id=" + id);
		}
	}
}
