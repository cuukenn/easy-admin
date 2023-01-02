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
package io.github.cuukenn.easyadmin.module.system.dao;

import io.github.cuukenn.easyframework.core.dao.IBoolLogicDelete;
import io.github.cuukenn.easyframework.jpa.dao.AbstractJpaEntityBool;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Table;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author changgg
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "infra_operation_log")
@Table(appliesTo = "infra_operation_log", comment = "基础设施-操作记录表")
@Where(clause = IBoolLogicDelete.LOGIC_DELETE_SQL)
public class OperationLogPo extends AbstractJpaEntityBool {
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Comment("用户ID")
	private Long userId;
	@Comment("用户IP")
	private String userIp;
	@Comment("用户agent")
	private String userAgent;
	@Comment("资源定位符")
	private String uri;
	@Comment("资源方法")
	private String method;
	@Comment("资源描述")
	private String uriDescription;
	@Comment("资源处理时间")
	private Long duration;
	@Comment("资源请求参数")
	private String params;
	@Comment("资源响应结果")
	private String result;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object object) {
		return super.equals(object);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}