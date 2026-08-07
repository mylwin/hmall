package com.hmall.common.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.util.NumberUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 继承自 hutool 的集合工具类
 */
public class CollUtils extends CollectionUtil {
    /**
     * 返回一个空集合（List）
     * @param <T> 集合元素类型
     * @return 空集合（List）
     */
    public static <T> List<T> emptyList() {
        return Collections.emptyList();
    }

    /**
     * 返回一个空集合（Set）
     * @param <T> 集合元素类型
     * @return 空集合（Set）
     */
    public static <T> Set<T> emptySet() {
        return Collections.emptySet();
    }

    /**
     * 返回一个空集合（Map）
     * @param <K> key类型
     * @param <V> value类型
     * @return 空集合（Map）
     */
    public static <K,V> Map<K, V> emptyMap() {
        return Collections.emptyMap();
    }

    /**
     * 返回一个只包含单个元素t的集合（Set）
     * @param <T> 集合元素类型
     * @param t 单个元素
     * @return 只包含单个元素t的Set
     */
    public static <T> Set<T> singletonSet(T t) {
        return Collections.singleton(t);
    }

    /**
     * 返回一个只包含单个元素t的集合（List）
     * @param <T> 集合元素类型
     * @param t 单个元素
     * @return 只包含单个元素t的List
     */
    public static <T> List<T> singletonList(T t) {
        return Collections.singletonList(t);
    }

    /**
     * 字符串集合转 Integer 集合
     * <p>注意：原集合为 null 或空时返回 null。</p>
     *
     * @param originList 原始字符串集合
     * @return 转换后的 Integer 集合
     */
    public static List<Integer> convertToInteger(List<String> originList){
        return CollUtils.isNotEmpty(originList) ? originList.stream().map(NumberUtil::parseInt).collect(Collectors.toList()) : null;
    }

    /**
     * 字符串集合转 Long 集合
     * <p>注意：原集合为 null 或空时返回 null。</p>
     *
     * @param originLIst 原始字符串集合
     * @return 转换后的 Long 集合
     */
    public static List<Long> convertToLong(List<String> originLIst){
        return CollUtils.isNotEmpty(originLIst) ? originLIst.stream().map(NumberUtil::parseLong).collect(Collectors.toList()) : null;
    }

    /**
     * 以 conjunction 为分隔符将集合转换为字符串 如果集合元素为数组、Iterable或Iterator，则递归组合其为字符串
     * @param collection 集合
     * @param conjunction 分隔符
     * @param <T> 集合元素类型
     * @return 连接后的字符串
     * See Also: IterUtil.join(Iterator, CharSequence)
     */
    public static <T> String join(Collection<T> collection, CharSequence conjunction) {
        if (null == collection || collection.isEmpty()) {
            return null;
        }
        return IterUtil.join(collection.iterator(), conjunction);
    }

    /**
     * 以 conjunction 为分隔符将集合转换为字符串 如果集合元素为数组、Iterable或Iterator，则递归组合其为字符串（忽略null）
     * @param collection 集合
     * @param conjunction 分隔符
     * @param <T> 集合元素类型
     * @return 连接后的字符串
     * See Also: IterUtil.join(Iterator, CharSequence)
     */
    public static <T> String joinIgnoreNull(Collection<T> collection, CharSequence conjunction) {
        if (null == collection || collection.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (T t : collection) {
            if(t == null) continue;
            sb.append(t).append(",");
        }
        if(sb.length() <= 0){
            return null;
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}