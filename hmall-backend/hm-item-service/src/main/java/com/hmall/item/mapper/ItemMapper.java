package com.hmall.item.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hmall.item.domain.dto.OrderDetailDTO;
import com.hmall.item.domain.po.Item;
import org.apache.ibatis.annotations.Update;

/**
 * 商品表 Mapper 接口
 */
public interface ItemMapper extends BaseMapper<Item> {

    /**
     * 扣减商品库存
     *
     * @param orderDetail 订单明细（含商品id与购买数量）
     */
    @Update("UPDATE item SET stock = stock - #{num} WHERE id = #{itemId}")
    void updateStock(OrderDetailDTO orderDetail);
}
