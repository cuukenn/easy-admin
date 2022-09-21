package com.cuukenn.openstudysource.cloud.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cuukenn.openstudysource.cloud.framework.entity.AbstractMybatisEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 角色表
 *
 * @author changgg
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@TableName("sys_role")
public class Role extends AbstractMybatisEntity {
    private static final long serialVersionUID = -1962873788949663319L;
    /**
     * 用户角色
     */
    private String role;
}
