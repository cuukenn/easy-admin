/*
 * Copyright 2022 the original author or authors.
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
package io.github.cuukenn.easyframework.biz.operationlog.core.pojo.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.cuukenn.easyframework.mybatis.core.pojo.dao.AbstractMybatisEntityRepeated;

/**
 * @author changgg
 */
@TableName("infra_operation_log")
public class OperationLog extends AbstractMybatisEntityRepeated {
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * traceId
	 */
	private String traceId;
	/**
	 * 模块
	 */
	private String module;
	/**
	 * 资源定位符
	 */
	private String uri;
	/**
	 * 用户IP
	 */
	private String userIp;
	/**
	 * 用户agent
	 */
	private String userAgent;
	/**
	 * 开始时间
	 */
	private Long startTime;
	/**
	 * 结束时间
	 */
	private Long endTime;
	/**
	 * 持续时间
	 */
	private Long duration;
	/**
	 * 资源方法
	 */
	private String uriMethod;
	/**
	 * 资源参数
	 */
	private String uriParams;
	/**
	 * 资源结果code
	 */
	private String uriResultCode;
	/**
	 * 资源结果载荷
	 */
	private String uriResultMessage;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getUriMethod() {
		return uriMethod;
	}

	public void setUriMethod(String uriMethod) {
		this.uriMethod = uriMethod;
	}

	public String getUriParams() {
		return uriParams;
	}

	public void setUriParams(String uriParams) {
		this.uriParams = uriParams;
	}

	public String getUriResultCode() {
		return uriResultCode;
	}

	public void setUriResultCode(String uriResultCode) {
		this.uriResultCode = uriResultCode;
	}

	public String getUriResultMessage() {
		return uriResultMessage;
	}

	public void setUriResultMessage(String uriResultMessage) {
		this.uriResultMessage = uriResultMessage;
	}

	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return "OperationLog{" +
			"userId=" + userId +
			", traceId='" + traceId + '\'' +
			", module='" + module + '\'' +
			", uri='" + uri + '\'' +
			", userIp='" + userIp + '\'' +
			", userAgent='" + userAgent + '\'' +
			", startTime=" + startTime +
			", endTime=" + endTime +
			", duration=" + duration +
			", uriMethod='" + uriMethod + '\'' +
			", uriParams='" + uriParams + '\'' +
			", uriResultCode='" + uriResultCode + '\'' +
			", uriResultMessage='" + uriResultMessage + '\'' +
			'}';
	}
}