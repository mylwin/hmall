package com.hmall.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

/**
 * 购物车服务远程调用客户端
 * <p>基于 OpenFeign 声明式调用 {@code hm-cart-service} 的购物车接口，由 Spring Cloud LoadBalancer 负责实例选择。</p>
 */
@FeignClient("hm-cart-service")
public interface CartClient {

    /**
     * 根据商品id集合批量删除购物车条目
     * @param itemIds 商品id集合
     */
    @DeleteMapping("/carts")
    void deleteCartItemByIds(@RequestParam("ids") Collection<Long> itemIds);
}