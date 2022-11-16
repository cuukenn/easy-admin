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
package io.github.cuukenn.easyframework.core.pojo;

import io.github.cuukenn.easyframework.core.exception.enums.GlobalResultCode;

import java.util.Collection;

/**
 * 分页数据
 *
 * @author changgg
 */
public class ApiPageResult<T> extends ApiResult<ApiPageResult.PageWrapper<T>> {
	private ApiPageResult(IResult resultCode, PageWrapper<T> data) {
		super(resultCode, data);
	}

	/**
	 * 分页数据
	 *
	 * @param pageNum   页码
	 * @param pageSize  页大小
	 * @param pageCount 页总数
	 * @param data      数据
	 * @param <T>       数据类型
	 * @return pageData
	 */
	public static <T> ApiPageResult<T> success(Long pageNum, Long pageSize, Long pageCount, Collection<T> data) {
		return new ApiPageResult<>(new PageWrapper<>(pageNum, pageSize, pageCount, data));
	}

	private ApiPageResult(PageWrapper<T> data) {
		super(GlobalResultCode.SUCCESS, data);
	}

	static class PageWrapper<T> {
		private Long pageNum;
		private Long pageSize;
		private Long pageTotal;
		private Collection<T> items;

		public PageWrapper(Long pageNum, Long pageSize, Long pageTotal, Collection<T> items) {
			this.pageNum = pageNum;
			this.pageSize = pageSize;
			this.pageTotal = pageTotal;
			this.items = items;
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

		public Long getPageTotal() {
			return pageTotal;
		}

		public void setPageTotal(Long pageTotal) {
			this.pageTotal = pageTotal;
		}

		public Collection<T> getItems() {
			return items;
		}

		public void setItems(Collection<T> items) {
			this.items = items;
		}
	}
}
