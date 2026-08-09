package com.hmall.trade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hmall.api.dto.OrderFormDTO;
import com.hmall.trade.domain.po.Order;


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
