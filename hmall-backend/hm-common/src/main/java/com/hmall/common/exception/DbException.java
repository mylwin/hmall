package com.hmall.common.exception;

/**
 * 数据库操作异常，表示数据库访问失败，对应 HTTP 状态码 500
 * <p>如连接失败、SQL 执行错误等场景。</p>
 */
public class DbException extends CommonException{
    /**
     * 使用提示信息构造异常
     *
     * @param message 提示信息
     */
    public DbException(String message) {
        super(message, 500);
    }

    /**
     * 使用提示信息和原始异常构造异常
     *
     * @param message 提示信息
     * @param cause   原始异常
     */
    public DbException(String message, Throwable cause) {
        super(message, cause, 500);
    }

    /**
     * 使用原始异常构造异常
     *
     * @param cause 原始异常
     */
    public DbException(Throwable cause) {
        super(cause, 500);
    }
}
