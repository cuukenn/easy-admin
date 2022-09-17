package com.cuukenn.openstudysource.cloud.common.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;

/**
 * 分页数据
 *
 * @author changgg
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
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
    public static <T> PageResult<T> build(Long pageNum, Long pageSize, Long pageCount, Collection<T> data) {
        return new PageResult<>(new PageWrapper<>(pageNum, pageSize, pageCount, data));
    }

    private PageResult(PageWrapper<T> data) {
        super(ResultCode.SUCCESS, data);
    }

    @AllArgsConstructor
    @Data
    static class PageWrapper<T> {
        private Long pageNum;
        private Long pageSize;
        private Long pageTotal;
        private Collection<T> items;
    }
}
