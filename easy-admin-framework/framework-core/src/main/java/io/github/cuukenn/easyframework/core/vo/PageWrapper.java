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

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author changgg
 */
@Schema(title = "统一分页数据封装")
public class PageWrapper<T> {
	@Schema(title = "页码")
	private Integer pageNum;
	@Schema(title = "页大小")
	private Integer pageSize;
	@Schema(title = "数据总量")
	private Long pageTotal;
	@Schema(title = "分页数据")
	private List<T> items;

	private PageWrapper() {
	}

	public PageWrapper(Integer pageNum, Integer pageSize, Long pageTotal, List<T> items) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.pageTotal = pageTotal;
		this.items = items;
	}

	/**
	 * 分页数据
	 *
	 * @param data      数据
	 * @param transform 数据转换
	 * @param <T>       原始数据类型
	 * @param <R>       响应数据类型
	 * @return pageData
	 */
	public static <T, R> PageWrapper<R> transform(PageWrapper<T> data, Function<T, R> transform) {
		PageWrapper<R> pageWrapper = new PageWrapper<>();
		pageWrapper.setPageNum(data.getPageNum());
		pageWrapper.setPageSize(data.getPageSize());
		pageWrapper.setPageTotal(data.pageTotal);
		pageWrapper.setItems(data.getItems().stream().map(transform).collect(Collectors.toList()));
		return pageWrapper;
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

	public Long getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(Long pageTotal) {
		this.pageTotal = pageTotal;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}
}