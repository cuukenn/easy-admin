package com.cuukenn.openstudysource.cloud.user.api.client;

import com.cuukenn.openstudysource.cloud.user.api.IUserApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户查询服务
 *
 * @author changgg
 */
@ConditionalOnMissingBean(IUserApi.class)
@FeignClient(name = "user-service", path = UserFeignClient.MAPPING)
public interface UserFeignClient extends IUserApi {
}
