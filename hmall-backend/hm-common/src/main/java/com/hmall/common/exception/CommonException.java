package com.hmall.common.exception;

import lombok.Getter;

/**
 * 自定义通用异常基类，携带 HTTP 状态码
 * <p>各类业务异常（如 {@link BadRequestException}、{@link UnauthorizedException}）均继承此类，
 * 由 {@code com.hmall.common.advice.CommonExceptionAdvice} 统一处理。</p>
 */
@Getter
public class CommonException extends RuntimeException{
    /**
     * HTTP 状态码
     */
    private int code;

    /**
     * 使用提示信息和状态码构造异常
     *
     * @param message 提示信息
     * @param code    状态码
     */
    public CommonException(String message, int code) {
        super(message);
        this.code = code;
    }

    /**
     * 使用提示信息、原始异常和状态码构造异常
     *
     * @param message 提示信息
     * @param cause   原始异常
     * @param code    状态码
     */
    public CommonException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    /**
     * 使用原始异常和状态码构造异常
     *
     * @param cause 原始异常
     * @param code  状态码
     */
    public CommonException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }
}
