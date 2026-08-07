package com.hmall.common.exception;

/**
 * 认证异常，表示用户未登录或登录已失效，对应 HTTP 状态码 401
 * <p>如未携带 token、token 无效或已过期等场景。</p>
 */
public class UnauthorizedException extends CommonException{
    /**
     * 使用提示信息构造异常
     *
     * @param message 提示信息
     */
    public UnauthorizedException(String message) {
        super(message, 401);
    }

    /**
     * 使用提示信息和原始异常构造异常
     *
     * @param message 提示信息
     * @param cause   原始异常
     */
    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause, 401);
    }

    /**
     * 使用原始异常构造异常
     *
     * @param cause 原始异常
     */
    public UnauthorizedException(Throwable cause) {
        super(cause, 401);
    }
}
