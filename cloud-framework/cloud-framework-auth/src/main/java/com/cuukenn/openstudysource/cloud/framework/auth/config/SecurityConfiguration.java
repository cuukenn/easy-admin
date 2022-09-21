package com.cuukenn.openstudysource.cloud.framework.auth.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author changgg
 */
public class SecurityConfiguration {
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
}
