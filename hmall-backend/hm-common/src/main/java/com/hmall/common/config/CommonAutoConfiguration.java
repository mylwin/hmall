package com.hmall.common.config;

import com.hmall.common.advice.CommonExceptionAdvice;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * hm-common 公共自动配置类
 * <p>集中导入各微服务共享的组件，服务模块依赖本依赖即可自动装配，
 * 无需在各服务启动类中额外扩大组件扫描范围。</p>
 * <p>仅在 Spring MVC（Servlet）环境下装配，WebFlux 网关等响应式应用不加载。</p>
 */
@Configuration
@ConditionalOnClass(DispatcherServlet.class)
@Import(CommonExceptionAdvice.class)
public class CommonAutoConfiguration {
}
