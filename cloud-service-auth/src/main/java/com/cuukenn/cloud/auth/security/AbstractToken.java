package com.cuukenn.cloud.auth.security;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * @author changgg
 */
@Data
public abstract class AbstractToken implements Token {
    @Setter(value = AccessLevel.PROTECTED)
    private String token;
}
