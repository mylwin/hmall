package com.hmall.gateway.filter;

import com.hmall.common.exception.UnauthorizedException;
import com.hmall.common.utils.CollUtils;
import com.hmall.gateway.config.AuthProperties;
import com.hmall.gateway.util.JwtTool;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 网关全局鉴权过滤器
 * <p>拦截请求（排除 {@link AuthProperties#getExcludePaths()} 放行路径），校验请求头中的
 * JWT token，解析出用户 id 后写入 {@code user-info} 请求头透传给下游微服务。</p>
 */
@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(AuthProperties.class)
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final JwtTool jwtTool;

    private final AuthProperties authProperties;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 网关过滤逻辑：校验 token 并透传用户信息
     *
     * @param exchange 请求交换对象
     * @param chain    网关过滤器链
     * @return 响应流
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.获取 request
        ServerHttpRequest request = exchange.getRequest();

        // 2.判断是否需要拦截
        if (isExclude(request.getPath().toString())) {
            // 2.1.不需要拦截，直接放行
            return chain.filter(exchange);
        }

        // 3.获取请求头中的 token
        String token = null;
        List<String> authorization = request.getHeaders().get("authorization");
        if (!CollUtils.isEmpty(authorization)) {
            token = authorization.get(0);
        }

        // 4.校验并解析头 token
        Long userId = null;
        try {
            userId = jwtTool.parseToken(token);
        } catch (UnauthorizedException e) {
            // 无效则放行
            ServerHttpResponse response = exchange.getResponse();
            response.setRawStatusCode(HttpStatus.UNAUTHORIZED.value());
            return response.setComplete();
        }

        // 5.传递用户信息
        String userInfo = userId.toString();
        ServerWebExchange sex = exchange.mutate()
                .request(builder -> builder.header("user-info", userInfo))
                .build();

        // 6.放行
        return chain.filter(sex);
    }

    /**
     * 判断路径是否在免登录放行列表中
     *
     * @param antPath 请求路径
     * @return true 表示放行，无需校验 token
     */
    private boolean isExclude(String antPath) {
        for (String excludePath : authProperties.getExcludePaths()) {
            if (antPathMatcher.match(excludePath, antPath)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 过滤器执行顺序，值越小越先执行
     *
     * @return 顺序值
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
