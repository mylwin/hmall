package com.hmall.pay;

import com.hmall.api.client.TradeClient;
import com.hmall.api.client.UserClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 支付服务启动类
 * <p>组件扫描 {@code com.hmall} 根包（覆盖 hm-common 的 Bean）,
 * 并注册 {@code com.hmall.pay.mapper} 下的 Mapper。</p>
 */
@MapperScan("com.hmall.pay.mapper")
@EnableFeignClients(clients = {UserClient.class, TradeClient.class})
@SpringBootApplication(scanBasePackages = "com.hmall")
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }
}