package io.github.cuukenn.easyadmin.module.system.controller.admin.log.vo;

import io.github.cuukenn.easyframework.core.vo.PageReqVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author changgg
 */
@Schema(title = "日志级别分页过滤请求Vo")
@Data
public class LogLevelPageFilterReqVo extends PageReqVo {
	@Schema(title = "过滤(日志名称)")
	private String filter;
}
