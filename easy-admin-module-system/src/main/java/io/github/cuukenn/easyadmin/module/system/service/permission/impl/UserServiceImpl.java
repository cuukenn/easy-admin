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
import io.github.cuukenn.easyadmin.module.system.controller.admin.permission.vo.UserVo;
import io.github.cuukenn.easyadmin.module.system.converter.permission.UserConverter;
import io.github.cuukenn.easyadmin.module.system.dao.UserPo;
import io.github.cuukenn.easyadmin.module.system.dao.repository.UserRepository;
import io.github.cuukenn.easyadmin.module.system.service.permission.IUserService;
import io.github.cuukenn.easyadmin.module.system.service.permission.dto.UserDto;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author changgg
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {
	private final UserRepository repository;

	@Override
	public UserDto get(Long id) {
		return UserConverter.INSTANCE.toUserDto(repository.findById(id).orElseThrow(() -> {
			throw new BizException(-602, "指定用户不存在,id=" + id);
		}));
	}

	@Override
	public List<UserDto> list() {
		List<UserPo> list = CollectionUtil.list(false, repository.findAll());
		return UserConverter.INSTANCE.toUserDto(list);
	}

	@Override
	public List<UserDto> list(Boolean status) {
		List<UserPo> list = CollectionUtil.list(false, repository.findAllByStatus(status));
		return UserConverter.INSTANCE.toUserDto(list);
	}

	@Override
	public PageWrapper<UserDto> list(PageReqVo vo) {
		Pageable page = PageRequest.of(
			vo.getPageNum() - 1, vo.getPageSize(),
			vo.getOrderDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, vo.getOrderColumn()
		);
		Page<UserPo> pages = repository.findAll(page);
		return new PageWrapper<>(pages.getNumber(), pages.getSize(), pages.getTotalElements(), UserConverter.INSTANCE.toUserDto(pages.getContent()));
	}

	@Override
	public void create(UserVo.CreateReqVo vo) {
		UserPo po = UserConverter.INSTANCE.toUserPo(vo);
		this.checkUnique(po);
		repository.save(po);
	}

	@Override
	public void update(UserVo.UpdateReqVo vo) {
		UserPo po = repository.findById(vo.getId()).orElseThrow(() -> {
			throw new BizException(-602, "指定用户不存在,id=" + vo.getId());
		});
		UserConverter.INSTANCE.update(vo, po);
		this.checkUnique(po);
		repository.save(po);
	}

	@Override
	public void update(Long id, Boolean status) {
		UserPo po = repository.findById(id).orElseThrow(() -> {
			throw new BizException(-602, "指定用户不存在,id=" + id);
		});
		po.setStatus(status);
		repository.save(po);
	}

	@Override
	@Transactional(rollbackFor = RuntimeException.class)
	public void delete(Long id) {
		this.checkExist(id);
		repository.logicDelete(id);
	}


	private void checkExist(Long id) {
		if (!repository.existsById(id)) {
			throw new BizException(-602, "指定用户不存在,id=" + id);
		}
	}


	private void checkUnique(UserPo po) {
		if (po == null) {
			return;
		}
		if (po.getId() != null) {
			if (StrUtil.isNotBlank(po.getUsername())) {
				if (repository.existsByUsernameAndIdNot(po.getUsername(), po.getId())) {
					throw new BizException(-602, "用户名重复");
				}
			}
		} else {
			if (StrUtil.isNotBlank(po.getUsername())) {
				if (repository.existsByUsername(po.getUsername())) {
					throw new BizException(-602, "用户名重复");
				}
			}
		}
	}
}
