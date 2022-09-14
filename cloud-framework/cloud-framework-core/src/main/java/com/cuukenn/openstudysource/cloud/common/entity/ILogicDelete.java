package com.cuukenn.openstudysource.cloud.common.entity;

/**
 * @author changgg
 */
public interface ILogicDelete {
    /**
     * 删除标志列名
     */
    String IS_DELETED = "is_deleted";

    /**
     * 状态码
     *
     * @return deleted
     */
    Boolean getLogicDeleted();

    /**
     * 是否被删除
     *
     * @return delete status
     */
    default boolean deleted() {
        return Boolean.TRUE.equals(getLogicDeleted());
    }
}
