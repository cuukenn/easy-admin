package com.cuukenn.openstudysource.cloud.user.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuukenn.openstudysource.cloud.user.dao.mapper.UserMapper;
import com.cuukenn.openstudysource.cloud.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * 用户数据db操作
 *
 * @author changgg
 */
@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepository extends ServiceImpl<UserMapper, User> {
}