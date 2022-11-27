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
package io.github.cuukenn.easyadmin.dao;

import io.github.cuukenn.easyframework.jpa.dao.AbstractJpaEntityBool;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
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
@Entity(name = "sys_role")
@DynamicUpdate
@DynamicInsert
public class RolePo extends AbstractJpaEntityBool {
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 角色名称
	 */
	@Column(length = 20, unique = true)
	private String name;
	/**
	 * 角色权限标识
	 */
	@Column(length = 50, unique = true)
	private String permission;
	/**
	 * 角色状态(开启/关闭)
	 */
	private Boolean status;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
}
