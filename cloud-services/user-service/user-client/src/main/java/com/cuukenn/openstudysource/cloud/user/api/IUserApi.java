package com.cuukenn.openstudysource.cloud.user.api;

import com.cuukenn.openstudysource.cloud.framework.BaseApi;
import com.cuukenn.openstudysource.cloud.framework.dto.Result;
import com.cuukenn.openstudysource.cloud.user.dto.AuthUserDto;
import com.cuukenn.openstudysource.cloud.user.dto.ChangePasswordCommand;
import com.cuukenn.openstudysource.cloud.user.dto.CheckPasswdCommand;
import com.cuukenn.openstudysource.cloud.user.dto.UpdatePasswordCommand;
import com.cuukenn.openstudysource.cloud.user.dto.UpdateUserCommand;
import com.cuukenn.openstudysource.cloud.user.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 用户查询服务
 *
 * @author changgg
 */
public interface IUserApi extends BaseApi<UserDto, UserDto, UpdateUserCommand> {
    String MAPPING = "/user";

    /**
     * 通过用户名查询指定用户
     *
     * @param username 用户名
     * @return userDto
     */
    @GetMapping(value = "/username/{username}")
    AuthUserDto findUserByUsername(@PathVariable("username") String username);

    /**
     * 检查账户密码是否正确
     *
     * @param command@return 用户数据
     */
    @PostMapping(value = "/password-auth")
    UserDto passwordAuth(@Valid @NotNull @RequestBody CheckPasswdCommand command);

    /**
     * 重置用户密码
     *
     * @param uid 用户ID
     * @return 结果
     */
    @PutMapping(value = "/update/password/reset")
    Result<Void> resetPassword(@Valid @NotNull Long uid);

    /**
     * 修改用户密码
     *
     * @param command command
     * @return 结果
     */
    @PutMapping(value = "/update/password")
    Result<Void> changePassword(@Valid @RequestBody ChangePasswordCommand command);

    /**
     * 更新用户密码(密码原始数据不更新，仅更新加密方式)
     *
     * @param command command
     * @return 结果
     */
    @PutMapping(value = "/update/password/encryption-mode")
    Result<Void> updatePassword(@Valid @RequestBody UpdatePasswordCommand command);
}
