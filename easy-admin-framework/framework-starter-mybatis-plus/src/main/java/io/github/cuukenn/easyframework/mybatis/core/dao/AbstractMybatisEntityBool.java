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
package io.github.cuukenn.easyframework.mybatis.core.dao;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.github.cuukenn.easyframework.core.dao.IBoolLogicDeleteEntity;

import java.util.Date;

/**
 * @author changgg
 */
public abstract class AbstractMybatisEntityBool implements IBoolLogicDeleteEntity {
	private static final long serialVersionUID = -5132345367955693237L;
	/**
	 * 创建人
	 */
	@TableField(fill = FieldFill.INSERT)
	private Long createdBy;
	/**
	 * 更新人
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long lastModifiedBy;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createdTime;
	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date lastModifiedTime;
	/**
	 * 更新时间
	 */
	@TableLogic(value = "false", delval = "true")
	private Boolean deleted;

	@Override
	public Long getCreatedBy() {
		return createdBy;
	}

	@Override
	public Long getLastModifiedBy() {
		return lastModifiedBy;
	}

	@Override
	public Date getCreatedTime() {
		return createdTime;
	}

	@Override
	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	@Override
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public void setLastModifiedBy(Long lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	@Override
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Override
	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	@Override
	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		AbstractMybatisEntityBool that = (AbstractMybatisEntityBool) o;
		return this.getId() != null && getId().equals(that.getId());
	}

	@Override
	public int hashCode() {
		return getId() != null ? getId().hashCode() : 43;
	}
}
