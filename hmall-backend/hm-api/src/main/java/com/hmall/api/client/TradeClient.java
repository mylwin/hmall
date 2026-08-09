package com.hmall.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * 交易服务远程调用客户端
 * <p>基于 OpenFeign 声明式调用 {@code hm-trade-service} 的订单接口，由 Spring Cloud LoadBalancer 负责实例选择。</p>
 */
@FeignClient("hm-trade-service")
public interface TradeClient {

    /**
     * 标记订单已支付
     *
     * @param orderId 订单id
     */
    @PutMapping("/orders/{orderId}")
    void markOrderPaySuccess(@PathVariable("orderId") Long orderId);
}
