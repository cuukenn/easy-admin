package com.cuukenn.cloud.auth.dto;

import com.cuukenn.openstudysource.cloud.framework.dto.Dto;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 验证码DTO
 *
 * @author changgg
 */
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

    public CaptchaDTO(String id, String imgBase64) {
        this.id = id;
        this.imgBase64 = imgBase64;
    }

    public String getId() {
        return id;
    }

    public String getImgBase64() {
        return imgBase64;
    }
}
