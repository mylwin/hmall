package com.hmall.common.utils;

/**
 * 用户信息工具类
 * <p>基于 ThreadLocal 保存当前登录用户 id，实现请求线程内的数据隔离。</p>
 */
public class UserContext {
    /**
     * 线程本地变量，保存当前线程登录用户 id
     */
    private static final ThreadLocal<Long> tl = new ThreadLocal<>();

    /**
     * 保存当前登录用户信息到ThreadLocal
     * @param userId 用户id
     */
    public static void setUser(Long userId) {
        tl.set(userId);
    }

    /**
     * 获取当前登录用户信息
     * @return 用户id
     */
    public static Long getUser() {
        return tl.get();
    }

    /**
     * 移除当前登录用户信息
     */
    public static void removeUser(){
        tl.remove();
    }
}
