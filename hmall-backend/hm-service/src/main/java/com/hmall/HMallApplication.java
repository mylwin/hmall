package com.hmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 黑马商城启动类
 * <p>组件扫描 {@code com.hmall} 根包（覆盖 hm-common、hm-service 的 Bean），
 * 并注册 {@code com.hmall.mapper} 下的 Mapper。</p>
 */
@MapperScan("com.hmall.mapper")
@SpringBootApplication
public class HMallApplication {
    public static void main(String[] args) {
        SpringApplication.run(HMallApplication.class, args);
    }
}