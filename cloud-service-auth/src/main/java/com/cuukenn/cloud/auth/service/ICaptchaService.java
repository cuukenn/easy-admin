package com.cuukenn.cloud.auth.service;

import cn.hutool.core.util.StrUtil;
import com.cuukenn.openstudysource.cloud.framework.exception.BizException;

/**
 * 验证码字符串存取服务
 *
 * @author changgg
 */
public interface ICaptchaService {
    /**
     * 校验验证码
     *
     * @param id   验证码ID
     * @param code 输入的验证码
     */
    default void validate(String id, String code) {
        final String exceptedCode = removeIfAbstract(id);
        if (StrUtil.isBlank(code)) {
            throw new BizException(-500, "请输入验证码");
        }
        if (exceptedCode == null) {
            throw new BizException(-501, "验证码已过期");
        }
        if (!StrUtil.equalsIgnoreCase(code, exceptedCode)) {
            throw new BizException(-502, "验证码不正确");
        }
    }

    /**
     * 使用指定ID获取对应的验证码结果
     * 存在结果将数据清空后进行返回
     *
     * @param id 验证码ID
     * @return 验证码内容{可以为null}
     */
    String removeIfAbstract(String id);

    /**
     * 将验证码进行保存
     *
     * @param code 验证码
     * @return 验证码ID
     */
    String save(String code);
}
