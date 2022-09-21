package com.cuukenn.cloud.auth.service.impl;

import com.cuukenn.openstudysource.cloud.user.api.client.UserFeignClient;
import com.cuukenn.openstudysource.cloud.user.dto.AuthUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author changgg
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DefaultUserDetailServiceImpl implements UserDetailsService {
    private final UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUserDto userDto = userFeignClient.findUserByUsername(username);
        if (userDto == null) {
            throw new UsernameNotFoundException(username);
        }
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        if (Boolean.TRUE.equals(userDto.getIsAdmin())) {
            log.info("{} is admin account,add admin permission to authority list", username);
            authorities.add(new SimpleGrantedAuthority("admin"));
        }
        log.info("load user by username:{}", username);
        return new User(userDto.getUsername(), userDto.getPassword(),
            Boolean.TRUE.equals(userDto.getEnabled()),
            Boolean.FALSE.equals(userDto.getAccountExpired()),
            Boolean.FALSE.equals(userDto.getAccountExpired()),
            Boolean.FALSE.equals(userDto.getPasswordExpired()),
            authorities);
    }
}
