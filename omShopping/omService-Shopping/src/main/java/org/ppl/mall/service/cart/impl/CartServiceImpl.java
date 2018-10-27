package org.ppl.mall.service.cart.impl;

import org.ppl.mall.mapper.TbCartItemMapper;
import org.ppl.mall.mapper.TbItemMapper;
import org.ppl.mall.model.CartItem;
import org.ppl.mall.pojo.TbCartItem;
import org.ppl.mall.pojo.TbCartItemExample;
import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.service.cart.CartService;
import org.ppl.mall.util.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@com.alibaba.dubbo.config.annotation.Service
public class CartServiceImpl implements CartService {

    @Autowired
    private TbCartItemMapper cartItemMapper;
    @Autowired
    private TbItemMapper itemMapper;

    /**
     * 获取指定用户购物车列表
     * @param userId
     * @return
     */
    @Override
    @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
    public List<CartItem> getCartList(Long userId) {
        TbCartItemExample example = new TbCartItemExample();
        TbCartItemExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<TbCartItem> tbCartItems = cartItemMapper.selectByExample(example);
        List<CartItem> cartItems = new ArrayList<>();
        for (TbCartItem tbCartItem : tbCartItems) {
            TbItem tbItem = itemMapper.selectByPrimaryKey(tbCartItem.getItemId());
            if (tbItem != null) {
                CartItem cartItem = new CartItem();
                cartItem.setId(tbCartItem.getItemId());
                cartItem.setNum(tbCartItem.getNum());
                cartItem.setPrice(tbItem.getPrice());
                cartItem.setTitle(tbItem.getTitle());
                cartItem.setImage(tbItem.getImages()[0]);
                cartItems.add(cartItem);
            }
        }
        return cartItems;
    }

    /**
     * 添加商品到购物车
     * @param userId 用户id
     * @param itemId 商品id
     * @param num    商品数量
     * @return WebResult
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public WebResult addCartItem(Long userId, Long itemId, Integer num) {
        TbItem item = itemMapper.selectByPrimaryKey(itemId);
        TbCartItemExample example = new TbCartItemExample();
        TbCartItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbCartItem> cartItems = cartItemMapper.selectByExample(example);
        if (item != null) {
            if (cartItems != null && cartItems.size() > 0) {
                TbCartItem cartItem = cartItems.get(0);
                cartItem.setNum(cartItem.getNum() + num);
                cartItem.setTotalFee(item.getPrice() / 100.0 * cartItem.getNum());
                cartItemMapper.updateByPrimaryKey(cartItem);
            } else {
                TbCartItem newCartItem = new TbCartItem();
                newCartItem.setItemId(itemId);
                newCartItem.setNum(num);
                newCartItem.setUserId(userId);
                newCartItem.setTotalFee((item.getPrice() / 100.0 * num));
                cartItemMapper.insertSelective(newCartItem);
            }
            return WebResult.ok();
        }
        return WebResult.build(WebResult.SERVER_ERROR, "商品不存在!");
    }
}
