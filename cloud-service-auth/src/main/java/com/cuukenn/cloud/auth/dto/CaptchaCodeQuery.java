package com.cuukenn.cloud.auth.dto;

import com.cuukenn.openstudysource.cloud.framework.dto.Query;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 验证码请求
 *
 * @author changgg
 */
public class CaptchaCodeQuery extends Query {
    private static final long serialVersionUID = -3783166218296538723L;
    @Max(192)
    @Min(130)
    private Integer captchaWidth;
    @Max(108)
    @Min(48)
    private Integer captchaHeight;

    public CaptchaCodeQuery(Integer captchaWidth, Integer captchaHeight) {
        this.captchaWidth = captchaWidth;
        this.captchaHeight = captchaHeight;
    }

    public Integer getCaptchaWidth() {
        return captchaWidth;
    }

    public void setCaptchaWidth(Integer captchaWidth) {
        this.captchaWidth = captchaWidth;
    }

    public Integer getCaptchaHeight() {
        return captchaHeight;
    }

    public void setCaptchaHeight(Integer captchaHeight) {
        this.captchaHeight = captchaHeight;
    }
}
