package com.cuukenn.cloud.auth.service.impl;

import cn.hutool.core.util.IdUtil;
import com.cuukenn.cloud.auth.config.CaptchaProperties;
import com.cuukenn.cloud.auth.service.ICaptchaService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author changgg
 */
@Service
@ConditionalOnProperty(name = "app.captcha.mode", havingValue = "redis")
@EnableConfigurationProperties(CaptchaProperties.class)
public class InRedisCaptchaServiceImpl implements ICaptchaService {
    private final StringRedisTemplate redisTemplate;
    private final CaptchaProperties properties;

    public InRedisCaptchaServiceImpl(StringRedisTemplate redisTemplate, CaptchaProperties properties) {
        this.redisTemplate = redisTemplate;
        this.properties = properties;
    }

    @Override
    public String removeIfAbstract(String id) {
        return redisTemplate.opsForValue().getAndDelete(getKey(id));
    }

    @Override
    public String save(String code) {
        String id = IdUtil.fastSimpleUUID();
        redisTemplate.opsForValue().set(getKey(id), code, properties.getTimeout());
        return id;
    }

    /**
     * 组装key
     *
     * @param id id
     * @return key
     */
    private String getKey(String id) {
        return properties.getKeyPrefix() + ":" + id;
    }
}
