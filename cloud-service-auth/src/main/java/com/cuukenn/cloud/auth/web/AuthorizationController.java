package com.cuukenn.cloud.auth.web;

import com.cuukenn.openstudysource.cloud.framework.auth.SecurityConstant;
import com.cuukenn.openstudysource.cloud.framework.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.WebAttributes;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author changgg
 */
@RestController
public class AuthorizationController {
    private static final Logger log = LoggerFactory.getLogger(AuthorizationController.class);

    /**
     * 获取当前用户信息
     *
     * @param principal 认证信息
     * @return 用户数据
     */
    @GetMapping("/current-user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    /**
     * @return login视图
     */
    @RequestMapping(SecurityConstant.LOGIN_URL)
    public ModelAndView login(HttpServletRequest request, ModelAndView model) {
        Object exception = request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        ModelMap modelMap = model.getModelMap();
        if (exception instanceof Exception) {
            log.error("login failed.msg:{}", ((Exception) exception).getMessage());
            modelMap.addAttribute("error", ((Exception) exception).getMessage());
        } else {
            modelMap.addAttribute("error", "");
        }
        modelMap.addAttribute("username", SecurityConstant.LOGIN_USERNAME_PARAM);
        modelMap.addAttribute("password", SecurityConstant.LOGIN_PASSWORD_PARAM);
        modelMap.addAttribute("captcha_id", SecurityConstant.LOGIN_CAPTCHA_ID_PARAM);
        modelMap.addAttribute("captcha_code", SecurityConstant.LOGIN_CAPTCHA_CODE_PARAM);
        modelMap.addAttribute("login_action", SecurityConstant.LOGIN_LOGIN_ACTION_PARAM);
        modelMap.addAttribute("redirect_url", SecurityConstant.LOGIN_REDIRECT_URL_PARAM);
        model.setViewName("login");
        return model;
    }

    /**
     * 退出登录
     *
     * @param request 请求
     * @param token   令牌
     * @return 状态
     */
    @DeleteMapping("/logout")
    public Result<Void> logout(HttpServletRequest request, @RequestHeader("Authorization") String token) {
        return Result.success();
    }
}
