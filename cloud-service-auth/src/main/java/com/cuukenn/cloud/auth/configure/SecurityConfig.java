package com.cuukenn.cloud.auth.configure;

import com.cuukenn.cloud.auth.filter.CaptchaCodeVerifyFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author changgg
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
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
            .formLogin(form ->
                form.loginPage("/login")
                    .loginProcessingUrl("/login")
                    .successHandler(loginSuccessHandler())
                    .permitAll()
            ).authorizeRequests(auth -> auth
                .antMatchers("/captcha/**").permitAll()
                .anyRequest().authenticated())
            .addFilterBefore(captchaCodeVerifyFilter, UsernamePasswordAuthenticationFilter.class);
    }

    private AuthenticationSuccessHandler loginSuccessHandler() {
        return (request, response, authentication) -> {
            response.sendRedirect("/current-user");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
