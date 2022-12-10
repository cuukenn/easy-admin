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

import io.github.cuukenn.easyadmin.module.system.enums.GenderType;
import io.github.cuukenn.easyframework.core.dao.IBoolLogicDelete;
import io.github.cuukenn.easyframework.jpa.dao.AbstractJpaEntityBool;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;
import org.hibernate.annotations.Where;

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
@Entity(name = "sys_user")
@Table(appliesTo = "sys_user", comment = "系统用户表")
@DynamicUpdate
@DynamicInsert
@Where(clause = IBoolLogicDelete.LOGIC_DELETE_SQL)
public class UserPo extends AbstractJpaEntityBool {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment("id")
	private Long id;
	@Comment("用户名")
	@Column(nullable = false, length = 20)
	private String username;
	@Comment("密码")
	@Column(length = 160)
	private String password;
	@Comment("昵称")
	@Column(length = 20)
	private String nickname;
	@Comment("性别")
	@Column(length = 4)
	private GenderType gender;
	@Comment("头像地址")
	@Column(length = 100)
	private String avatar;
	@Comment("地址")
	@Column(length = 200)
	private String location;
	@Comment("邮箱")
	@Column(length = 50)
	private String email;
	@ColumnDefault(value = "false")
	@Comment("状态(开启/关闭)")
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
