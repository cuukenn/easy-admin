package com.cuukenn.openstudysource.cloud.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.cuukenn.openstudysource.cloud.framework.dto.PageQuery;
import com.cuukenn.openstudysource.cloud.framework.dto.PageResult;
import com.cuukenn.openstudysource.cloud.framework.exception.BizException;
import com.cuukenn.openstudysource.cloud.user.converter.RoleConverter;
import com.cuukenn.openstudysource.cloud.user.dao.RoleRepository;
import com.cuukenn.openstudysource.cloud.user.dto.RoleDto;
import com.cuukenn.openstudysource.cloud.user.dto.UpdateRoleCommand;
import com.cuukenn.openstudysource.cloud.user.entity.Role;
import com.cuukenn.openstudysource.cloud.user.service.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色服务
 *
 * @author changgg
 */
@Service
public class DefaultRoleServiceImpl implements IRoleService {
    private static final Logger log = LoggerFactory.getLogger(DefaultRoleServiceImpl.class);
    private final RoleRepository repository;
    private final RoleConverter converter;

    public DefaultRoleServiceImpl(RoleRepository repository, RoleConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public PageResult<RoleDto> list(PageQuery query) {
        PageDTO<Role> page = new PageDTO<>(query.getPageNum(), query.getPageSize());
        //排序
        if (Boolean.TRUE.equals(query.getOrder().getDesc())) {
            page.setOrders(Collections.singletonList(OrderItem.desc(query.getOrder().getColumn())));
        } else {
            page.setOrders(Collections.singletonList(OrderItem.asc(query.getOrder().getColumn())));
        }
        PageDTO<Role> result = repository.page(page);
        List<RoleDto> data = result.getRecords().stream().map(converter::toDto).collect(Collectors.toList());
        return PageResult.build(result.getCurrent(), result.getSize(), result.getTotal(), data);
    }

    @Override
    public void add(RoleDto dto) {
        if (dto == null) {
            return;
        }
        Role entity = converter.fromDto(dto);
        repository.saveOrUpdate(entity);
        log.info("add role,role:{}", dto.getRole());
    }

    @Override
    public void update(UpdateRoleCommand command) {
        log.info("update role,id:{}", -1L);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            return;
        }
        int cnt = repository.getBaseMapper().deleteById(id);
        if (cnt <= 0) {
            log.error("delete failed,id:{},maybe not exist", id);
            throw new BizException(-400, "role id not found,delete failed");
        }
    }
}
