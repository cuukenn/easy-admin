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

import io.github.cuukenn.easyadmin.module.system.controller.admin.log.vo.OperationLogResVo;
import io.github.cuukenn.easyadmin.module.system.converter.log.OperationLogConverter;
import io.github.cuukenn.easyadmin.module.system.service.log.IOperationLogService;
import io.github.cuukenn.easyadmin.module.system.service.log.dto.OperationLogDto;
import io.github.cuukenn.easyframework.core.vo.ApiResult;
import io.github.cuukenn.easyframework.core.vo.PageReqVo;
import io.github.cuukenn.easyframework.core.vo.PageWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author changgg
 */
@Tag(name = "管理后台-API操作记录")
@RestController
@RequestMapping("/operation-log")
@RequiredArgsConstructor
@Slf4j
public class OperationLogController {
	private final IOperationLogService service;

	@Operation(summary = "操作记录分页查询")
	@GetMapping("/page")
	public ApiResult<PageWrapper<OperationLogResVo>> page(@Valid PageReqVo vo) {
		PageWrapper<OperationLogDto> wrapper = service.page(vo);
		return ApiResult.success(PageWrapper.transform(wrapper, OperationLogConverter.INSTANCE::toOperationLogResVo));
	}
}
