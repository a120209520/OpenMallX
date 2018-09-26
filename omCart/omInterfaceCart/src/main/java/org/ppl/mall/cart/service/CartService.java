package org.ppl.mall.cart.service;

import org.ppl.mall.pojo.TbItem;

import java.util.List;

public interface CartService {
    List<TbItem> getCartListFromCookie(String cookie);
}
