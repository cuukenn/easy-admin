package com.cuukenn.openstudysource.cloud.user.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuukenn.openstudysource.cloud.user.dao.mapper.associate.UserRoleMapper;
import com.cuukenn.openstudysource.cloud.user.entity.associate.UserRole;
import org.springframework.stereotype.Repository;

/**
 * 角色数据db操作
 *
 * @author changgg
 */
@Repository
public class UserRoleRepository extends ServiceImpl<UserRoleMapper, UserRole> {
}
