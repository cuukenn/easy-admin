package com.cuukenn.openstudysource.cloud.user.entity.associate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cuukenn.openstudysource.cloud.framework.entity.AbstractMybatisEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author changgg
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
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
}
