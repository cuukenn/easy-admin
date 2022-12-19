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
package io.github.cuukenn.dynamic.database.mongodb.support;

import com.mongodb.MongoClientSettings;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;

/**
 * @author changgg
 */
@FunctionalInterface
public interface DynamicMongoClientSettingsCustomizer {
	/**
	 * 支持主库和从库进行一些特殊操作
	 *
	 * @param instanceId 实例ID
	 * @param dynamic    动态库
	 * @param properties 配置
	 */
	void customize(String instanceId, MongoClientSettings.Builder dynamic, MongoProperties properties);
}
