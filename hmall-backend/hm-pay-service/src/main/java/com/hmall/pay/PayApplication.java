package com.hmall.pay;

import com.hmall.api.client.TradeClient;
import com.hmall.api.client.UserClient;
import com.hmall.api.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 支付服务启动类
 * <p>组件扫描本服务包，公共 Bean（MyBatis、JSON、全局异常处理）由 hm-common 自动装配，
 * 并注册 {@code com.hmall.pay.mapper} 下的 Mapper。</p>
 */
@MapperScan("com.hmall.pay.mapper")
@EnableFeignClients(clients = {UserClient.class, TradeClient.class}, defaultConfiguration = DefaultFeignConfig.class)
@SpringBootApplication
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }
}