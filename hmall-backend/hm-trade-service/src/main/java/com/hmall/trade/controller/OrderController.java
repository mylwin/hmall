package com.hmall.trade.controller;

import com.hmall.api.dto.OrderFormDTO;
import com.hmall.common.utils.BeanUtils;
import com.hmall.trade.domain.vo.OrderVO;
import com.hmall.trade.service.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 订单管理
 */
@Tag(name = "订单管理接口")
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final IOrderService orderService;

    /**
     * 根据id查询订单
     * @param orderId 订单id
     * @return 订单详情
     */
    @Operation(summary = "根据id查询订单")
    @GetMapping("{id}")
    public OrderVO queryOrderById(@Parameter(name = "id", description = "订单id") @PathVariable("id") Long orderId) {
        return BeanUtils.copyBean(orderService.getById(orderId), OrderVO.class);
    }

    /**
     * 创建订单
     * @param orderFormDTO 订单信息
     * @return 订单编号
     */
    @Operation(summary = "创建订单")
    @PostMapping
    public Long createOrder(@RequestBody OrderFormDTO orderFormDTO){
        return orderService.createOrder(orderFormDTO);
    }

    /**
     * 标记订单已支付
     * @param orderId 订单id
     */
    @Operation(summary = "标记订单已支付")
    @Parameter(name = "orderId", description = "订单id", in = ParameterIn.PATH)
    @PutMapping("/{orderId}")
    public void markOrderPaySuccess(@PathVariable("orderId") Long orderId) {
        orderService.markOrderPaySuccess(orderId);
    }
}
