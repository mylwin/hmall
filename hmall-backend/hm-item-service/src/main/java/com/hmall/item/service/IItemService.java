package com.hmall.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hmall.item.domain.dto.ItemDTO;
import com.hmall.item.domain.dto.OrderDetailDTO;
import com.hmall.item.domain.po.Item;

import java.util.Collection;
import java.util.List;

/**
 * 商品 服务类
 */
public interface IItemService extends IService<Item> {
    /**
     * 扣减库存
     * @param items 订单详情列表
     */
    void deductStock(List<OrderDetailDTO> items);

    /**
     * 根据id列表查询商品列表
     * @param ids id列表
     * @return 商品列表
     */
    List<ItemDTO> queryItemByIds(Collection<Long> ids);
}
