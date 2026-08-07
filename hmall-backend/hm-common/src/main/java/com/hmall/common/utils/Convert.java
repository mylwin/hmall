package com.hmall.common.utils;

/**
 * 对原对象进行计算，设置到目标对象中
 **/
public interface Convert<R,T>{
    /**
     * 对原对象进行计算，将结果设置到目标对象中
     *
     * @param origin 原对象
     * @param target 目标对象
     */
    void convert(R origin, T target);
}