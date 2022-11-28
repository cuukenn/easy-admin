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
import io.github.cuukenn.easyadmin.converter.permission.RoleConverter;
import io.github.cuukenn.easyadmin.dao.RolePo;
import io.github.cuukenn.easyadmin.dao.repository.RoleRepository;
import io.github.cuukenn.easyadmin.service.permission.IRoleService;
import io.github.cuukenn.easyadmin.service.permission.dto.RoleDto;
import io.github.cuukenn.easyframework.core.exception.BizException;
import io.github.cuukenn.easyframework.core.vo.PageReqVo;
import io.github.cuukenn.easyframework.core.vo.PageWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public RoleDto get(Long id) {
		return RoleConverter.INSTANCE.toRoleDto(repository.findById(id).orElseThrow(() -> {
			throw new BizException(-602, "指定角色不存在,id=" + id);
		}));
	}

	@Override
	public PageWrapper<RoleDto> list(PageReqVo vo) {
		Pageable page = PageRequest.of(
			vo.getPageNum() - 1, vo.getPageSize(),
			vo.getOrderDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, vo.getOrderColumn()
		);
		Page<RolePo> pages = repository.findAll(page);
		return new PageWrapper<>(pages.getNumber(), pages.getSize(), pages.getTotalElements(), RoleConverter.INSTANCE.toRoleDto(pages.getContent()));
	}

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
