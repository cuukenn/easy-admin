package com.cuukenn.openstudysource.cloud.user.service;

import com.cuukenn.openstudysource.cloud.framework.BaseService;
import com.cuukenn.openstudysource.cloud.user.dto.AuthUserDto;
import com.cuukenn.openstudysource.cloud.user.dto.ChangePasswordCommand;
import com.cuukenn.openstudysource.cloud.user.dto.CheckPasswdCommand;
import com.cuukenn.openstudysource.cloud.user.dto.UpdatePasswordCommand;
import com.cuukenn.openstudysource.cloud.user.dto.UpdateUserCommand;
import com.cuukenn.openstudysource.cloud.user.dto.UserDto;

import java.util.List;

/**
 * @author changgg
 */
public interface IUserService extends BaseService<UserDto, UserDto, UpdateUserCommand> {
    /**
     * 重置密码
     *
     * @param uid 用户ID
     */
    void resetPassword(Long uid);

    /**
     * 修改密码
     *
     * @param command 修改动作
     */
    void changePassword(ChangePasswordCommand command);

    /**
     * 更新密码(密码加密方式更新后同步更新，密码保持一致)
     *
     * @param command 修改动作
     */
    void updatePassword(UpdatePasswordCommand command);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 数据
     */
    AuthUserDto findByUsername(String username);

    /**
     * 获取角色列表
     *
     * @param loginId   登录ID
     * @param loginType 登录类型
     * @return 权限列表
     */
    List<String> getRoleList(Object loginId, String loginType);

    /**
     * 检查账户密码是否正确
     *
     * @param command@return 用户数据
     */
    UserDto passwordAuth(CheckPasswdCommand command);
}
