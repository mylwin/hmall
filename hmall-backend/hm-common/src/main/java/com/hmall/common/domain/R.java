package com.hmall.common.domain;

import com.hmall.common.exception.CommonException;
import lombok.Data;


/**
 * 统一返回结果
 */
@Data
public class R<T> {
    /**
     * 状态码
     */
    private int code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;

    /**
     * 成功，无业务数据
     * <p>即 {@link #ok(Object)} 的便捷重载，code 固定为 200、msg 固定为 "OK"、data 为 null。</p>
     *
     * @return 统一返回结果
     */
    public static R<Void> ok() {
        return ok(null);
    }

    /**
     * 成功，code 固定为 200，msg 固定为 "OK"
     * <p>data 为传入的业务数据，可为 null。</p>
     *
     * @param data 业务数据，可为 null
     * @param <T>  数据类型
     * @return 统一返回结果
     */
    public static <T> R<T> ok(T data) {
        return new R<>(200, "OK", data);
    }

    /**
     * 失败，code 固定为 500
     * <p>msg 由调用方传入，data 固定为 null。</p>
     *
     * @param msg 提示信息
     * @param <T> 数据类型
     * @return 统一返回结果
     */
    public static <T> R<T> error(String msg) {
        return new R<>(500, msg, null);
    }

    /**
     * 失败，code 与 msg 均由调用方指定
     * <p>data 固定为 null。</p>
     *
     * @param code 状态码
     * @param msg  提示信息
     * @param <T>  数据类型
     * @return 统一返回结果
     */
    public static <T> R<T> error(int code, String msg) {
        return new R<>(code, msg, null);
    }

    /**
     * 失败，从业务异常中提取状态码与提示信息
     * <p>code 为 {@link CommonException#getCode()}，msg 为异常信息，data 固定为 null。</p>
     *
     * @param e   业务异常
     * @param <T> 数据类型
     * @return 统一返回结果
     */
    public static <T> R<T> error(CommonException e) {
        return new R<>(e.getCode(), e.getMessage(), null);
    }

    /**
     * 无参构造器，字段取默认值（code=0，msg=null，data=null）
     */
    public R() {
    }

    /**
     * 全参构造器
     *
     * @param code 状态码
     * @param msg  提示信息
     * @param data 业务数据
     */
    public R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 是否成功（code 等于 200）
     *
     * @return true 表示成功
     */
    public boolean success(){
        return code == 200;
    }
}
