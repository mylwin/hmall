package com.hmall.common.domain;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hmall.common.utils.BeanUtils;
import com.hmall.common.utils.CollUtils;
import com.hmall.common.utils.Convert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页查询结果
 * @param <T> 数据类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO<T> {
    /**
     * 数据总数
     */
    protected Long total;
    /**
     * 数据总页数
     */
    protected Long pages;
    /**
     * 数据列表
     */
    protected List<T> list;

    /**
     * 创建一个空的分页查询结果
     * <p>参数直接透传，不做空值校验；结果列表固定为空列表。</p>
     *
     * @param total 数据总数
     * @param pages 数据总页数
     * @param <T>   数据类型
     * @return 空的分页查询结果
     */
    public static <T> PageDTO<T> empty(Long total, Long pages) {
        return new PageDTO<>(total, pages, CollUtils.emptyList());
    }

    /**
     * 创建一个空的分页查询结果（利用分页对象）
     * <p>注意：page 为 null 时本方法不做校验，会抛出空指针异常。</p>
     *
     * @param page 分页对象
     * @param <T>  数据类型
     * @return 空的分页查询结果
     */
    public static <T> PageDTO<T> empty(Page<?> page) {
        return new PageDTO<>(page.getTotal(), page.getPages(), CollUtils.emptyList());
    }

    /**
     * 利用分页对象创建分页查询结果
     * <p>page 为 null 时返回字段均为 null 的空结果对象；
     * 记录列表为 null 或空时返回空分页结果（保留 total 与 pages）。</p>
     *
     * @param page 分页对象
     * @param <T>  数据类型
     * @return 分页查询结果
     */
    public static <T> PageDTO<T> of(Page<T> page) {
        if(page == null){
            return new PageDTO<>();
        }
        if (CollUtils.isEmpty(page.getRecords())) {
            return empty(page);
        }
        return new PageDTO<>(page.getTotal(), page.getPages(), page.getRecords());
    }

    /**
     * 利用分页对象创建分页查询结果（通过转换器将原记录转换为目标类型）
     * <p>page 为 null 时返回字段均为 null 的空结果对象；
     * 记录列表为 null 或空时返回空分页结果（保留 total 与 pages）。</p>
     *
     * @param page   分页对象
     * @param mapper 数据转换器，用于将原记录映射为目标类型
     * @param <T>    目标数据类型
     * @param <R>    原数据类型
     * @return 分页查询结果
     */
    public static <T,R> PageDTO<T> of(Page<R> page, Function<R, T> mapper) {
        if(page == null){
            return new PageDTO<>();
        }
        if (CollUtils.isEmpty(page.getRecords())) {
            return empty(page);
        }
        return new PageDTO<>(page.getTotal(), page.getPages(),
                page.getRecords().stream().map(mapper).collect(Collectors.toList()));
    }

    /**
     * 利用分页对象创建分页查询结果（列表已由调用方转换好）
     * <p>注意：page 为 null 时本方法不做校验，会抛出空指针异常；
     * list 不做任何校验，直接作为结果列表。</p>
     *
     * @param page 分页对象
     * @param list 已转换好的数据列表
     * @param <T>  数据类型
     * @return 分页查询结果
     */
    public static <T> PageDTO<T> of(Page<?> page, List<T> list) {
        return new PageDTO<>(page.getTotal(), page.getPages(), list);
    }

    /**
     * 利用分页对象创建分页查询结果（自动将原记录转换为目标类型）
     * <p>注意：page 为 null 时本方法不做校验，会抛出空指针异常；
     * 记录列表为 null 或空时转换为空列表，不会抛异常。</p>
     *
     * @param page  分页对象
     * @param clazz 目标对象的class
     * @param <T>   目标数据类型
     * @param <R>   原数据类型
     * @return 分页查询结果
     */
    public static <T, R> PageDTO<T> of(Page<R> page, Class<T> clazz) {
        return new PageDTO<>(page.getTotal(), page.getPages(), BeanUtils.copyList(page.getRecords(), clazz));
    }

    /**
     * 利用分页对象创建分页查询结果（自动转换原记录，并支持字段自定义转换器）
     * <p>注意：page 为 null 时本方法不做校验，会抛出空指针异常；
     * 记录列表为 null 或空时转换为空列表，不会抛异常。</p>
     *
     * @param page    分页对象
     * @param clazz   目标对象的class
     * @param convert 转换器，用于处理字段名不匹配的字段
     * @param <T>     目标数据类型
     * @param <R>     原数据类型
     * @return 分页查询结果
     */
    public static <T, R> PageDTO<T> of(Page<R> page, Class<T> clazz, Convert<R, T> convert) {
        return new PageDTO<>(page.getTotal(), page.getPages(), BeanUtils.copyList(page.getRecords(), clazz, convert));
    }
}
