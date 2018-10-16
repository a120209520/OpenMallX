package org.ppl.mall.service.cart;

import org.ppl.mall.pojo.TbItem;

import java.util.List;

/**
 * 购物车接口
 */
public interface CartService {
    List<TbItem> getCartListFromCookie(String cookie);
}
