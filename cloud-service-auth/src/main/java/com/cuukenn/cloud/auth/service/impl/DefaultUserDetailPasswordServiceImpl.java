package com.cuukenn.cloud.auth.service.impl;

import com.cuukenn.openstudysource.cloud.user.api.client.UserFeignClient;
import com.cuukenn.openstudysource.cloud.user.dto.UpdatePasswordCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.stereotype.Service;

/**
 * @author changgg
 */
@Service
public class DefaultUserDetailPasswordServiceImpl implements UserDetailsPasswordService {
    private static final Logger log = LoggerFactory.getLogger(DefaultUserDetailPasswordServiceImpl.class);
    private final UserFeignClient userFeignClient;

    public DefaultUserDetailPasswordServiceImpl(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        log.info("update user password for {}", user.getUsername());
        userFeignClient.updatePassword(new UpdatePasswordCommand(user.getUsername(), newPassword));
        return new User(user.getUsername(),
            newPassword,
            user.isEnabled(),
            user.isAccountNonExpired(),
            user.isCredentialsNonExpired(),
            user.isAccountNonLocked(),
            user.getAuthorities());
    }
}
