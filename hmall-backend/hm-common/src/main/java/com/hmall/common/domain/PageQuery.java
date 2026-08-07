package com.hmall.common.domain;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.Min;

/**
 * 分页查询参数
 */
@Data
@Schema(description = "分页查询条件")
@Accessors(chain = true)
public class PageQuery {
    /**
     * 默认每页查询的数量
     */
    public static final Integer DEFAULT_PAGE_SIZE = 20;
    /**
     * 默认的页码
     */
    public static final Integer DEFAULT_PAGE_NUM = 1;
    /**
     * 页码，默认 {@link #DEFAULT_PAGE_NUM}
     */
    @Schema(description = "页码")
    @Min(value = 1, message = "页码不能小于1")
    private Integer pageNo = DEFAULT_PAGE_NUM;
    /**
     * 每页查询数量，默认 {@link #DEFAULT_PAGE_SIZE}
     */
    @Schema(description = "每页查询数量")
    @Min(value = 1, message = "每页查询数量不能小于1")
    private Integer pageSize = DEFAULT_PAGE_SIZE;
    /**
     * 是否升序，默认 true
     */
    @Schema(description = "是否升序")
    private Boolean isAsc = true;
    /**
     * 排序字段（前端指定），可为空，为空时由各转换方法自行兜底
     */
    @Schema(description = "排序字段")
    private String sortBy;

    /**
     * 查询的起始位置（数据偏移量，即 SQL 中的 offset）
     * <p>计算方式：(pageNo - 1) * pageSize。
     * 注意：pageNo 或 pageSize 为 null 时自动拆箱会抛出空指针异常。</p>
     *
     * @return 查询起始位置
     */
    public int from(){
        return (pageNo - 1) * pageSize;
    }

    /**
     * 转换为 MyBatis-Plus 分页参数
     * <p>排序优先级：手动传入的 orderItems 优先；未传入时按前端 sortBy 排序；
     * sortBy 也为空则不排序（orderItems 与 sortBy 均有空值处理）。
     * 注意：pageNo 或 pageSize 为 null 时自动拆箱会抛出空指针异常。</p>
     *
     * @param orderItems 手动指定的排序字段，可为空
     * @param <T>        数据类型
     * @return MyBatis-Plus 分页对象
     */
    public <T> Page<T> toMpPage(OrderItem... orderItems) {
        Page<T> page = new Page<>(pageNo, pageSize);
        // 是否手动指定排序方式
        if (orderItems != null && orderItems.length > 0) {
            for (OrderItem orderItem : orderItems) {
                page.addOrder(orderItem);
            }
            return page;
        }
        // 前端是否有排序字段
        if (StrUtil.isNotEmpty(sortBy)){
            OrderItem orderItem = new OrderItem();
            orderItem.setAsc(isAsc);
            orderItem.setColumn(sortBy);
            page.addOrder(orderItem);
        }
        return page;
    }

    /**
     * 转换为 MyBatis-Plus 分页参数（指定默认排序字段）
     * <p>当前端未传排序字段（sortBy 为空）时，使用 defaultSortBy 兜底并按 isAsc 排序；
     * 否则按前端传入的 sortBy 及 isAsc 排序。
     * 注意：pageNo 或 pageSize 为 null 时自动拆箱会抛出空指针异常。</p>
     *
     * @param defaultSortBy 默认排序字段，sortBy 为空时生效
     * @param isAsc         是否升序
     * @param <T>           数据类型
     * @return MyBatis-Plus 分页对象
     */
    public <T> Page<T> toMpPage(String defaultSortBy, boolean isAsc) {
        if (StringUtils.isBlank(sortBy)){
            sortBy = defaultSortBy;
            this.isAsc = isAsc;
        }
        Page<T> page = new Page<>(pageNo, pageSize);
        OrderItem orderItem = new OrderItem();
        orderItem.setAsc(this.isAsc);
        orderItem.setColumn(sortBy);
        page.addOrder(orderItem);
        return page;
    }

    /**
     * 转换为 MyBatis-Plus 分页参数（默认按创建时间 create_time 降序排序）
     *
     * @param <T> 数据类型
     * @return MyBatis-Plus 分页对象
     */
    public <T> Page<T> toMpPageDefaultSortByCreateTimeDesc() {
        return toMpPage("create_time", false);
    }
}
