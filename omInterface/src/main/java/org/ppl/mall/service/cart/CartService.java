package org.ppl.mall.service.cart;

import org.ppl.mall.model.CartItem;
import org.ppl.mall.pojo.TbCartItem;
import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.pojo.TbUser;
import org.ppl.mall.util.WebResult;

import java.util.List;

/**
 * 购物车接口
 */
public interface CartService {
    List<CartItem> getCartList(Long userId);
    WebResult addCartItem(Long userId, Long itemId, Integer num);
}
