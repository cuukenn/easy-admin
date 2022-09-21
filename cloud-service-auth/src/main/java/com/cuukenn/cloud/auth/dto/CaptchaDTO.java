package com.cuukenn.cloud.auth.dto;

import com.cuukenn.openstudysource.cloud.framework.dto.Dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * 验证码DTO
 *
 * @author changgg
 */
@RequiredArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CaptchaDTO extends Dto {
    private static final long serialVersionUID = -134531838825915131L;
    /**
     * 验证码ID
     */
    @JsonProperty("id")
    private final String id;
    /**
     * 验证码图片base64数据
     */
    @JsonProperty("img_base64")
    private final String imgBase64;
}
