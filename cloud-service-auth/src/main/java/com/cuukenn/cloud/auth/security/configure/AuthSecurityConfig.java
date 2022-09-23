package com.cuukenn.cloud.auth.security.configure;

import cn.hutool.json.JSONUtil;
import com.cuukenn.cloud.auth.dto.TokenDto;
import com.cuukenn.cloud.auth.security.LoginAction;
import com.cuukenn.cloud.auth.security.filter.CaptchaCodeVerifyFilter;
import com.cuukenn.openstudysource.cloud.framework.auth.SecurityConstant;
import com.cuukenn.openstudysource.cloud.framework.auth.config.SecurityConfiguration;
import com.cuukenn.openstudysource.cloud.framework.auth.config.TokenProperties;
import com.cuukenn.openstudysource.cloud.framework.auth.config.WebMvcConfig;
import com.cuukenn.openstudysource.cloud.framework.dto.Result;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author changgg
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import({SecurityConfiguration.class, WebMvcConfig.class})
public class AuthSecurityConfig extends WebSecurityConfigurerAdapter {
    private final CaptchaCodeVerifyFilter captchaCodeVerifyFilter;
    private final TokenProperties tokenProperties;

    public AuthSecurityConfig(CaptchaCodeVerifyFilter captchaCodeVerifyFilter, TokenProperties tokenProperties) {
        this.captchaCodeVerifyFilter = captchaCodeVerifyFilter;
        this.tokenProperties = tokenProperties;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/static/**")
            .antMatchers("/websocket/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session
                .maximumSessions(tokenProperties.getMaximumSessions())
            ).rememberMe(rm -> rm
                .rememberMeParameter(SecurityConstant.LOGIN_REMEMBER_ME_PARAM)
                .tokenValiditySeconds((int) TimeUnit.HOURS.toSeconds(tokenProperties.getRememberMeInvalidTimeOfHours()))
            ).formLogin(form ->
                form.loginPage(SecurityConstant.LOGIN_URL)
                    .loginProcessingUrl(SecurityConstant.LOGIN_URL)
                    .failureUrl(SecurityConstant.LOGIN_FAIL_URL)
                    .successHandler(loginSuccessHandler())
                    .usernameParameter(SecurityConstant.LOGIN_USERNAME_PARAM)
                    .passwordParameter(SecurityConstant.LOGIN_PASSWORD_PARAM)
                    .permitAll()
            ).authorizeRequests(auth -> auth
                .antMatchers("/captcha/**").permitAll()
                .antMatchers("/v3/api-docs/**").permitAll()
                .anyRequest().authenticated())
            .addFilterBefore(captchaCodeVerifyFilter, UsernamePasswordAuthenticationFilter.class);
    }

    private AuthenticationSuccessHandler loginSuccessHandler() {
        final Map<LoginAction, AuthenticationSuccessHandler> strategies = new HashMap<>(2);
        strategies.put(LoginAction.REDIRECT, (request, response, authentication) -> {
            //登录成功重定向到指定位置
            String redirect = request.getParameter(SecurityConstant.LOGIN_REDIRECT_URL_PARAM);
            if (redirect == null || redirect.isBlank()) {
                response.sendRedirect("/");
            } else {
                response.sendRedirect(redirect + "");
            }
        });
        strategies.put(LoginAction.JUST_RETURN, (request, response, authentication) -> {
            //直接输出jwt信息
            response.setStatus(HttpStatus.SC_OK);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            try (PrintWriter writer = response.getWriter()) {
                writer.print(JSONUtil.toJsonStr(Result.success(new TokenDto(
                    tokenProperties.getTokenHeader(),
                    tokenProperties.getTokenPrefix(),
                    request.getSession().getId()))));
            }
        });
        return (request, response, authentication) -> {
            LoginAction action = LoginAction.fromValue(request.getParameter(SecurityConstant.LOGIN_LOGIN_ACTION_PARAM));
            strategies.get(action).onAuthenticationSuccess(request, response, authentication);
        };
    }
}
