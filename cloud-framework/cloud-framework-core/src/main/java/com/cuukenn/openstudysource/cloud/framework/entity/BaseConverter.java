package com.cuukenn.openstudysource.cloud.framework.entity;

import com.cuukenn.openstudysource.cloud.framework.dto.Dto;

/**
 * @author changgg
 */
public interface BaseConverter<D extends Dto, E extends IEntity> {
    /**
     * dto2entity
     *
     * @param dto dto
     * @return entity
     */
    E fromDto(D dto);

    /**
     * entity2dto
     *
     * @param entity entity
     * @return dto
     */
    D toDto(E entity);
}
