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
@Entity(name = "sys_social_user")
@Table(appliesTo = "sys_social_user", comment = "社会化用户表")
@DynamicUpdate
@DynamicInsert
@Where(clause = IBoolLogicDelete.LOGIC_DELETE_SQL)
public class SocialUserPo extends AbstractJpaEntityBool {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment("id")
	private Long id;
	@Comment("第三方系统的唯一ID")
	@Column(nullable = false)
	private String uuid;
	@Comment("第三方用户来源")
	@Column(nullable = false)
	private String source;
	@Comment("用户的授权令牌")
	@Column(nullable = false)
	private String accessToken;
	@Comment("第三方用户的授权令牌的有效期")
	private String expireIn;
	@Comment("刷新令牌")
	private String refreshToken;
	@Comment("第三方用户的 open id")
	private String openId;
	@Comment("第三方用户的 ID")
	private String uid;
	@Comment("个别平台的授权信息")
	private String accessVode;
	@Comment("第三方用户的 union id")
	private String unionId;
	@Comment("第三方用户授予的权限")
	private String scope;
	@Comment("个别平台的授权信息")
	private String tokenType;
	@Comment("id token")
	private String idToken;
	@Comment("小米平台用户的附带属性")
	private String macAlgorithm;
	@Comment("小米平台用户的附带属性")
	private String macKey;
	@Comment("用户的授权code")
	private String code;
	@Comment("Twitter平台用户的附带属性")
	private String oauthToken;
	@Comment("Twitter平台用户的附带属性")
	private String oauthTokenSecret;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
}
