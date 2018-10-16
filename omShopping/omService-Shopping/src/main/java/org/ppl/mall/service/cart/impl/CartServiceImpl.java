package org.ppl.mall.service.cart.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.commons.lang3.StringUtils;
import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.service.ItemService;
import org.ppl.mall.service.cart.CartService;
import org.ppl.mall.util.JsonUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@com.alibaba.dubbo.config.annotation.Service
public class CartServiceImpl implements CartService {

    @Reference
    private ItemService itemService;

    @Override
    @Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
    public List<TbItem> getCartListFromCookie(String cookie) {
        if (StringUtils.isBlank(cookie)) {
            return new ArrayList<>();
        }
        List<TbItem> list = JsonUtils.jsonToList(cookie, TbItem.class);
        return list;
    }
}
