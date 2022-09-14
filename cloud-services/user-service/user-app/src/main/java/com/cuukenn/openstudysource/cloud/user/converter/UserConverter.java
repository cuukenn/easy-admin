package com.cuukenn.openstudysource.cloud.user.converter;

import com.cuukenn.openstudysource.cloud.user.dto.UserDto;
import com.cuukenn.openstudysource.cloud.user.entity.User;
import org.mapstruct.Mapper;

/**
 * @author changgg
 */
@Mapper(componentModel = "spring")
public interface UserConverter {
    /**
     * user2dto
     *
     * @param user user
     * @return dto
     */
    UserDto toUserDto(User user);

    /**
     * dto2user
     *
     * @param dto dto
     * @return user
     */
    User fromUserDto(UserDto dto);
}
