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
package io.github.cuukenn.easyframework.jpa.dao;

import io.github.cuukenn.easyframework.core.dao.IBoolLogicDeleteEntity;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author changgg
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractJpaEntityBool implements IBoolLogicDeleteEntity {
	private static final long serialVersionUID = -5132345367955693237L;
	/**
	 * 创建人
	 */
	@CreatedBy
	@Comment("创建人")
	private Long createdBy;
	/**
	 * 更新人
	 */
	@LastModifiedBy
	@Comment("修改人")
	private Long lastModifiedBy;
	/**
	 * 创建时间
	 */
	@CreatedDate
	@Column(nullable = false)
	@Comment("创建时间")
	private Date createdTime;
	/**
	 * 更新时间
	 */
	@LastModifiedDate
	@Column(nullable = false)
	@Comment("修改时间")
	private Date lastModifiedTime;
	/**
	 * 逻辑删除状态
	 */
	@ColumnDefault(value = "false")
	@Comment("逻辑删除：true 已删除,false 正常")
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
		AbstractJpaEntityBool that = (AbstractJpaEntityBool) o;
		return this.getId() != null && getId().equals(that.getId());
	}

	@Override
	public int hashCode() {
		return getId() != null ? getId().hashCode() : 43;
	}
}
