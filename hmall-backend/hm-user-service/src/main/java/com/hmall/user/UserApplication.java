package com.hmall.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 用户服务启动类
 * <p>组件扫描本服务包，公共 Bean（MyBatis、JSON、全局异常处理）由 hm-common 自动装配，
 * 并注册 {@code com.hmall.user.mapper} 下的 Mapper。</p>
 */
@MapperScan("com.hmall.user.mapper")
@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}