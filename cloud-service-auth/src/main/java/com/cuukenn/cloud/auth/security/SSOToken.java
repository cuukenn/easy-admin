package com.cuukenn.cloud.auth.security;

import cn.hutool.core.util.StrUtil;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.Payload;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author changgg
 */
@SuppressWarnings({"AlibabaClassNamingShouldBeCamel"})
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SSOToken extends AbstractToken {
    /**
     * 主键
     */
    private String id;
    /**
     * 租户 ID
     */
    private String tenantId;
    /**
     * 发布者
     */
    private String issuer;
    /**
     * IP 地址
     */
    private String ip;
    /**
     * 创建日期
     */
    private long time = System.currentTimeMillis();
    /**
     * 请求头信息
     */
    private String userAgent;
    /**
     * 编码器
     */
    private JWSSigner jwsSigner;

    @Override
    public String getToken() {
        String token;
        if ((token = super.getToken()) != null) {
            return token;
        }
        //创建JWS头，设置签名算法和类型
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256)
            .type(JOSEObjectType.JWT)
            .keyID(id)
            .customParam(SecurityConstant.TOKEN_USER_ISS, issuer)
            .build();
        //将负载信息封装到Payload中
        Map<String, Object> jsonObject = new HashMap<>(10);
        if (null != this.getTenantId()) {
            jsonObject.put(SecurityConstant.TOKEN_TENANT_ID, this.getTenantId());
        }
        if (null != this.getIp()) {
            jsonObject.put(SecurityConstant.TOKEN_USER_IP, this.getIp());
        }
        if (null != this.getUserAgent()) {
            jsonObject.put(SecurityConstant.TOKEN_USER_AGENT, this.getUserAgent());
        }
        final JWSObject jwsObject = new JWSObject(jwsHeader, new Payload(jsonObject));
        try {
            jwsObject.sign(jwsSigner);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        return jwsObject.serialize();
    }

    /**
     * 解析jwt
     *
     * @param tokenStr 令牌字符串
     * @return token
     */
    public static SSOToken parser(String tokenStr) {
        try {
            JWSObject jwsObject = JWSObject.parse(tokenStr);
            SSOToken token = new SSOToken();
            JWSHeader header = jwsObject.getHeader();
            token.setId(header.getKeyID());
            token.setIssuer((String) header.getCustomParam(SecurityConstant.TOKEN_USER_ISS));

            Payload payload = jwsObject.getPayload();
            Map<String, Object> jsonObject = payload.toJSONObject();
            String ip = (String) jsonObject.get(SecurityConstant.TOKEN_USER_IP);
            if (StrUtil.isNotEmpty(ip)) {
                token.setIp(ip);
            }
            String userAgent = (String) jsonObject.get(SecurityConstant.TOKEN_USER_AGENT);
            if (StrUtil.isNotEmpty(userAgent)) {
                token.setUserAgent(userAgent);
            }
            String tenantId = (String) jsonObject.get(SecurityConstant.TOKEN_TENANT_ID);
            if (StrUtil.isNotEmpty(tenantId)) {
                token.setTenantId(tenantId);
            }
            return token;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
