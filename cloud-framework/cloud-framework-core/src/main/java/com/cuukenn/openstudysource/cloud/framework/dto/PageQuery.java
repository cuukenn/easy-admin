package com.cuukenn.openstudysource.cloud.framework.dto;

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
public class PageQuery extends Query {
    private static final long serialVersionUID = -6184988778297716689L;
    @NotNull
    @Min(0)
    private Long pageNum;
    @Min(1L)
    @Max(50L)
    private Long pageSize = 20L;
    private Order order = new Order("id", true);

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
}
