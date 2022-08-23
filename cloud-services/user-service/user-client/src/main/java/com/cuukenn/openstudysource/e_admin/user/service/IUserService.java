package com.cuukenn.openstudysource.e_admin.user.service;

import com.cuukenn.openstudysource.e_admin.user.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 用户查询服务
 *
 * @author changgg
 */
public interface IUserService {
    String MAPPING = "/api/user";

    /**
     * 通过用户名查询指定用户
     *
     * @param username 用户名
     * @return userDto
     */
    @GetMapping(value = "/findUserByUsername")
    UserDto findUserByUsername(String username);

    /**
     * 获取用户权限列表
     *
     * @param loginId   id
     * @param loginType type
     * @return permission list
     */
    @GetMapping(value = "/getPermissionList")
    List<String> getPermissionList(Object loginId, String loginType);

    /**
     * 角色列表
     *
     * @param loginId   id
     * @param loginType type
     * @return role list
     */
    @GetMapping(value = "/getRoleList")
    List<String> getRoleList(Object loginId, String loginType);
}
