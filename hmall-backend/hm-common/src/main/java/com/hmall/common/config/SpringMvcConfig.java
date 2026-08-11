package com.hmall.common.config;

import com.hmall.common.interceptor.UserInfoInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC 配置，注册用户信息拦截器
 * <p>仅在 Servlet 环境（Spring MVC 服务）下生效，WebFlux 网关等响应式应用不加载。
 * 微服务通过拦截器从请求头 {@code user-info} 获取网关传递的用户 id，填充 {@code UserContext}。</p>
 */
@Configuration
@ConditionalOnClass(DispatcherServlet.class)
public class SpringMvcConfig implements WebMvcConfigurer {
    /**
     * 注册用户信息拦截器
     *
     * @param registry 拦截器注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInfoInterceptor());
    }
}
