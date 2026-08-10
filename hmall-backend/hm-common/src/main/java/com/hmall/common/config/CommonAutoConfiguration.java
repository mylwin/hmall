package com.hmall.common.config;

import com.hmall.common.advice.CommonExceptionAdvice;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * hm-common 公共自动配置类
 * <p>集中导入各微服务共享的组件，服务模块依赖本依赖即可自动装配，
 * 无需在各服务启动类中额外扩大组件扫描范围。</p>
 */
@Configuration
@Import(CommonExceptionAdvice.class)
public class CommonAutoConfiguration {
}
