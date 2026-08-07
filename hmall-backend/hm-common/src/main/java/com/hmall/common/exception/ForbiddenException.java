package com.hmall.common.exception;

/**
 * 禁止访问异常，表示已登录但无权限访问资源，对应 HTTP 状态码 403
 */
public class ForbiddenException extends CommonException{
    /**
     * 使用提示信息构造异常
     *
     * @param message 提示信息
     */
    public ForbiddenException(String message) {
        super(message, 403);
    }

    /**
     * 使用提示信息和原始异常构造异常
     *
     * @param message 提示信息
     * @param cause   原始异常
     */
    public ForbiddenException(String message, Throwable cause) {
        super(message, cause, 403);
    }

    /**
     * 使用原始异常构造异常
     *
     * @param cause 原始异常
     */
    public ForbiddenException(Throwable cause) {
        super(cause, 403);
    }
}
