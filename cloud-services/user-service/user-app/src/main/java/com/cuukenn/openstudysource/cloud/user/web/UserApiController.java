package com.cuukenn.openstudysource.cloud.user.web;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaIgnore;
import com.cuukenn.openstudysource.cloud.common.dto.PageQuery;
import com.cuukenn.openstudysource.cloud.common.dto.PageResult;
import com.cuukenn.openstudysource.cloud.common.dto.Result;
import com.cuukenn.openstudysource.cloud.user.api.IUserApi;
import com.cuukenn.openstudysource.cloud.user.dto.ChangePasswordCommand;
import com.cuukenn.openstudysource.cloud.user.dto.CheckPasswdCommand;
import com.cuukenn.openstudysource.cloud.user.dto.UpdateUserCommand;
import com.cuukenn.openstudysource.cloud.user.dto.UserDto;
import com.cuukenn.openstudysource.cloud.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

/**
 * @author changgg
 */
@SuppressWarnings("AlibabaServiceOrDaoClassShouldEndWithImpl")
@RestController
@RequestMapping(IUserApi.MAPPING)
@Slf4j
@RequiredArgsConstructor
public class UserApiController implements IUserApi {
    private final IUserService userService;

    @Override
    @SaIgnore
    public UserDto passwordAuth(CheckPasswdCommand command) {
        return userService.passwordAuth(command);
    }

    @Override
    @SaIgnore
    public UserDto findUserByUsername(String username) {
        return userService.findByUsername(username);
    }

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return Collections.emptyList();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return userService.getRoleList(loginId, loginType);
    }

    @Override
    public PageResult<UserDto> listUser(PageQuery query) {
        return userService.list(query);
    }

    @Override
    @SaCheckPermission(value = "user.add", orRole = "admin")
    public Result<Void> addUser(UserDto dto) {
        userService.addUser(dto);
        return Result.success();
    }

    @Override
    public Result<Void> updateUser(UpdateUserCommand command) {
        userService.updateUser(command);
        return Result.success();
    }

    @Override
    @SaCheckPermission(value = "user.delete", orRole = "admin")
    public Result<Void> deleteUser(Long uid) {
        userService.deleteUser(uid);
        return Result.success();
    }

    @Override
    @SaCheckPermission(value = "user.reset-password", orRole = "admin")
    public Result<Void> resetPassword(Long uid) {
        userService.resetPassword(uid);
        return Result.success();
    }

    @Override
    public Result<Void> updatePassword(ChangePasswordCommand command) {
        userService.updatePassword(command);
        return Result.success();
    }
}
