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

import io.github.cuukenn.easyframework.core.dao.IRepeatedLogicDeleteEntity;
import org.hibernate.annotations.Where;
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
@Where(clause = "deleted=0")
public abstract class AbstractJpaEntityRepeated implements IRepeatedLogicDeleteEntity {
	private static final long serialVersionUID = -5132345367955693237L;
	/**
	 * 创建人
	 */
	@CreatedBy
	private Long createdBy;
	/**
	 * 更新人
	 */
	@LastModifiedBy
	private Long lastModifiedBy;
	/**
	 * 创建时间
	 */
	@CreatedDate
	@Column(nullable = false)
	private Date createdTime;
	/**
	 * 更新时间
	 */
	@LastModifiedDate
	@Column(nullable = false)
	private Date lastModifiedTime;
	/**
	 * 更新时间
	 */
	private Long deleted;

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
	public Long getDeleted() {
		if (deleted == null) {
			this.deleted = 0L;
		}
		return deleted;
	}

	@Override
	public void setDeleted(Long deleted) {
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
		AbstractJpaEntityRepeated that = (AbstractJpaEntityRepeated) o;
		return this.getId() != null && getId().equals(that.getId());
	}

	@Override
	public int hashCode() {
		return getId() != null ? getId().hashCode() : 43;
	}
}
