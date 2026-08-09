package com.hmall.item;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 商品服务启动类
 * <p>组件扫描 {@code com.hmall} 根包（覆盖 hm-common 的 Bean），
 * 并注册 {@code com.hmall.item.mapper} 下的 Mapper。</p>
 */
@MapperScan("com.hmall.item.mapper")
@SpringBootApplication(scanBasePackages = "com.hmall")
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class, args);
    }
}