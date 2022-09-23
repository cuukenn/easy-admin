package com.cuukenn.openstudysource.cloud.framework.auth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.session.DefaultCookieSerializerCustomizer;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author changgg
 */
@EnableConfigurationProperties(TokenProperties.class)
public class SecurityConfiguration {
    private static final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);

    /**
     * 无感知多密码器共存并更新到默认算法
     *
     * @return 密码加密器
     */
    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 授权前缀
     *
     * @return 授权前缀实例
     */
    @Bean
    @ConditionalOnMissingBean(GrantedAuthorityDefaults.class)
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }

    @Bean
    public DefaultCookieSerializer cookieSerializer(ServerProperties serverProperties,
                                                    ObjectProvider<DefaultCookieSerializerCustomizer> cookieSerializerCustomizers) {
        Session.Cookie cookie = serverProperties.getServlet().getSession().getCookie();
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
        map.from(cookie::getName).to(cookieSerializer::setCookieName);
        map.from(cookie::getDomain).to(cookieSerializer::setDomainName);
        map.from(cookie::getPath).to(cookieSerializer::setCookiePath);
        map.from(cookie::getHttpOnly).to(cookieSerializer::setUseHttpOnlyCookie);
        map.from(cookie::getSecure).to(cookieSerializer::setUseSecureCookie);
        map.from(cookie::getMaxAge).to((maxAge) -> cookieSerializer.setCookieMaxAge((int) maxAge.getSeconds()));
        cookieSerializerCustomizers.orderedStream().forEach((customizer) -> customizer.customize(cookieSerializer));
        return cookieSerializer;
    }

    /**
     * session策略
     *
     * @return session策略
     */
    @Bean
    @Primary
    public HttpSessionIdResolver sessionIdResolver(TokenProperties properties, CookieSerializer cookieSerializer) {
        log.info("register header & cookie session resolver");
        final CookieHttpSessionIdResolver cookie = new CookieHttpSessionIdResolver();
        cookie.setCookieSerializer(cookieSerializer);
        final HttpSessionIdResolver header = new HeaderHttpSessionIdResolver(properties.getTokenHeader());
        return new HttpSessionIdResolver() {
            @Override
            public List<String> resolveSessionIds(HttpServletRequest request) {
                List<String> sessionIds = header.resolveSessionIds(request);
                if (sessionIds != null && !sessionIds.isEmpty()) {
                    return sessionIds;
                }
                return cookie.resolveSessionIds(request);
            }

            @Override
            public void setSessionId(HttpServletRequest request, HttpServletResponse response, String sessionId) {
                header.setSessionId(request, response, sessionId);
                cookie.setSessionId(request, response, sessionId);
            }

            @Override
            public void expireSession(HttpServletRequest request, HttpServletResponse response) {
                header.expireSession(request, response);
                cookie.expireSession(request, response);
            }
        };
    }
}
