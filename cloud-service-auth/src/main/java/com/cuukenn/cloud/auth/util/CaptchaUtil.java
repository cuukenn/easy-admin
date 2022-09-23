package com.cuukenn.cloud.auth.util;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.ChineseGifCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 验证码工具类
 *
 * @author changgg
 */
public final class CaptchaUtil {
    private CaptchaUtil() {
    }

    /**
     * 随机获取验证码类型
     *
     * @return 验证码实例
     */
    @SuppressWarnings("DuplicateBranchesInSwitch")
    public static Captcha getCaptcha(int captchaWidth, int captchaHeight) {
        switch (ThreadLocalRandom.current().nextInt(14)) {
            case 0:
                return new GifCaptcha(captchaWidth, captchaHeight);
            case 1:
                return new SpecCaptcha(captchaWidth, captchaHeight);
            case 2:
                return new ChineseCaptcha(captchaWidth, captchaHeight);
            case 3:
                return new ChineseGifCaptcha(captchaWidth, captchaHeight);
            default:
                return new ArithmeticCaptcha(captchaWidth, captchaHeight);
        }
    }
}
