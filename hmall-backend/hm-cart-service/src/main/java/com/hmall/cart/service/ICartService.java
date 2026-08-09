package com.hmall.cart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hmall.cart.domain.dto.CartFormDTO;
import com.hmall.cart.domain.po.Cart;
import com.hmall.cart.domain.vo.CartVO;

import java.util.Collection;
import java.util.List;

/**
 * 购物车相关接口
 */
public interface ICartService extends IService<Cart> {
    /**
     * 添加商品到购物车
     * @param cartFormDTO 购物车表单数据
     */
    void addItem2Cart(CartFormDTO cartFormDTO);

    /**
     * 查询当前用户购物车列表
     * @return 购物车列表
     */
    List<CartVO> queryMyCarts();

    /**
     * 根据商品id集合查询购物车列表
     * @param itemIds 商品id集合
     */
    void removeByItemIds(Collection<Long> itemIds);
}
