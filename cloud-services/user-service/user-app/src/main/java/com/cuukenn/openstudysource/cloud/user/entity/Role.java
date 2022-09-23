package com.cuukenn.openstudysource.cloud.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cuukenn.openstudysource.cloud.framework.entity.AbstractMybatisEntity;

/**
 * 角色表
 *
 * @author changgg
 */
@TableName("sys_role")
public class Role extends AbstractMybatisEntity {
    private static final long serialVersionUID = -1962873788949663319L;
    /**
     * 用户角色
     */
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
