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
package io.github.cuukenn.easyframework.security.toolkit.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author changgg
 */
public class AuthUserDto implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 用户id
	 */
	private Long id;
	/**
	 * 租户id
	 */
	private Long tenantId;
	/**
	 * 用户类型
	 */
	private Integer userType;
	/**
	 * 授权范围
	 */
	private List<String> scopes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public List<String> getScopes() {
		return scopes;
	}

	public void setScopes(List<String> scopes) {
		this.scopes = scopes;
	}
}
