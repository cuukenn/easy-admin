package com.cuukenn.openstudysource.cloud.user.web;

import com.cuukenn.openstudysource.cloud.framework.dto.PageQuery;
import com.cuukenn.openstudysource.cloud.framework.dto.PageResult;
import com.cuukenn.openstudysource.cloud.framework.dto.Result;
import com.cuukenn.openstudysource.cloud.user.api.IRoleApi;
import com.cuukenn.openstudysource.cloud.user.dto.RoleDto;
import com.cuukenn.openstudysource.cloud.user.dto.UpdateRoleCommand;
import com.cuukenn.openstudysource.cloud.user.service.IRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changgg
 */
@SuppressWarnings("AlibabaServiceOrDaoClassShouldEndWithImpl")
@RestController
@RequestMapping(IRoleApi.MAPPING)
public class RoleController implements IRoleApi {
    private final IRoleService service;

    public RoleController(IRoleService service) {
        this.service = service;
    }

    @Override
    public PageResult<RoleDto> list(PageQuery query) {
        return service.list(query);
    }

    @Override
    public Result<Void> add(RoleDto dto) {
        service.add(dto);
        return Result.success();
    }

    @Override
    public Result<Void> update(UpdateRoleCommand command) {
        service.update(command);
        return Result.success();
    }

    @Override
    public Result<Void> delete(Long id) {
        service.delete(id);
        return Result.success();
    }
}
