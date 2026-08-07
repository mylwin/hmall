package com.hmall.service;

import com.hmall.domain.dto.OrderFormDTO;
import com.hmall.domain.po.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 订单管理模块接口
 */
public interface IOrderService extends IService<Order> {
    /**
     * 创建订单
     * @param orderFormDTO 订单信息
     * @return 订单编号
     */
    Long createOrder(OrderFormDTO orderFormDTO);

    /**
     * 标记订单已支付
     * @param orderId 订单编号
     */
    void markOrderPaySuccess(Long orderId);
}
