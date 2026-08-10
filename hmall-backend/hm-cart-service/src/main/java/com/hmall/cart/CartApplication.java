package com.hmall.cart;

import com.hmall.api.client.ItemClient;
import com.hmall.api.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 购物车服务启动类
 * <p>组件扫描 {@code com.hmall} 根包（覆盖 hm-common 的 Bean）,
 * 并注册 {@code com.hmall.cart.mapper} 下的 Mapper。</p>
 */
@MapperScan("com.hmall.cart.mapper")
@EnableFeignClients(clients = {ItemClient.class}, defaultConfiguration = DefaultFeignConfig.class)
@SpringBootApplication(scanBasePackages = "com.hmall")
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class, args);
    }
}