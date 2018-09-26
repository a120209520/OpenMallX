package org.ppl.mall.cart.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.ppl.mall.cart.service.CartService;
import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.service.ItemService;
import org.ppl.mall.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ItemService itemService;

    @Override
    public List<TbItem> getCartListFromCookie(String cookie) {
        if (StringUtils.isBlank(cookie)) {
            return new ArrayList<>();
        }
        List<TbItem> list = JsonUtils.jsonToList(cookie, TbItem.class);
        return list;
    }
}
