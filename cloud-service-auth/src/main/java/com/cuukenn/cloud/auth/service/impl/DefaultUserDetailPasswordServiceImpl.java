package com.cuukenn.cloud.auth.service.impl;

import com.cuukenn.openstudysource.cloud.user.api.client.UserFeignClient;
import com.cuukenn.openstudysource.cloud.user.dto.UpdatePasswordCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.stereotype.Service;

/**
 * @author changgg
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DefaultUserDetailPasswordServiceImpl implements UserDetailsPasswordService {
    private final UserFeignClient userFeignClient;

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
