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
package io.github.cuukenn.easyframework.core.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 分页查询
 *
 * @author changgg
 */
@Schema(title = "分页请求")
public class PageReqVo {
	private static final long serialVersionUID = -6184988778297716689L;
	@NotNull
	@Min(0)
	private Long pageNum;
	@Min(1L)
	@Max(50L)
	private Long pageSize;
	private Order order;

	public PageReqVo() {
		this.pageSize = 20L;
		this.order = new Order("id", true);
	}

	@Valid
	public static class Order {
		@NotEmpty
		private String column;
		private Boolean desc;

		public Order(String column, Boolean desc) {
			this.column = column;
			this.desc = desc;
		}

		public String getColumn() {
			return column;
		}

		public void setColumn(String column) {
			this.column = column;
		}

		public Boolean getDesc() {
			return desc;
		}

		public void setDesc(Boolean desc) {
			this.desc = desc;
		}
	}

	public Long getPageNum() {
		return pageNum;
	}

	public void setPageNum(Long pageNum) {
		this.pageNum = pageNum;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "PageReqVO{" +
			"pageNum=" + pageNum +
			", pageSize=" + pageSize +
			", order=" + order +
			'}';
	}
}
