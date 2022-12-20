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

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
	@Min(1)
	@Schema(title = "页码")
	private Integer pageNum;
	@Min(1L)
	@Max(50L)
	@Schema(title = "页大小")
	private Integer pageSize;
	@Schema(title = "排序字段")
	private String orderColumn;
	@Schema(title = "是否逆序排序")
	private Boolean orderDesc;

	public PageReqVo() {
		this.pageSize = 20;
		this.orderColumn = "id";
		this.orderDesc = true;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public Boolean getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(Boolean orderDesc) {
		this.orderDesc = orderDesc;
	}

	@Override
	public String toString() {
		return "PageReqVo{" + "pageNum=" + pageNum + ", pageSize=" + pageSize + ", orderColumn='" + orderColumn + '\'' + ", orderDesc=" + orderDesc + '}';
	}
}
