package com.hmall.api.config;

import com.hmall.common.utils.UserContext;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;

/**
 * Feign 默认配置类
 * <p>作为各服务 {@code @EnableFeignClients(defaultConfiguration = ...)} 的默认配置：
 * 设置 Feign 日志级别为 FULL，并通过 {@link RequestInterceptor} 将当前用户 id
 * 写入 {@code user-info} 请求头，实现微服务间用户信息透传。</p>
 */
public class DefaultFeignConfig {
    @Bean
    public Logger.Level feignLogLevel(){
        return Logger.Level.FULL;
    }

    /**
     * 用户信息传递拦截器
     * <p>发起 Feign 调用时，将当前登录用户 id 写入 {@code user-info} 请求头，
     * 透传给下游微服务；未登录时忽略。</p>
     *
     * @return Feign 请求拦截器
     */
    @Bean
    public RequestInterceptor userInfoRequestInterceptor() {
        return new RequestInterceptor(){
            /**
             * 拦截 Feign 请求，将用户 id 写入请求头
             *
             * @param requestTemplate Feign 请求模板
             */
            @Override
            public void apply(RequestTemplate requestTemplate) {
                // 获取用户信息
                Long userId = UserContext.getUser();
                if(userId == null){
                    return;
                }
                requestTemplate.header("user-info", userId.toString());
            }
        };
    }
}