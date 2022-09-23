package com.cuukenn.openstudysource.cloud.user.entity.associate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cuukenn.openstudysource.cloud.framework.entity.AbstractMybatisEntity;

/**
 * @author changgg
 */
@TableName("sys_user_role")
public class UserRole extends AbstractMybatisEntity {
    private static final long serialVersionUID = -1962873788949663319L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
