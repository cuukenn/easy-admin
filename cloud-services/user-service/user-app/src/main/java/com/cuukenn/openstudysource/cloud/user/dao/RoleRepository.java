package com.cuukenn.openstudysource.cloud.user.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuukenn.openstudysource.cloud.user.dao.mapper.RoleMapper;
import com.cuukenn.openstudysource.cloud.user.entity.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * 角色数据db操作
 *
 * @author changgg
 */
@Repository
@RequiredArgsConstructor
@Slf4j
public class RoleRepository extends ServiceImpl<RoleMapper, Role> {
}
