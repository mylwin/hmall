package com.hmall.pay.enums;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;

/**
 * 支付方式枚举类
 */
@Getter
public enum PayChannel {
    wxPay("微信支付"),
    aliPay("支付宝支付"),
    balance("余额支付"),
    ;

    /**
     * 描述
     */
    private final String desc;

    PayChannel(String desc) {
        this.desc = desc;
    }

    /**
     * 根据值获取描述
     *
     * @param value 值
     * @return 描述
     */
    public static String desc(String value){
        if (StrUtil.isBlank(value)) {
            return "";
        }
        return PayChannel.valueOf(value).getDesc();
    }
}
