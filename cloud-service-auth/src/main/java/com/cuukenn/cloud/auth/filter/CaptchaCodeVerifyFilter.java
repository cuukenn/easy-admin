package com.cuukenn.cloud.auth.filter;

import com.cuukenn.cloud.auth.service.ICaptchaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
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

/**
 * @author changgg
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CaptchaCodeVerifyFilter extends OncePerRequestFilter {
    private final String login = "/login";
    private final ICaptchaService captchaService;
    private final AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler(login + "?error");

    @SuppressWarnings("NullableProblems")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean flag = false;
        try {
            if (request.getMethod().equals(HttpMethod.POST.name()) && login.equals(request.getRequestURI())) {
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

    private void validateCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String id = request.getParameter("captchaId");
        final String code = request.getParameter("captchaCode");
        captchaService.validate(id, code);
    }

    static class ValidateCodeException extends AuthenticationException {

        private static final long serialVersionUID = -6231022868728842911L;

        public ValidateCodeException(String msg) {
            super(msg);
        }
    }
}
