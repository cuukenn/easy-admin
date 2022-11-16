/*
 * Copyright 2022 the original author or authors.
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
package io.github.cuukenn.easyframework.mybatis.core.configure;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.IllegalSQLInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import io.github.cuukenn.easyframework.core.api.CurrentUserService;
import io.github.cuukenn.easyframework.mybatis.core.handle.DataObjectHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * mybatis-plus相关配置
 *
 * @author changgg
 */
@Configuration(proxyBeanMethods = false)
public class MyBatisPlusConfigure {
	private static final Logger logger = LoggerFactory.getLogger(MyBatisPlusConfigure.class);

	/**
	 * 防止恶意全表更新和删除
	 *
	 * @return 插件
	 */
	@Bean
	@ConditionalOnMissingBean
	public BlockAttackInnerInterceptor blockAttackInnerInterceptor() {
		return new BlockAttackInnerInterceptor();
	}

	/**
	 * 分页
	 *
	 * @return 插件
	 */
	@Bean
	@ConditionalOnMissingBean
	public PaginationInnerInterceptor paginationInnerInterceptor() {
		return new PaginationInnerInterceptor();
	}

	/**
	 * 创建时间、更新时间自动填充
	 *
	 * @return 时间元数据自动填充
	 */
	@Bean
	@ConditionalOnMissingBean
	public MetaObjectHandler timeMetaObjectWithoutSecurity(CurrentUserService currentUser) {
		logger.info("register mataObjectHandler");
		return new DataObjectHandler(currentUser);
	}

	/**
	 * sql性能规范
	 *
	 * @return 插件
	 */
	@Bean
	@Profile({"dev", "local", "test"})
	@ConditionalOnMissingBean
	public IllegalSQLInnerInterceptor innerInterceptor() {
		return new IllegalSQLInnerInterceptor();
	}
}
