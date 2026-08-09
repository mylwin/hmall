package com.hmall.pay.enums;

import lombok.Getter;

/**
 * 支付状态枚举类
 */
@Getter
public enum PayStatus {
    NOT_COMMIT(0, "未提交"),
    WAIT_BUYER_PAY(1, "待支付"),
    TRADE_CLOSED(2, "已关闭"),
    TRADE_SUCCESS(3, "支付成功"),
    TRADE_FINISHED(3, "支付成功"),
    ;
    private final int value;
    private final String desc;

    PayStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 根据值判断是否相等
     * @param value 值
     * @return 是否相等
     */
    public boolean equalsValue(Integer value){
        if (value == null) {
            return false;
        }
        return getValue() == value;
    }
}