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
package io.github.cuukenn.dynamic.database.mongodb.support.context;

import io.github.cuukenn.dynamic.database.mongodb.support.DynamicContextValueParser;
import io.github.cuukenn.dynamic.database.mongodb.support.DynamicMongoContext;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 上下文
 *
 * @author changgg
 */
public class DefaultDynamicMongoContext implements DynamicMongoContext {
	private static final Logger logger = LoggerFactory.getLogger(DefaultDynamicMongoContext.class);
	/**
	 * 实例ID
	 */
	private String instanceId;
	/**
	 * 库名
	 */
	private String database;

	public DefaultDynamicMongoContext() {
		this.instanceId = "";
		this.database = "";
	}

	public DefaultDynamicMongoContext(String instanceId, String database) {
		this.instanceId = StringUtils.hasText(instanceId) ? instanceId : "";
		this.database = StringUtils.hasText(database) ? database : "";
	}

	@Override
	public void parseValue(MethodInvocation invocation, DynamicContextValueParser valueParser) {
		boolean isDebugEnabled = logger.isDebugEnabled();
		if (isDebugEnabled) {
			logger.debug("before parse context:{}", this);
		}
		if (valueParser != null) {
			if (StringUtils.hasText(this.instanceId)) {
				this.instanceId = valueParser.parse(invocation, this.instanceId);
			}
			if (StringUtils.hasText(this.database)) {
				this.database = valueParser.parse(invocation, this.database);
			}
		}
		if (isDebugEnabled) {
			logger.debug("after parse context:{}", this);
		}
	}

	@Override
	public String getInstanceId() {
		return instanceId;
	}

	@Override
	public String getDatabase() {
		return database;
	}

	@Override
	public String toString() {
		return "DefaultDynamicMongoContext{" +
			"instanceId='" + instanceId + '\'' +
			", database='" + database + '\'' +
			'}';
	}
}
