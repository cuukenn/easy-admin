package com.cuukenn.openstudysource.cloud.framework;

import com.cuukenn.openstudysource.cloud.framework.dto.Command;
import com.cuukenn.openstudysource.cloud.framework.dto.Dto;
import com.cuukenn.openstudysource.cloud.framework.dto.PageQuery;
import com.cuukenn.openstudysource.cloud.framework.dto.PageResult;

/**
 * @author changgg
 */
public interface BaseService<LDto extends Dto, ADto extends Dto, UCommand extends Command> {
    /**
     * 分页查询数据
     *
     * @param query 分页数据
     * @return 分页
     */
    PageResult<LDto> list(PageQuery query);

    /**
     * 添加
     *
     * @param dto dto
     */
    void add(ADto dto);

    /**
     * 更新
     *
     * @param command command
     */
    void update(UCommand command);

    /**
     * 删除
     *
     * @param id id
     */
    void delete(Long id);
}
