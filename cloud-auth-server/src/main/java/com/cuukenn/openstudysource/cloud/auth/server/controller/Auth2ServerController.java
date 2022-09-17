package com.cuukenn.openstudysource.cloud.auth.server.controller;

/**
 * @author changgg
 */

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.oauth2.config.SaOAuth2Config;
import cn.dev33.satoken.oauth2.logic.SaOAuth2Handle;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.cuukenn.openstudysource.cloud.user.api.client.IUserFeignClient;
import com.cuukenn.openstudysource.cloud.user.dto.CheckPasswdCommand;
import com.cuukenn.openstudysource.cloud.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sa-OAuth2 Server端 控制器
 *
 * @author cuukenn
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class Auth2ServerController {
    private final IUserFeignClient userFeignClient;

    /**
     * 处理所有OAuth相关请求
     *
     * @return object
     */
    @RequestMapping("/oauth2/*")
    public Object request() {
        log.info("------- 进入请求: {}", SaHolder.getRequest().getUrl());
        return SaOAuth2Handle.serverRequest();
    }

    /**
     * Sa-OAuth2 定制化配置
     *
     * @param cfg cfg
     */
    @Autowired
    public void setAuthConfig(SaOAuth2Config cfg) {
        cfg.setNotLoginView(() -> {
            // 配置：未登录时返回的View
            return "当前会话在SSO-Server端尚未登录，请先访问" + "<a href='/oauth2/doLogin?name=sa&pwd=123456' target='_blank'> doLogin登录 </a>" + "进行登录之后，刷新页面开始授权";
        }).setDoLoginHandle((name, pwd) -> {
            // 配置：登录处理函数
            UserDto userDto = userFeignClient.passwordAuth(new CheckPasswdCommand(name, pwd));
            if (userDto != null) {
                StpUtil.login(userDto.getId());
                return SaResult.ok();
            }
            return SaResult.error("账号名或密码错误");
        }).setConfirmView((clientId, scope) -> {
            // 配置：确认授权时返回的View
            return "<p>应用 " + clientId + " 请求授权：" + scope + "</p>" + "<p>请确认：<a href='/oauth2/doConfirm?client_id=" + clientId + "&scope=" + scope + "' target='_blank'> 确认授权 </a></p>" + "<p>确认之后刷新页面</p>";
        }).setIsPassword(true);
    }

    @ExceptionHandler
    public SaResult handlerException(Exception e) {
        log.error("failed auth", e);
        return SaResult.error(e.getMessage());
    }
}
