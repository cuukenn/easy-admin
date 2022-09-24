package com.cuukenn.openstudysource.cloud.user.converter;

import com.cuukenn.openstudysource.cloud.framework.entity.BaseConverter;
import com.cuukenn.openstudysource.cloud.user.dto.RoleDto;
import com.cuukenn.openstudysource.cloud.user.entity.Role;
import org.mapstruct.Mapper;

/**
 * @author changgg
 */
@Mapper(componentModel = "spring")
public interface RoleConverter extends BaseConverter<RoleDto, Role> {
}
