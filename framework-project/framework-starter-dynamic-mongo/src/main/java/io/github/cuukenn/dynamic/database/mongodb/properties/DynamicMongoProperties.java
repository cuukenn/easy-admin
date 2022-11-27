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
package io.github.cuukenn.dynamic.database.mongodb.properties;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.Map;

/**
 * @author changgg
 */
@ConfigurationProperties(prefix = DynamicMongoProperties.PREFIX)
public class DynamicMongoProperties {
	public static final String PREFIX = "spring.data.mongodb.dynamic";
	private Map<String, MongoProperties> config;
	@NestedConfigurationProperty
	private DynamicMongoAopProperties aop = new DynamicMongoAopProperties();

	public Map<String, MongoProperties> getConfig() {
		return config;
	}

	public void setConfig(Map<String, MongoProperties> config) {
		this.config = config;
	}

	public DynamicMongoAopProperties getAop() {
		return aop;
	}

	public void setAop(DynamicMongoAopProperties aop) {
		this.aop = aop;
	}
}
