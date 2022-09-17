package com.cuukenn.openstudysource.cloud.controller;

import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author changgg
 */
@RestController
public class AuthController {

    @RequestMapping("/login/passwd")
    public SaResult passwordLogin(String username, String password) {
        try (HttpResponse response = HttpUtil.createPost("server.auth.cloud.com:8081/oauth2/token")
            .form("grant_type", "password")
            .form("client_id", "1001")
            .form("client_secret", "aaaa-bbbb-cccc-dddd-eeee")
            .form("username", username)
            .form("password", password)
            .execute()) {
            SaResult result = JSONUtil.toBean(response.body(), SaResult.class, true);
            // code不等于200  代表请求失败
            if (result.getCode() != SaResult.CODE_SUCCESS) {
                return SaResult.error(result.getMsg());
            }
            // 根据openid获取其对应的userId
            @SuppressWarnings("unchecked") Map<String, Object> data = (Map<String, Object>) result.getData();
            long uid = getUserIdByOpenid((String) data.get("openid"));
            data.put("uid", uid);
            String accessToken = (String) data.get("access_token");
            // 返回相关参数
            StpUtil.login(uid, SaLoginConfig.setToken(accessToken));
            return SaResult.data(data);
        }
    }

    private long getUserIdByOpenid(String openid) {
        // 此方法仅做模拟，实际开发要根据具体业务逻辑来获取userId
        return Long.parseLong(openid);
    }
}
