package com.cuukenn.openstudysource.cloud.framework.auth.util;

import cn.hutool.extra.spring.SpringUtil;
import com.cuukenn.openstudysource.cloud.framework.auth.annotation.AnonymousAccess;
import com.cuukenn.openstudysource.cloud.framework.auth.enums.RequestMethodEnum;
import com.cuukenn.openstudysource.cloud.framework.exception.BizException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author changgg
 */
public final class SecurityUtil {
    private SecurityUtil() {
    }

    /**
     * 获取当前登录的用户
     *
     * @return UserDetails
     */
    public static UserDetails getCurrentUser() {
        UserDetailsService userDetailsService = SpringUtil.getBean(UserDetailsService.class);
        return userDetailsService.loadUserByUsername(getCurrentUsername());
    }

    /**
     * 获取系统用户名称
     *
     * @return 系统用户名称
     */
    public static String getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new AuthenticationCredentialsNotFoundException("用户未登录");
        }
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        }
        throw new BizException(-301, "用户已登陆,但用户信息无法获取");
    }

    /**
     * 获取被匿名注解包裹的rest服务
     *
     * @param handlerMethodMap 集合
     * @return 匿名集合
     */
    public static Map<String, Set<String>> getAnonymousUrl(Map<RequestMappingInfo, HandlerMethod> handlerMethodMap) {
        Map<String, Set<String>> anonymousUrls = new HashMap<>(6);
        Set<String> get = new HashSet<>();
        Set<String> post = new HashSet<>();
        Set<String> put = new HashSet<>();
        Set<String> patch = new HashSet<>();
        Set<String> delete = new HashSet<>();
        Set<String> all = new HashSet<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> infoEntry : handlerMethodMap.entrySet()) {
            HandlerMethod handlerMethod = infoEntry.getValue();
            AnonymousAccess anonymousAccess = handlerMethod.getMethodAnnotation(AnonymousAccess.class);
            if (null != anonymousAccess) {
                List<RequestMethod> requestMethods = new ArrayList<>(infoEntry.getKey().getMethodsCondition().getMethods());
                RequestMethodEnum request = Objects.requireNonNull(
                    RequestMethodEnum.find(requestMethods.size() == 0 ? RequestMethodEnum.ALL.getType() : requestMethods.get(0).name())
                );
                if (infoEntry.getKey().getPatternsCondition() == null) {
                    throw new IllegalArgumentException("patterns is null");
                }
                Set<String> patterns = infoEntry.getKey().getPatternsCondition().getPatterns();
                switch (request) {
                    case GET:
                        get.addAll(patterns);
                        break;
                    case POST:
                        post.addAll(patterns);
                        break;
                    case PUT:
                        put.addAll(patterns);
                        break;
                    case PATCH:
                        patch.addAll(patterns);
                        break;
                    case DELETE:
                        delete.addAll(patterns);
                        break;
                    default:
                        all.addAll(patterns);
                        break;
                }
            }
        }
        anonymousUrls.put(RequestMethodEnum.GET.getType(), get);
        anonymousUrls.put(RequestMethodEnum.POST.getType(), post);
        anonymousUrls.put(RequestMethodEnum.PUT.getType(), put);
        anonymousUrls.put(RequestMethodEnum.PATCH.getType(), patch);
        anonymousUrls.put(RequestMethodEnum.DELETE.getType(), delete);
        anonymousUrls.put(RequestMethodEnum.ALL.getType(), all);
        return anonymousUrls;
    }
}
