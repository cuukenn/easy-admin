package com.cuukenn.openstudysource.cloud.user.api;

import com.cuukenn.openstudysource.cloud.common.dto.PageQuery;
import com.cuukenn.openstudysource.cloud.common.dto.PageResult;
import com.cuukenn.openstudysource.cloud.common.dto.Result;
import com.cuukenn.openstudysource.cloud.user.dto.ChangePasswordCommand;
import com.cuukenn.openstudysource.cloud.user.dto.CheckPasswdCommand;
import com.cuukenn.openstudysource.cloud.user.dto.UpdateUserCommand;
import com.cuukenn.openstudysource.cloud.user.dto.UserDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户查询服务
 *
 * @author changgg
 */
public interface IUserApi {
    String MAPPING = "/user";

    /**
     * 检查账户密码是否正确
     *
     * @param command@return 用户数据
     */
    @PostMapping(value = "/get/password-auth")
    UserDto passwordAuth(@Valid @NotNull @RequestBody CheckPasswdCommand command);

    /**
     * 通过用户名查询指定用户
     *
     * @param username 用户名
     * @return userDto
     */
    @GetMapping(value = "/get/username")
    UserDto findUserByUsername(@RequestParam("username") String username);

    /**
     * 获取用户权限列表
     *
     * @param loginId   id
     * @param loginType type
     * @return permission list
     */
    @GetMapping(value = "/get/permission-list")
    List<String> getPermissionList(@RequestParam("loginId") Object loginId, @RequestParam("loginType") String loginType);

    /**
     * 角色列表
     *
     * @param loginId   id
     * @param loginType type
     * @return role list
     */
    @GetMapping(value = "/get/role-list")
    List<String> getRoleList(@RequestParam("loginId") Object loginId, @RequestParam("loginType") String loginType);

    /**
     * 分页查询用户
     *
     * @param query 分页数据
     * @return 结果
     */
    @GetMapping(value = "/get/list")
    PageResult<UserDto> listUser(@Valid PageQuery query);

    /**
     * 添加用户
     *
     * @param dto dto
     * @return 结果
     */
    @PostMapping(value = "/add")
    Result<Void> addUser(@Valid @NotNull UserDto dto);

    /**
     * 更新用户数据
     *
     * @param command dto
     * @return 结果
     */
    @PutMapping(value = "/update")
    Result<Void> updateUser(@Valid @NotNull UpdateUserCommand command);

    /**
     * 添加用户
     *
     * @param uid 用户ID
     * @return 结果
     */
    @DeleteMapping(value = "/delete")
    Result<Void> deleteUser(@Valid @RequestParam("uid") @NotNull Long uid);

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
    Result<Void> updatePassword(@Valid @RequestBody @NotNull ChangePasswordCommand command);
}
