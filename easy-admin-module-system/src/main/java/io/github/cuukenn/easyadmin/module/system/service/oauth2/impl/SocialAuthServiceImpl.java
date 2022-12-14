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
package io.github.cuukenn.easyadmin.module.system.service.oauth2.impl;

import io.github.cuukenn.easyadmin.module.system.service.oauth2.ISocialAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author changgg
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SocialAuthServiceImpl implements ISocialAuthService {
	@Override
	public void bindSocialUser() {

	}

	@Override
	public void unbindSocialUser() {

	}

	@Override
	public Long getBindUserId() {
		return null;
	}
}
