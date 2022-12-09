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
package io.github.cuukenn.easyadmin.module.system.controller.admin.log;

import io.github.cuukenn.easyadmin.module.system.controller.admin.log.vo.LogLevelPageFilterReqVo;
import io.github.cuukenn.easyadmin.module.system.controller.admin.log.vo.LogLevelResVo;
import io.github.cuukenn.easyadmin.module.system.controller.admin.log.vo.LogLevelUpdateReqVo;
import io.github.cuukenn.easyadmin.module.system.converter.log.LogLevelConverter;
import io.github.cuukenn.easyadmin.module.system.service.log.ILogLevelService;
import io.github.cuukenn.easyadmin.module.system.service.log.dto.LogLevelDto;
import io.github.cuukenn.easyframework.core.vo.ApiResult;
import io.github.cuukenn.easyframework.core.vo.PageWrapper;
import io.github.cuukenn.easyframework.web.toolkit.valid.UpdateGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author changgg
 */
@Tag(name = "管理后台-日志级别管理")
@RestController
@RequestMapping("/log/level")
@RequiredArgsConstructor
@Slf4j
public class LegLevelController {
	private final ILogLevelService service;

	@Operation(summary = "日志级别分页数据")
	@GetMapping("/page")
	public ApiResult<PageWrapper<LogLevelResVo>> page(@Valid LogLevelPageFilterReqVo vo) {
		PageWrapper<LogLevelDto> wrapper = service.page(vo);
		return ApiResult.success(PageWrapper.transform(wrapper, LogLevelConverter.INSTANCE::toLogLevelResVo));
	}

	@Operation(summary = "更新日志级别")
	@PutMapping("/update")
	public ApiResult<Boolean> update(@Validated(UpdateGroup.class) @RequestBody LogLevelUpdateReqVo vo) {
		service.update(vo);
		return ApiResult.success(true);
	}

	@Operation(summary = "重置所有日志级别")
	@PutMapping("/reset")
	public ApiResult<Boolean> reset() {
		service.reset();
		return ApiResult.success(true);
	}
}
