package com.hmall.cart.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hmall.cart.domain.po.Cart;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 购物车表 Mapper 接口
 */
public interface CartMapper extends BaseMapper<Cart> {

    /**
     * 购物车中该商品数量加 1
     *
     * @param itemId 商品id
     * @param userId 用户id
     */
    @Update("UPDATE cart SET num = num + 1 WHERE user_id = #{userId} AND item_id = #{itemId}")
    void updateNum(@Param("itemId") Long itemId, @Param("userId") Long userId);
}
