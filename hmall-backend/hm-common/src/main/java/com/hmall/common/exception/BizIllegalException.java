package com.hmall.common.exception;

/**
 * 业务非法异常，表示业务规则校验不通过，对应 HTTP 状态码 500
 * <p>如库存不足、订单状态不允许操作等场景。</p>
 */
public class BizIllegalException extends CommonException{
    /**
     * 使用提示信息构造异常
     *
     * @param message 提示信息
     */
    public BizIllegalException(String message) {
        super(message, 500);
    }

    /**
     * 使用提示信息和原始异常构造异常
     *
     * @param message 提示信息
     * @param cause   原始异常
     */
    public BizIllegalException(String message, Throwable cause) {
        super(message, cause, 500);
    }

    /**
     * 使用原始异常构造异常
     *
     * @param cause 原始异常
     */
    public BizIllegalException(Throwable cause) {
        super(cause, 500);
    }
}
