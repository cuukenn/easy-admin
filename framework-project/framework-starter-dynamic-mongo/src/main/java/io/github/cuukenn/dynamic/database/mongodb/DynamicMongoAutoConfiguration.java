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
package io.github.cuukenn.dynamic.database.mongodb;

import io.github.cuukenn.dynamic.database.mongodb.configurate.DynamicMongoAopConfiguration;
import io.github.cuukenn.dynamic.database.mongodb.configurate.DynamicMongoClientConfiguration;
import io.github.cuukenn.dynamic.database.mongodb.configurate.DynamicMongoClientFactoryConfiguration;
import io.github.cuukenn.dynamic.database.mongodb.properties.DynamicMongoProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author changgg
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({DynamicMongoProperties.class})
@ConditionalOnProperty(prefix = DynamicMongoProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@Import({DynamicMongoAopConfiguration.class, DynamicMongoClientConfiguration.class, DynamicMongoClientFactoryConfiguration.class})
@AutoConfigureAfter(MongoAutoConfiguration.class)
public class DynamicMongoAutoConfiguration {
}
