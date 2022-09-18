package com.cuukenn.openstudysource.cloud.framework.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author changgg
 */
public interface IEntity extends Serializable {
    /**
     * 匿名用户
     */
    String ANONYMOUS = "-1";
    /**
     * ID物理主键
     */
    String ID = "ID";
    /**
     * 创建人列名
     */
    String CREATED_BY = "created_by";
    /**
     * 上次更新人列名
     */
    String LAST_MODIFIED_BY = "last_modified_by";
    /**
     * 创建时间列名
     */
    String CREATED_TIME = "created_time";
    /**
     * 更新时间列名
     */
    String LAST_MODIFIED_TIME = "last_modified_time";

    /**
     * 标识
     *
     * @return id
     */
    Long getId();

    /**
     * 创建人
     *
     * @return create_by
     */
    String getCreatedBy();

    /**
     * 修改人
     *
     * @return last_modified_by
     */
    String getLastModifiedBy();

    /**
     * 创建时间
     *
     * @return create_time
     */
    Date getCreatedTime();

    /**
     * 修改时间
     *
     * @return last_modified_time
     */
    Date getLastModifiedTime();
}
