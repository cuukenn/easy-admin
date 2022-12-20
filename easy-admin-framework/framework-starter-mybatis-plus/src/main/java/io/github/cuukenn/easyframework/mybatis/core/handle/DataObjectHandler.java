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
package io.github.cuukenn.easyframework.mybatis.core.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import io.github.cuukenn.easyframework.core.dao.IEntity;
import io.github.cuukenn.easyframework.core.api.CurrentUserService;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * @author changgg
 */
public class DataObjectHandler implements MetaObjectHandler {
	private final CurrentUserService currentUser;

	public DataObjectHandler(CurrentUserService currentUser) {
		this.currentUser = currentUser;
	}

	@Override
	public void insertFill(MetaObject metaObject) {
		if (metaObject instanceof IEntity) {
			IEntity entity = (IEntity) metaObject;
			Date now = new Date();
			entity.setCreatedTime(now);
			entity.setLastModifiedTime(now);
			entity.setCreatedBy(currentUser.getCurrentUserId());
			entity.setLastModifiedBy(currentUser.getCurrentUserId());
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		if (metaObject instanceof IEntity) {
			IEntity entity = (IEntity) metaObject;
			Date now = new Date();
			entity.setLastModifiedTime(now);
			entity.setLastModifiedBy(currentUser.getCurrentUserId());
		}
	}
}
