package com.cuukenn.openstudysource.cloud.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class PageQuery extends Query {
    private static final long serialVersionUID = -6184988778297716689L;
    @NotNull
    @Min(0)
    private Long pageNum;
    @Min(1L)
    @Max(50L)
    private Long pageSize = 20L;
    private Order order = new Order("id", true);

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Valid
    public static class Order {
        @NotEmpty
        private String column;
        private Boolean desc;
    }
}
