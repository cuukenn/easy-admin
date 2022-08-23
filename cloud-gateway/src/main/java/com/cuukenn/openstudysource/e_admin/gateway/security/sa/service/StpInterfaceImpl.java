package com.cuukenn.openstudysource.e_admin.gateway.security.sa.service;

import cn.dev33.satoken.stp.StpInterface;
import com.cuukenn.openstudysource.e_admin.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author changgg
 * 自定义权限验证接口扩展
 */
@Component
@RequiredArgsConstructor
public class StpInterfaceImpl implements StpInterface {
    private final IUserService userService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限列表
        return userService.getPermissionList(loginId, loginType);
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的角色列表
        return userService.getRoleList(loginId, loginType);
    }
}

