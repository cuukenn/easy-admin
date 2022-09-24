package com.cuukenn.openstudysource.cloud.user.web;

import com.cuukenn.openstudysource.cloud.framework.auth.annotation.AnonymousAccess;
import com.cuukenn.openstudysource.cloud.framework.dto.PageQuery;
import com.cuukenn.openstudysource.cloud.framework.dto.PageResult;
import com.cuukenn.openstudysource.cloud.framework.dto.Result;
import com.cuukenn.openstudysource.cloud.user.api.IUserApi;
import com.cuukenn.openstudysource.cloud.user.dto.AuthUserDto;
import com.cuukenn.openstudysource.cloud.user.dto.ChangePasswordCommand;
import com.cuukenn.openstudysource.cloud.user.dto.CheckPasswdCommand;
import com.cuukenn.openstudysource.cloud.user.dto.UpdatePasswordCommand;
import com.cuukenn.openstudysource.cloud.user.dto.UpdateUserCommand;
import com.cuukenn.openstudysource.cloud.user.dto.UserDto;
import com.cuukenn.openstudysource.cloud.user.service.IUserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changgg
 */
@SuppressWarnings("AlibabaServiceOrDaoClassShouldEndWithImpl")
@RestController
@RequestMapping(IUserApi.MAPPING)
public class UserController implements IUserApi {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Override
    @Secured("admin")
    public PageResult<UserDto> list(PageQuery query) {
        return userService.list(query);
    }

    @Override
    @Secured("admin")
    public Result<Void> add(UserDto dto) {
        userService.add(dto);
        return Result.success();
    }

    @Override
    @Secured("admin")
    public Result<Void> update(UpdateUserCommand command) {
        userService.update(command);
        return Result.success();
    }

    @Override
    @Secured("admin")
    public Result<Void> delete(Long uid) {
        userService.delete(uid);
        return Result.success();
    }

    @Override
    @AnonymousAccess
    public AuthUserDto findUserByUsername(String username) {
        return userService.findByUsername(username);
    }

    @Override
    public UserDto passwordAuth(CheckPasswdCommand command) {
        return userService.passwordAuth(command);
    }

    @Override
    @Secured("admin")
    public Result<Void> resetPassword(Long uid) {
        userService.resetPassword(uid);
        return Result.success();
    }

    @Override
    public Result<Void> changePassword(ChangePasswordCommand command) {
        userService.changePassword(command);
        return Result.success();
    }

    @Override
    @PreAuthorize("ex.isCurrentUser(command.username)")
    public Result<Void> updatePassword(UpdatePasswordCommand command) {
        userService.updatePassword(command);
        return Result.success();
    }
}