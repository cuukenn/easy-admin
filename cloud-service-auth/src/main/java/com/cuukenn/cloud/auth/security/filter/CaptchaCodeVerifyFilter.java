package com.cuukenn.cloud.auth.security.filter;

import cn.hutool.json.JSONUtil;
import com.cuukenn.cloud.auth.security.LoginAction;
import com.cuukenn.cloud.auth.security.SecurityConstant;
import com.cuukenn.cloud.auth.service.ICaptchaService;
import com.cuukenn.openstudysource.cloud.framework.dto.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author changgg
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CaptchaCodeVerifyFilter extends OncePerRequestFilter {
    private final ICaptchaService captchaService;
    private final AuthenticationFailureHandler failureHandler = this.getAuthenticationFailureHandler();

    @SuppressWarnings("NullableProblems")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean flag = false;
        try {
            if (request.getMethod().equals(HttpMethod.POST.name()) && SecurityConstant.LOGIN_URL.equals(request.getRequestURI())) {
                validateCode(request, response);
            }
        } catch (RuntimeException e) {
            flag = true;
            failureHandler.onAuthenticationFailure(request, response, new ValidateCodeException(e.getMessage()));
        }
        if (!flag) {
            filterChain.doFilter(request, response);
        }
    }

    private AuthenticationFailureHandler getAuthenticationFailureHandler() {
        final Map<LoginAction, AuthenticationFailureHandler> strategies = new HashMap<>(2);
        strategies.put(LoginAction.REDIRECT, new SimpleUrlAuthenticationFailureHandler(SecurityConstant.LOGIN_FAIL_URL));
        strategies.put(LoginAction.JUST_RETURN, (request, response, exception) -> {
            //直接输出jwt信息
            response.setStatus(HttpStatus.SC_OK);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            try (PrintWriter writer = response.getWriter()) {
                writer.print(JSONUtil.toJsonStr(Result.failed(exception.getMessage())));
            }
        });
        return (request, response, exception) -> {
            LoginAction action = LoginAction.fromValue(request.getParameter(SecurityConstant.LOGIN_LOGIN_ACTION_PARAM));
            strategies.get(action).onAuthenticationFailure(request, response, exception);
        };
    }

    private void validateCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String id = request.getParameter(SecurityConstant.LOGIN_CAPTCHA_ID_PARAM);
        final String code = request.getParameter(SecurityConstant.LOGIN_CAPTCHA_CODE_PARAM);
        captchaService.validate(id, code);
    }

    static class ValidateCodeException extends AuthenticationException {

        private static final long serialVersionUID = -6231022868728842911L;

        public ValidateCodeException(String msg) {
            super(msg);
        }
    }
}
