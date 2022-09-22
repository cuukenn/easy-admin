package com.cuukenn.cloud.auth.dto;

import com.cuukenn.openstudysource.cloud.framework.dto.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author changgg
 */
@RequiredArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
public class TokenDto extends Dto {
    private static final long serialVersionUID = -3414509553446023554L;
    /**
     * 令牌header前缀
     */
    private String tokenHeaderName;
    /**
     * 令牌值前缀
     */
    private String tokenPrefix;
    /**
     * 令牌
     */
    private String token;
}
