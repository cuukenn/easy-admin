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
package io.github.cuukenn.easyadmin.module.system.service.log.impl;

import cn.hutool.core.util.StrUtil;
import io.github.cuukenn.easyadmin.module.system.controller.admin.log.vo.LogLevelVo;
import io.github.cuukenn.easyadmin.module.system.controller.admin.log.vo.LogLevelPageFilterReqVo;
import io.github.cuukenn.easyadmin.module.system.service.log.ILogLevelService;
import io.github.cuukenn.easyadmin.module.system.service.log.dto.LogLevelDto;
import io.github.cuukenn.easyframework.core.vo.PageWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.logging.LoggerConfiguration;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author changgg
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LogLevelServiceImpl implements ILogLevelService, InitializingBean {
	private final LoggingSystem loggingSystem;
	private final Collection<LoggerConfiguration> configurations = new LinkedList<>();

	@Override
	public PageWrapper<LogLevelDto> page(LogLevelPageFilterReqVo vo) {
		List<LoggerConfiguration> loggerConfigurations = loggingSystem.getLoggerConfigurations();
		if (StrUtil.isNotBlank(vo.getFilter())) {
			loggerConfigurations = loggerConfigurations.stream().filter(configuration -> configuration.getName().contains(vo.getFilter())).collect(Collectors.toList());
		}
		final long skipSize = (long) (vo.getPageNum() - 1) * vo.getPageSize();
		if (skipSize >= loggerConfigurations.size()) {
			return new PageWrapper<>(vo.getPageNum(), vo.getPageSize(), (long) loggerConfigurations.size(), Collections.emptyList());
		}
		return new PageWrapper<>(vo.getPageNum(), vo.getPageSize(), (long) loggerConfigurations.size(), loggerConfigurations.stream().skip(skipSize).limit(vo.getPageSize()).map(item -> new LogLevelDto(item.getName(), item.getEffectiveLevel())).collect(Collectors.toList()));
	}

	@Override
	public void update(LogLevelVo.LogLevelUpdateReqVo vo) {
		loggingSystem.setLogLevel(vo.getName(), vo.getLevel());
	}

	@Override
	public void reset() {
		configurations.forEach(configuration -> loggingSystem.setLogLevel(configuration.getName(), configuration.getEffectiveLevel()));
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.configurations.clear();
		for (LoggerConfiguration configuration : this.loggingSystem.getLoggerConfigurations()) {
			this.configurations.add(new LoggerConfiguration(configuration.getName(), configuration.getConfiguredLevel(), configuration.getEffectiveLevel()));
		}
	}
}
