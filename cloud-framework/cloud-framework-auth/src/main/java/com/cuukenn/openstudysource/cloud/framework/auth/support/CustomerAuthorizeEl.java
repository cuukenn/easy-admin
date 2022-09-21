package com.cuukenn.openstudysource.cloud.framework.auth.support;

import com.cuukenn.openstudysource.cloud.framework.auth.util.SecurityUtil;
import org.springframework.stereotype.Component;

/**
 * 自定义鉴权SpEl表达式
 *
 * @author changgg
 */
@SuppressWarnings("unused")
@Component("ex")
public class CustomerAuthorizeEl {
    /**
     * 是否为当前用户
     *
     * @param username 用户名
     * @return 是否当前用户操作
     */
    public boolean isCurrentUser(String username) {
        return SecurityUtil.getCurrentUsername().equals(username);
    }
}
