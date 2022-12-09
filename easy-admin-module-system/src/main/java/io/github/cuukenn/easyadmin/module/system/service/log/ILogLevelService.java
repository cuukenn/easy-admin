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
package io.github.cuukenn.easyadmin.module.system.service.log;

import io.github.cuukenn.easyadmin.module.system.controller.admin.log.vo.LogLevelPageFilterReqVo;
import io.github.cuukenn.easyadmin.module.system.controller.admin.log.vo.LogLevelUpdateReqVo;
import io.github.cuukenn.easyadmin.module.system.service.log.dto.LogLevelDto;
import io.github.cuukenn.easyframework.core.vo.PageWrapper;

/**
 * @author changgg
 */
public interface ILogLevelService {
	/**
	 * 分页查询日志级别
	 *
	 * @param vo vo
	 * @return 分页数据
	 */
	PageWrapper<LogLevelDto> page(LogLevelPageFilterReqVo vo);

	/**
	 * 更新日志级别
	 *
	 * @param vo vo
	 */
	void update(LogLevelUpdateReqVo vo);

	/**
	 * 重置为初始日志级别
	 */
	void reset();
}
