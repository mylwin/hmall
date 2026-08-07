package com.hmall.service;

import com.hmall.domain.dto.PayApplyDTO;
import com.hmall.domain.dto.PayOrderFormDTO;
import com.hmall.domain.po.PayOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 支付单 服务类
 */
public interface IPayOrderService extends IService<PayOrder> {
    /**
     * 申请支付单（幂等）
     * @param applyDTO 支付申请参数
     * @return 支付单id
     */
    String applyPayOrder(PayApplyDTO applyDTO);

    /**
     * 尝试基于用户余额支付
     * @param payOrderFormDTO 支付订单参数
     */
    void tryPayOrderByBalance(PayOrderFormDTO payOrderFormDTO);
}
