package io.github.cuukenn.easyadmin.module.system.service.log;

import io.github.cuukenn.easyadmin.module.system.service.log.dto.OperationLogDto;
import io.github.cuukenn.easyframework.core.vo.PageReqVo;
import io.github.cuukenn.easyframework.core.vo.PageWrapper;

/**
 * @author changgg
 */
public interface IOperationLogService {
	/**
	 * 获取数据
	 *
	 * @param vo vo
	 * @return 数据
	 */
	PageWrapper<OperationLogDto> page(PageReqVo vo);
}
