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
package io.github.cuukenn.dynamic.database.mongodb.configurate;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import io.github.cuukenn.dynamic.database.mongodb.support.DynamicMongoClientBuilder;
import io.github.cuukenn.dynamic.database.mongodb.support.DynamicMongoClientFactory;
import io.github.cuukenn.dynamic.database.mongodb.support.DynamicMongoClientPropertiesCustomizer;
import io.github.cuukenn.dynamic.database.mongodb.support.DynamicMongoClientSettingsCustomizer;
import io.github.cuukenn.dynamic.database.mongodb.support.factory.DynamicMongoDatabaseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.mongo.MongoClientFactory;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.mongo.MongoPropertiesClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDatabaseFactory;

import java.util.Collections;

/**
 * @author changgg
 */
@Configuration(proxyBeanMethods = false)
public class DynamicMongoClientConfiguration {
	private final Logger logger = LoggerFactory.getLogger(DynamicMongoClientConfiguration.class);

	@Bean
	@ConditionalOnMissingBean
	public MongoDatabaseFactory mongoClientFactory(MongoClient mongoClient, MongoProperties properties,
												   DynamicMongoClientFactory dynamicMongoClientFactory) {
		logger.info("register dynamic mongo database factory");
		return new DynamicMongoDatabaseFactory(mongoClient, properties.getMongoClientDatabase(), dynamicMongoClientFactory);
	}

	@Bean
	@ConditionalOnMissingBean
	public DynamicMongoClientBuilder dynamicMongoClientBuilder(MongoProperties primaryProperties,
															   ObjectProvider<DynamicMongoClientSettingsCustomizer> settingsCustomizers,
															   ObjectProvider<DynamicMongoClientPropertiesCustomizer> propertiesCustomizers) {
		logger.info("register dynamic mongo client builder");
		return ((instanceId, properties) -> {
			propertiesCustomizers.orderedStream().forEach(customizers -> customizers.customize(primaryProperties, instanceId, properties));
			return new MongoClientFactory(Collections.singletonList(clientSettingsBuilder -> settingsCustomizers.orderedStream().forEach(customizers -> customizers.customize(
				instanceId,
				clientSettingsBuilder,
				properties
			)))).createMongoClient(MongoClientSettings.builder().build());
		});
	}

	@Bean("dynamicMongoClientSettingsCustomizer")
	@ConditionalOnMissingBean(name = "dynamicMongoClientSettingsCustomizer")
	public DynamicMongoClientSettingsCustomizer dynamicMongoClientSettingsCustomizer(Environment environment) {
		logger.info("register dynamic mongo client setting customizer");
		return (instanceId, dynamic, properties) -> new MongoPropertiesClientSettingsBuilderCustomizer(properties, environment).customize(dynamic);
	}
}
