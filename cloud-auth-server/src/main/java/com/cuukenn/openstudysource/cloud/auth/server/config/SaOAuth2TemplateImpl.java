package com.cuukenn.openstudysource.cloud.auth.server.config;

import cn.dev33.satoken.oauth2.logic.SaOAuth2Template;
import cn.dev33.satoken.oauth2.model.SaClientModel;
import org.springframework.stereotype.Component;

/**
 * @author changgg
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
@Component
public class SaOAuth2TemplateImpl extends SaOAuth2Template {
    /**
     * 根据 id 获取 Client 信息
     *
     * @param clientId 客户端ID
     * @return model
     */
    @Override
    public SaClientModel getClientModel(String clientId) {
        // 此为模拟数据，真实环境需要从数据库查询
        if ("1001".equals(clientId)) {
            return new SaClientModel()
                .setClientId("10001")
                .setClientSecret("aaaa-bbbb-cccc-dddd-eeee")
                .setAllowUrl("*")
                .setContractScope("userinfo")
                .setIsAutoMode(true);
        }
        return null;
    }

    /**
     * 根据ClientId 和 LoginId 获取openid
     *
     * @param clientId 客户端ID
     * @param loginId  登录ID
     * @return openId
     */
    @Override
    public String getOpenid(String clientId, Object loginId) {
        // 此为模拟数据，真实环境需要从数据库查询
        return loginId + "";
    }
}
