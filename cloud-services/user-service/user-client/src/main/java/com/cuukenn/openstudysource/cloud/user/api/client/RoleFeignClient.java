package com.cuukenn.openstudysource.cloud.user.api.client;

import com.cuukenn.openstudysource.cloud.user.api.IRoleApi;
import com.cuukenn.openstudysource.cloud.user.api.IUserApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户角色查询服务
 *
 * @author changgg
 */
@ConditionalOnMissingBean(IUserApi.class)
@FeignClient(name = "user-service", path = RoleFeignClient.MAPPING)
public interface RoleFeignClient extends IRoleApi {
}
