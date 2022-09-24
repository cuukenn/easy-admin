package com.cuukenn.openstudysource.cloud.user.api;

import com.cuukenn.openstudysource.cloud.framework.BaseApi;
import com.cuukenn.openstudysource.cloud.user.dto.RoleDto;
import com.cuukenn.openstudysource.cloud.user.dto.UpdateRoleCommand;

/**
 * 用户角色查询服务
 *
 * @author changgg
 */
public interface IRoleApi extends BaseApi<RoleDto, RoleDto, UpdateRoleCommand> {
    String MAPPING = "/role";
}
