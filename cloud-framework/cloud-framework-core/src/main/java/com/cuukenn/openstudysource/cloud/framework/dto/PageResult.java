package com.cuukenn.openstudysource.cloud.framework.dto;

import java.util.Collection;

/**
 * 分页数据
 *
 * @author changgg
 */
public class PageResult<T> extends Result<PageResult.PageWrapper<T>> {
    private PageResult(IResult resultCode, PageWrapper<T> data) {
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
    public static <T> PageResult<T> build(Long pageNum, Long pageSize, Long pageCount, Collection<T> data) {
        return new PageResult<>(new PageWrapper<>(pageNum, pageSize, pageCount, data));
    }

    private PageResult(PageWrapper<T> data) {
        super(ResultCode.SUCCESS, data);
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
