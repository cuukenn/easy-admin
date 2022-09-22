package com.cuukenn.cloud.auth.security.filter;

import cn.hutool.core.util.StrUtil;
import com.cuukenn.cloud.auth.security.SecurityConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author changgg
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TokenFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = resolveToken(httpServletRequest);
        // 对于 Token 为空的不需要去查 Redis
        if (StrUtil.isNotBlank(token)) {
            //TODO
            log.info("token is {}", token);
        }
        filterChain.doFilter(request, response);
    }

    /**
     * resolve token header value
     *
     * @param request request
     * @return 令牌
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(SecurityConstant.TOKEN_HEADER_NAME);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(SecurityConstant.TOKEN_PREFIX)) {
            // 去掉令牌前缀
            return bearerToken.replace(SecurityConstant.TOKEN_PREFIX, "");
        } else {
            log.debug("invalid token:{}", bearerToken);
        }
        return null;
    }
}
