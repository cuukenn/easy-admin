package com.cuukenn.openstudysource.cloud.user.converter;

import com.cuukenn.openstudysource.cloud.user.dto.AuthUserDto;
import com.cuukenn.openstudysource.cloud.user.dto.UserDto;
import com.cuukenn.openstudysource.cloud.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
     * user2dto
     *
     * @param user user
     * @return dto
     */
    @Mapping(target = "accountExpired", expression = "java(user.isAccountInvalid())")
    @Mapping(target = "passwordExpired", expression = "java(user.isPasswordInvalid())")
    AuthUserDto toAuthUserDto(User user);

    /**
     * dto2user
     *
     * @param dto dto
     * @return user
     */
    User fromUserDto(UserDto dto);
}
