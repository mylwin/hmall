package com.hmall.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.Resource;

import java.time.Duration;

/**
 * JWT 配置属性，绑定配置前缀 {@code hm.jwt}
 */
@Data
@EnableConfigurationProperties(JwtProperties.class)
@ConfigurationProperties(prefix = "hm.jwt")
public class JwtProperties {
    /**
     * 密钥库文件位置（jks 格式）
     */
    private Resource location;
    /**
     * 密钥库密码
     */
    private String password;
    /**
     * 密钥别名
     */
    private String alias;
    /**
     * access-token 有效期，默认 10 分钟
     */
    private Duration tokenTTL = Duration.ofMinutes(10);
}
