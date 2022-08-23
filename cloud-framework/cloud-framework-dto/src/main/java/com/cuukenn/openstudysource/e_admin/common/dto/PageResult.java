package com.cuukenn.openstudysource.e_admin.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

/**
 * 分页数据
 *
 * @author changgg
 */
public class PageResult<T> extends Result<PageResult.PageWrapper<T>> {
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
  public static <T> PageResult<T> build(Integer pageNum, Integer pageSize, Integer pageCount, Collection<T> data) {
    return new PageResult<>(new PageWrapper<>(pageNum, pageSize, pageCount, data));
  }

  private PageResult(PageWrapper<T> data) {
    super(ResultCode.SUCCESS, data);
  }

  @AllArgsConstructor
  @Data
  static class PageWrapper<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Integer pageTotal;
    private Collection<T> items;
  }
}
