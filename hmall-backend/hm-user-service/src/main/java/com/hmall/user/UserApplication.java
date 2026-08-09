package com.hmall.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 用户服务启动类
 * <p>组件扫描 {@code com.hmall} 根包（覆盖 hm-common 的 Bean），
 * 并注册 {@code com.hmall.user.mapper} 下的 Mapper。</p>
 */
@MapperScan("com.hmall.user.mapper")
@SpringBootApplication(scanBasePackages = "com.hmall")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}