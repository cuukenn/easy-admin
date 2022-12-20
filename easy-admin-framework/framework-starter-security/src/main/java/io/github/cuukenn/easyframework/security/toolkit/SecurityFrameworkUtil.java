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
package io.github.cuukenn.easyframework.security.toolkit;

import io.github.cuukenn.easyframework.security.toolkit.dto.AuthUserDto;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Optional;

/**
 * @author changgg
 */
public final class SecurityFrameworkUtil {
	public static final String AUTHORIZATION_BEARER = "Bearer";

	/**
	 * 设置用户
	 *
	 * @param request 请求
	 * @param dto     当前用户
	 */
	public static void setAuthentication(HttpServletRequest request, AuthUserDto dto) {
		if (dto == null) {
			return;
		}
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
			dto, null, Collections.emptyList()
		);
		authentication.setDetails(new WebAuthenticationDetailsSource());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	/**
	 * 获取令牌
	 *
	 * @param request 请求
	 * @param header  请求头
	 * @return 令牌
	 */
	@Nullable
	public static String obtainAuthentication(HttpServletRequest request, String header) {
		String value = request.getHeader(header);
		if (StringUtils.hasText(value) && value.startsWith(AUTHORIZATION_BEARER)) {
			return value.substring(AUTHORIZATION_BEARER.length() + 1).trim();
		}
		return null;
	}

	/**
	 * 获取当前用户id
	 *
	 * @return 用户id
	 */
	@Nullable
	public static Long getCurrentUserId() {
		return Optional.ofNullable(getCurrentUser()).map(AuthUserDto::getId).orElse(null);
	}

	/**
	 * 获取当前用户数据
	 *
	 * @return 用户数据
	 */
	@Nullable
	public static AuthUserDto getCurrentUser() {
		return (AuthUserDto) Optional.ofNullable(SecurityContextHolder.getContext()).map(SecurityContext::getAuthentication).map(Authentication::getPrincipal).filter(principal -> principal instanceof AuthUserDto).orElse(null);
	}
}
