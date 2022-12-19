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
package io.github.cuukenn.dynamic.database.mongodb.support.factory;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import io.github.cuukenn.dynamic.database.mongodb.support.DynamicMongoClientFactory;
import io.github.cuukenn.dynamic.database.mongodb.support.DynamicMongoContext;
import io.github.cuukenn.dynamic.database.mongodb.support.context.DynamicMongoDatabaseContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @author changgg
 */
public abstract class AbstractDynamicMongoDatabaseFactory extends SimpleMongoClientDatabaseFactory {
	private final Logger logger = LoggerFactory.getLogger(AbstractDynamicMongoDatabaseFactory.class);
	protected final DynamicMongoClientFactory dynamicMongoClientFactory;

	protected AbstractDynamicMongoDatabaseFactory(MongoClient mongoClient, String databaseName, DynamicMongoClientFactory dynamicMongoClientFactory) {
		super(mongoClient, databaseName);
		this.dynamicMongoClientFactory = dynamicMongoClientFactory;
	}

	@Override
	protected MongoClient getMongoClient() {
		final DynamicMongoContext context = getContext();
		final MongoClient client = context != null && StringUtils.hasText(context.getInstanceId()) ? this.dynamicMongoClientFactory.getDynamicMongoClient(context.getInstanceId()) : super.getMongoClient();
		Assert.notNull(client, "no dynamic mongo database instance found for " + context);
		return client;
	}

	@Override
	public MongoDatabase getMongoDatabase() throws DataAccessException {
		final DynamicMongoContext context = getContext();
		return context != null && StringUtils.hasText(context.getDatabase()) ? getMongoDatabase(context.getDatabase()) : super.getMongoDatabase();
	}

	protected DynamicMongoContext getContext() {
		final DynamicMongoContext context = DynamicMongoDatabaseContextHolder.peek();
		if (logger.isDebugEnabled()) {
			logger.debug("excepted dynamic mongodb instance is [{}],use default mongo client:[{}]", context, context != null && !StringUtils.hasText(context.getInstanceId()));
		}
		return context;
	}
}
