package com.cuukenn.cloud.auth.service.impl;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import cn.hutool.core.util.IdUtil;
import com.cuukenn.cloud.auth.config.CaptchaProperties;
import com.cuukenn.cloud.auth.service.ICaptchaService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

/**
 * 内存验证码存储器
 *
 * @author changgg
 */
@Service
@ConditionalOnMissingBean(ICaptchaService.class)
@EnableConfigurationProperties(CaptchaProperties.class)
public class InMemoryCaptchaServiceImpl implements ICaptchaService {
    private final Cache<String, String> cache;

    public InMemoryCaptchaServiceImpl(CaptchaProperties properties) {
        cache = CacheUtil.newFIFOCache(1000, properties.getTimeout().get(ChronoUnit.MILLIS));
    }

    @Override
    public String removeIfAbstract(String id) {
        String data = cache.get(id);
        if (data != null) {
            cache.remove(id);
        }
        return data;
    }

    @Override
    public String save(String code) {
        String id = IdUtil.fastSimpleUUID();
        cache.put(id, code);
        return id;
    }
}
