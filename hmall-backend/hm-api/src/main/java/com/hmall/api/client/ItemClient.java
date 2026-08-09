package com.hmall.api.client;

import com.hmall.api.dto.ItemDTO;
import com.hmall.api.dto.OrderDetailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

/**
 * 商品服务远程调用客户端
 * <p>基于 OpenFeign 声明式调用 {@code hm-item-service} 的商品接口，由 Spring Cloud LoadBalancer 负责实例选择。</p>
 */
@FeignClient("hm-item-service")
public interface ItemClient {

    /**
     * 根据id列表批量查询商品信息
     *
     * @param ids 商品id列表
     * @return 商品信息列表
     */
    @GetMapping("/items")
    List<ItemDTO> queryItemByIds(@RequestParam("ids") Collection<Long> ids);

    /**
     * 扣减库存
     * @param items 订单详情列表
     */
    @PutMapping("/stock/deduct")
    void deductStock(@RequestBody List<OrderDetailDTO> items);
}
