package com.hmall.common.exception;

/**
 * 请求错误异常，表示客户端请求有误，对应 HTTP 状态码 400
 * <p>如请求参数缺失、格式错误等场景。</p>
 */
public class BadRequestException extends CommonException{
    /**
     * 使用提示信息构造异常
     *
     * @param message 提示信息
     */
    public BadRequestException(String message) {
        super(message, 400);
    }

    /**
     * 使用提示信息和原始异常构造异常
     *
     * @param message 提示信息
     * @param cause   原始异常
     */
    public BadRequestException(String message, Throwable cause) {
        super(message, cause, 400);
    }

    /**
     * 使用原始异常构造异常
     *
     * @param cause 原始异常
     */
    public BadRequestException(Throwable cause) {
        super(cause, 400);
    }
}
