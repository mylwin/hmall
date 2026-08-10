package com.hmall.trade;

import com.hmall.api.client.CartClient;
import com.hmall.api.client.ItemClient;
import com.hmall.api.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 交易服务启动类
 * <p>组件扫描本服务包，公共 Bean（MyBatis、JSON、全局异常处理）由 hm-common 自动装配，
 * 并注册 {@code com.hmall.trade.mapper} 下的 Mapper。</p>
 */
@MapperScan("com.hmall.trade.mapper")
@EnableFeignClients(clients = {ItemClient.class, CartClient.class}, defaultConfiguration = DefaultFeignConfig.class)
@SpringBootApplication
public class TradeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradeApplication.class, args);
    }
}