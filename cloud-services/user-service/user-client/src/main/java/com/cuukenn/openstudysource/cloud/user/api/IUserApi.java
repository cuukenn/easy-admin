package com.cuukenn.openstudysource.cloud.user.api;

import com.cuukenn.openstudysource.cloud.framework.dto.PageQuery;
import com.cuukenn.openstudysource.cloud.framework.dto.PageResult;
import com.cuukenn.openstudysource.cloud.framework.dto.Result;
import com.cuukenn.openstudysource.cloud.user.dto.AuthUserDto;
import com.cuukenn.openstudysource.cloud.user.dto.ChangePasswordCommand;
import com.cuukenn.openstudysource.cloud.user.dto.CheckPasswdCommand;
import com.cuukenn.openstudysource.cloud.user.dto.UpdatePasswordCommand;
import com.cuukenn.openstudysource.cloud.user.dto.UpdateUserCommand;
import com.cuukenn.openstudysource.cloud.user.dto.UserDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 用户查询服务
 *
 * @author changgg
 */
public interface IUserApi {
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
     * 分页查询用户
     *
     * @param query 分页数据
     * @return 结果
     */
    @GetMapping(value = "/list")
    PageResult<UserDto> listUser(@Valid PageQuery query);

    /**
     * 添加用户
     *
     * @param dto dto
     * @return 结果
     */
    @PostMapping(value = "/add")
    Result<Void> addUser(@Valid UserDto dto);

    /**
     * 更新用户数据
     *
     * @param command dto
     * @return 结果
     */
    @PutMapping(value = "/update")
    Result<Void> updateUser(@Valid UpdateUserCommand command);

    /**
     * 删除用户
     *
     * @param uid 用户ID
     * @return 结果
     */
    @DeleteMapping(value = "/delete")
    Result<Void> deleteUser(@Valid @NotNull @RequestParam("uid") Long uid);

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
