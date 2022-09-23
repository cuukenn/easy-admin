package com.cuukenn.cloud.auth.service.impl;

import com.cuukenn.openstudysource.cloud.user.api.client.UserFeignClient;
import com.cuukenn.openstudysource.cloud.user.dto.AuthUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class DefaultUserDetailServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(DefaultUserDetailServiceImpl.class);
    private final UserFeignClient userFeignClient;

    public DefaultUserDetailServiceImpl(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUserDto userDto = userFeignClient.findUserByUsername(username);
        if (userDto == null) {
            throw new UsernameNotFoundException(username);
        }
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        if (Boolean.TRUE.equals(userDto.getAdmin())) {
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
