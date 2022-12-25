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

import io.github.cuukenn.easyframework.security.core.SecurityConfigurer;
import io.github.cuukenn.easyframework.security.toolkit.dto.AuthUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author changgg
 */
public final class SecurityFrameworkUtil {
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfigurer.class);
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

	/**
	 * 获取可以忽略权限认证得url列表
	 * {@link PermitAll}
	 *
	 * @param requestMappingHandlerMapping apis
	 * @return uri列表
	 */
	@NonNull
	public static Map<HttpMethod, List<String>> getPermitAllUrlsFromAnnotations(RequestMappingHandlerMapping requestMappingHandlerMapping) {
		Map<HttpMethod, List<String>> ignoreUris = new LinkedHashMap<>();
		// 获得接口对应的 HandlerMethod 集合
		Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = requestMappingHandlerMapping.getHandlerMethods();
		// 获得有 @PermitAll 注解的接口
		for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethodMap.entrySet()) {
			HandlerMethod handlerMethod = entry.getValue();
			if (!handlerMethod.hasMethodAnnotation(PermitAll.class)) {
				continue;
			}
			if (entry.getKey().getPatternsCondition() == null) {
				continue;
			}
			Set<String> uris = entry.getKey().getPatternsCondition().getPatterns();
			// 根据请求方法，添加到 ignoreUris 结果
			entry.getKey().getMethodsCondition().getMethods().forEach(requestMethod -> {
				switch (requestMethod) {
					case GET:
						ignoreUris.computeIfAbsent(HttpMethod.GET, key -> new ArrayList<>()).addAll(uris);
						break;
					case POST:
						ignoreUris.computeIfAbsent(HttpMethod.POST, key -> new ArrayList<>()).addAll(uris);
						break;
					case PUT:
						ignoreUris.computeIfAbsent(HttpMethod.PUT, key -> new ArrayList<>()).addAll(uris);
						break;
					case DELETE:
						ignoreUris.computeIfAbsent(HttpMethod.DELETE, key -> new ArrayList<>()).addAll(uris);
						break;
					default:
						logger.warn("[not support the special type to set ignore uri][method:[{}],uris:[{}]]", requestMethod, uris);
						break;
				}
			});
		}
		return ignoreUris;
	}
}
