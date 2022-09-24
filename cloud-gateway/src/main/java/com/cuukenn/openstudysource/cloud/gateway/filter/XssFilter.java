package com.cuukenn.openstudysource.cloud.gateway.filter;

import com.cuukenn.openstudysource.cloud.framework.exception.BizException;
import io.netty.buffer.ByteBufAllocator;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author changgg
 */
@Component
@ConfigurationProperties("app.xss")
public class XssFilter implements GlobalFilter, Ordered {
    private final Logger log = LoggerFactory.getLogger(XssFilter.class);
    /**
     * 自定义白名单
     */
    private final static Safelist CUSTOM_WHITELIST = Safelist.relaxed()
        .addAttributes("video", "width", "height", "controls", "alt", "src")
        .addAttributes(":all", "style", "class");
    private List<XssWhiteUrl> whiteUrls;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        String method = request.getMethodValue();
        // 判断是否在白名单中
        if (this.white(uri.getPath(), method)) {
            return chain.filter(exchange);
        }
        // 只拦截POST和PUT请求
        if ((HttpMethod.POST.name().equals(method) || HttpMethod.PUT.name().equals(method))) {
            return DataBufferUtils.join(request.getBody())
                .flatMap(dataBuffer -> {
                    // 取出body中的参数
                    final byte[] oldBytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(oldBytes);
                    final String bodyString = new String(oldBytes, StandardCharsets.UTF_8);
                    if (!Jsoup.isValid(bodyString, CUSTOM_WHITELIST)) {
                        log.error("参数包含特殊字符,请检查后重新请求,url:{}", request.getURI());
                        return Mono.error(new BizException(-500, "参数包含特殊字符,请检查后重新请求"));
                    }
                    final ServerHttpRequest newRequest = new ServerHttpRequestDecorator(request.mutate().uri(uri).build()) {
                        @Override
                        @NonNull
                        public Flux<DataBuffer> getBody() {
                            return Flux.just(toDataBuffer(oldBytes));
                        }

                        @Override
                        @NonNull
                        public HttpHeaders getHeaders() {
                            return request.getHeaders();
                        }
                    };
                    return chain.filter(exchange.mutate().request(newRequest).build());
                });
        } else {
            return chain.filter(exchange);
        }
    }

    /**
     * 是否是白名单
     *
     * @param url    路由
     * @param method 请求方式
     * @return true/false
     */
    private boolean white(String url, String method) {
        return whiteUrls != null && whiteUrls.contains(new XssWhiteUrl(url, method));
    }

    /**
     * 字节数组转DataBuffer
     *
     * @param bytes 字节数组
     * @return DataBuffer
     */
    private DataBuffer toDataBuffer(byte[] bytes) {
        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
        DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
        buffer.write(bytes);
        return buffer;
    }

    public static final int ORDER = 10;

    @Override
    public int getOrder() {
        return ORDER;
    }

    private static class XssWhiteUrl {
        @NotEmpty
        private String url;
        @NotEmpty
        private String method;

        public XssWhiteUrl() {
        }

        public XssWhiteUrl(String url, String method) {
            this.url = url;
            this.method = method;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }
    }

    public List<XssWhiteUrl> getWhiteUrls() {
        return whiteUrls;
    }

    public void setWhiteUrls(List<XssWhiteUrl> whiteUrls) {
        this.whiteUrls = whiteUrls;
    }
}
