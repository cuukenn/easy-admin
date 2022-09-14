package com.cuukenn.openstudysource.cloud.user.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.cuukenn.openstudysource.cloud.common.dto.PageQuery;
import com.cuukenn.openstudysource.cloud.common.dto.PageResult;
import com.cuukenn.openstudysource.cloud.common.dto.ResultCode;
import com.cuukenn.openstudysource.cloud.common.exception.BizException;
import com.cuukenn.openstudysource.cloud.user.converter.UserConverter;
import com.cuukenn.openstudysource.cloud.user.dao.RoleRepository;
import com.cuukenn.openstudysource.cloud.user.dao.UserRepository;
import com.cuukenn.openstudysource.cloud.user.dao.UserRoleRepository;
import com.cuukenn.openstudysource.cloud.user.dto.ChangePasswordCommand;
import com.cuukenn.openstudysource.cloud.user.dto.CheckPasswdCommand;
import com.cuukenn.openstudysource.cloud.user.dto.UpdateUserCommand;
import com.cuukenn.openstudysource.cloud.user.dto.UserDto;
import com.cuukenn.openstudysource.cloud.user.entity.Role;
import com.cuukenn.openstudysource.cloud.user.entity.User;
import com.cuukenn.openstudysource.cloud.user.entity.associate.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户服务
 *
 * @author changgg
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DefaultUserServiceImpl implements IUserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserConverter userConverter;
    private final String DEFAULT_PASSWD = "1234@Abcd";

    @Override
    public PageResult<UserDto> list(PageQuery query) {
        PageDTO<User> page = new PageDTO<>(query.getPageNum(), query.getPageSize());
        //排序
        if (Boolean.TRUE.equals(query.getOrder().getDesc())) {
            page.setOrders(Collections.singletonList(OrderItem.desc(query.getOrder().getColumn())));
        } else {
            page.setOrders(Collections.singletonList(OrderItem.asc(query.getOrder().getColumn())));
        }
        PageDTO<User> result = userRepository.page(page);
        List<UserDto> data = result.getRecords().stream().map(userConverter::toUserDto).collect(Collectors.toList());
        return PageResult.build(result.getCurrent(), result.getSize(), result.getTotal(), data);
    }

    @Override
    public void addUser(UserDto dto) {
        if (dto == null) {
            return;
        }
        User user = userConverter.fromUserDto(dto);
        user.setPassword(passwordEncoder.encode(DEFAULT_PASSWD));
        //默认值设置
        user.setLocked(false);
        user.setEnabled(false);
        userRepository.saveOrUpdate(user);
        log.info("add user,username:{}", dto.getUsername());
    }

    @Override
    public void deleteUser(Long uid) {
        if (uid == null) {
            return;
        }
        int cnt = userRepository.getBaseMapper().deleteById(uid);
        if (cnt <= 0) {
            log.error("deleteUser failed,uid:{},maybe not exist", uid);
            throw new BizException(User.AccountStatus.NOT_FOUND);
        }
    }

    @Override
    public void updateUser(UpdateUserCommand command) {
        log.info("update user,uid:{}", StpUtil.getLoginIdAsLong());
    }

    @Override
    public void resetPassword(Long uid) {
        if (uid == null) {
            return;
        }
        final long currentUid = StpUtil.getLoginIdAsLong();
        boolean update = userRepository.update(null, Wrappers.lambdaUpdate(User.class)
            .set(User::getPassword, passwordEncoder.encode(DEFAULT_PASSWD))
            .set(User::getLastModifiedBy, currentUid)
            .set(User::getLastModifiedTime, new Date())
            .eq(User::getId, uid)
        );
        if (!update) {
            log.error("reset password failed");
            throw new BizException(new ResultCode.ResultCodeWrapper(-311, "reset password failed"));
        }
        log.info("reset password for {} by {}", uid, currentUid);
    }

    @Override
    public void updatePassword(ChangePasswordCommand command) {
        if (command == null) {
            return;
        }
        final long currentUid = StpUtil.getLoginIdAsLong();
        boolean update = userRepository.update(null, Wrappers.lambdaUpdate(User.class)
            .set(User::getPassword, passwordEncoder.encode(command.getNewPassword()))
            .set(User::getLastModifiedBy, currentUid)
            .set(User::getLastModifiedTime, new Date())
            .eq(User::getPassword, passwordEncoder.encode(command.getOldPassword()))
            .eq(User::getId, currentUid)
        );
        if (!update) {
            log.error("update password failed:old password incorrect");
            throw new BizException(new ResultCode.ResultCodeWrapper(-311, "old password error"));
        }
        log.info("update password for {}", currentUid);
    }

    @Override
    public UserDto findByUsername(String username) {
        if (StrUtil.isBlank(username)) {
            log.warn("find-by-username with blank username");
            return null;
        }
        return userConverter.toUserDto(userRepository.getOne(Wrappers.lambdaQuery(User.class).eq(User::getUsername, username)));
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<UserRole> roleList = userRoleRepository.list(Wrappers.lambdaQuery(UserRole.class)
            .select(UserRole::getRoleId)
            .eq(UserRole::getUserId, loginId));
        return roleList.stream().map(item -> roleRepository.getOne(Wrappers.lambdaQuery(Role.class)
            .select(Role::getRole)
            .eq(Role::getId, item.getRoleId()))
        ).map(Role::getRole).collect(Collectors.toList());
    }

    @Override
    public UserDto passwordAuth(CheckPasswdCommand command) {
        User user = userRepository.getOne(Wrappers.lambdaQuery(User.class)
            .eq(User::getUsername, command.getUsername()));
        if (user == null) {
            log.error("get account error for {},user not found", command);
            throw new BizException(User.AccountStatus.U_OR_P_ERROR);
        }
        user.checkAndThrow();
        user.checkPassword(passwordEncoder, command.getPassword());
        return userConverter.toUserDto(user);
    }
}
