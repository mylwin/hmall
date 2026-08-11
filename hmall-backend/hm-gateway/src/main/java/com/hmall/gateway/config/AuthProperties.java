package com.hmall.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.List;

/**
 * 登录认证配置属性，绑定配置前缀 {@code hm.auth}
 */
@Data
@EnableConfigurationProperties(AuthProperties.class)
@ConfigurationProperties(prefix = "hm.auth")
public class AuthProperties {
    /**
     * 需要登录校验的路径
     */
    private List<String> includePaths;
    /**
     * 免登录放行的路径
     */
    private List<String> excludePaths;
}
