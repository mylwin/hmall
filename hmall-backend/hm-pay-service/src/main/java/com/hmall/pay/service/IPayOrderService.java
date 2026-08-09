package com.hmall.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hmall.pay.domain.dto.PayApplyDTO;
import com.hmall.pay.domain.dto.PayOrderFormDTO;
import com.hmall.pay.domain.po.PayOrder;


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
