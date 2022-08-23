package com.cuukenn.openstudysource.e_admin.user.api;

import com.cuukenn.openstudysource.e_admin.user.service.IUserService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户查询服务
 *
 * @author changgg
 */
@FeignClient(name = "user-service")
@RequestMapping(IUserFeignClient.MAPPING)
public interface IUserFeignClient extends IUserService {
}
