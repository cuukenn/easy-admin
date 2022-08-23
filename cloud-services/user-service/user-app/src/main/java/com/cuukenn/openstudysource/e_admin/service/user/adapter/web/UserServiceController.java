package com.cuukenn.openstudysource.e_admin.service.user.adapter.web;

import com.cuukenn.openstudysource.e_admin.user.dto.UserDto;
import com.cuukenn.openstudysource.e_admin.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author changgg
 */
@SuppressWarnings("AlibabaServiceOrDaoClassShouldEndWithImpl")
@RestController
@RequestMapping(IUserService.MAPPING)
@Slf4j
public class UserServiceController implements IUserService {
    @Override
    public UserDto findUserByUsername(String username) {
        log.info("invoke");
        return null;
    }

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        log.info("invoke");
        return null;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        log.info("invoke");
        return null;
    }
}
