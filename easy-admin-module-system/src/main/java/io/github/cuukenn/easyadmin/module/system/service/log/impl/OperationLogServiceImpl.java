package io.github.cuukenn.easyadmin.module.system.service.log.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cuukenn.easyadmin.module.system.converter.log.OperationLogConverter;
import io.github.cuukenn.easyadmin.module.system.dao.OperationLogPo;
import io.github.cuukenn.easyadmin.module.system.dao.repository.OperationRepository;
import io.github.cuukenn.easyadmin.module.system.service.log.IOperationLogService;
import io.github.cuukenn.easyadmin.module.system.service.log.dto.OperationLogDto;
import io.github.cuukenn.easyframework.biz.operationlog.enhancer.IOperationLogPersistService;
import io.github.cuukenn.easyframework.core.vo.PageReqVo;
import io.github.cuukenn.easyframework.core.vo.PageWrapper;
import io.github.cuukenn.easyframework.web.toolkit.IpUtil;
import io.github.cuukenn.easyframework.web.toolkit.WebUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author changgg
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OperationLogServiceImpl implements IOperationLogPersistService, IOperationLogService {
	private final OperationRepository repository;
	private final ObjectMapper objectMapper;

	@Override
	@Async
	@SneakyThrows
	public void dealOperationLog(String uri, HttpMethod method, Operation operation, long spendTimeInMills, Object[] params, Object result, Throwable throwable) {
		OperationLogPo po = new OperationLogPo();
		//1、填充入参数据
		po.setUri(uri);
		po.setUriDescription(operation.summary());
		po.setMethod(method.name());
		po.setDuration(spendTimeInMills);
		po.setParams(Arrays.toString(params));
		po.setResult(objectMapper.writeValueAsString(result));
		//2、获取其他数据
		HttpServletRequest request = WebUtil.getRequest();
		String userIp = IpUtil.getAddress(request);
		String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
		po.setUserIp(userIp);
		po.setUserAgent(userAgent);
		//3、插入数据
		po.setDeleted(false);
		repository.save(po);
	}

	@Override
	public PageWrapper<OperationLogDto> page(PageReqVo vo) {
		Pageable page = PageRequest.of(
			vo.getPageNum() - 1, vo.getPageSize(),
			vo.getOrderDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, vo.getOrderColumn()
		);
		Page<OperationLogPo> pages = repository.findAll(page);
		return new PageWrapper<>(pages.getNumber(), pages.getSize(), pages.getTotalElements(), OperationLogConverter.INSTANCE.toOperationLogDto(pages.getContent()));
	}
}
