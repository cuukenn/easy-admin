package com.cuukenn.cloud.auth.security.configure;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.cuukenn.cloud.auth.dto.TokenDto;
import com.cuukenn.cloud.auth.security.LoginAction;
import com.cuukenn.cloud.auth.security.SSOToken;
import com.cuukenn.cloud.auth.security.SecurityConstant;
import com.cuukenn.cloud.auth.security.filter.CaptchaCodeVerifyFilter;
import com.cuukenn.cloud.auth.security.filter.TokenFilter;
import com.cuukenn.openstudysource.cloud.framework.dto.Result;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author changgg
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenFilter tokenFilter;
    private final CaptchaCodeVerifyFilter captchaCodeVerifyFilter;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/*.html")
            .antMatchers("/**/*.html")
            .antMatchers("/**/*.js")
            .antMatchers("/**/*.css")
            .antMatchers("/websocket/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .rememberMe(rm -> rm.rememberMeParameter(SecurityConstant.LOGIN_REMEMBER_ME_PARAM))
            .formLogin(form ->
                form.loginPage(SecurityConstant.LOGIN_URL)
                    .loginProcessingUrl(SecurityConstant.LOGIN_URL)
                    .failureUrl(SecurityConstant.LOGIN_FAIL_URL)
                    .successHandler(loginSuccessHandler())
                    .usernameParameter(SecurityConstant.LOGIN_USERNAME_PARAM)
                    .passwordParameter(SecurityConstant.LOGIN_PASSWORD_PARAM)
                    .permitAll()
            ).authorizeRequests(auth -> auth
                .antMatchers("/captcha/**").permitAll()
                .anyRequest().authenticated())
            .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(captchaCodeVerifyFilter, UsernamePasswordAuthenticationFilter.class);
    }

    private AuthenticationSuccessHandler loginSuccessHandler() {
        JWSSigner signer;
        try {
            signer = new MACSigner(RandomUtil.randomString(256 / 8));
        } catch (KeyLengthException e) {
            throw new RuntimeException(e);
        }
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
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            SSOToken token = new SSOToken();
            token.setJwsSigner(signer);
            token.setIssuer(user.getUsername());
            try (PrintWriter writer = response.getWriter()) {
                writer.print(JSONUtil.toJsonStr(Result.success(new TokenDto(
                    SecurityConstant.TOKEN_HEADER_NAME,
                    SecurityConstant.TOKEN_PREFIX,
                    token.getToken()))));
            }
        });
        return (request, response, authentication) -> {
            LoginAction action = LoginAction.fromValue(request.getParameter(SecurityConstant.LOGIN_LOGIN_ACTION_PARAM));
            strategies.get(action).onAuthenticationSuccess(request, response, authentication);
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
