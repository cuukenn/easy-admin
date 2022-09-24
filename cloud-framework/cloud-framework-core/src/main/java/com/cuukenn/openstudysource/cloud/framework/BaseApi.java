package com.cuukenn.openstudysource.cloud.framework;

import com.cuukenn.openstudysource.cloud.framework.dto.Command;
import com.cuukenn.openstudysource.cloud.framework.dto.Dto;
import com.cuukenn.openstudysource.cloud.framework.dto.PageQuery;
import com.cuukenn.openstudysource.cloud.framework.dto.PageResult;
import com.cuukenn.openstudysource.cloud.framework.dto.Result;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author changgg
 */
public interface BaseApi<LDto extends Dto, ADto extends Dto, UCommand extends Command> {
    /**
     * 分页查询
     *
     * @param query 分页数据
     * @return 结果
     */
    @GetMapping(value = "/list")
    PageResult<LDto> list(@Valid PageQuery query);

    /**
     * 添加
     *
     * @param dto dto
     * @return 结果
     */
    @PostMapping(value = "/add")
    Result<Void> add(@Valid ADto dto);

    /**
     * 更新
     *
     * @param command dto
     * @return 结果
     */
    @PutMapping(value = "/update")
    Result<Void> update(@Valid UCommand command);

    /**
     * 删除
     *
     * @param id id
     * @return 结果
     */
    @DeleteMapping(value = "/delete")
    Result<Void> delete(@Valid @NotNull @RequestParam("id") Long id);
}
