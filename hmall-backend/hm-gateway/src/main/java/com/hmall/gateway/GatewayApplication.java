package com.hmall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 网关启动类
 * <p>基于 Spring Cloud Gateway（WebFlux）作为统一入口，负责请求路由转发、
 * JWT 鉴权（{@link com.hmall.gateway.filter.AuthGlobalFilter}）及用户信息透传。</p>
 */
@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
