package org.ppl.mall.cart.controller;

import org.apache.commons.lang3.StringUtils;
import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.service.ItemService;
import org.ppl.mall.util.CookieUtils;
import org.ppl.mall.util.JsonUtils;
import org.ppl.mall.util.MsgResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车Controller
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ItemService itemService;
    @Value("${COOKIE_CART_TIMEOUT}")
    private int COOKIE_CART_TIMEOUT;

    //添加商品(游客状态)
    @RequestMapping("/add/{itemId}")
    public String addCartToCookie(@PathVariable Long itemId,
                          @RequestParam(defaultValue="1") Integer num,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        //001.从Cookie中获取购物车商品
        List<TbItem> list = getCartListFromCookie(request);
        //002.判断商品是否已经存在
        boolean isExist = false;
        for (TbItem item:list) {
            if (item.getId() == itemId.longValue()) {
                isExist = true;
                item.setNum(item.getNum()+num);
                break;
            }
        }
        if (!isExist) {
            TbItem newItem = itemService.getItemById(itemId);
            newItem.setNum(num);
            newItem.setImage(newItem.getImages()[0]); //购物车只取一张图片
            list.add(newItem);
        }
        //003.修改Cookie并保存
        CookieUtils.setCookie(request, response, "cart",
                JsonUtils.objectToJson(list), COOKIE_CART_TIMEOUT, true);
        return "cartSuccess";
    }

    //购物车显示
    @RequestMapping("/cart")
    public String showCartList(HttpServletRequest request) {
        List<TbItem> list = getCartListFromCookie(request);
        request.setAttribute("itemList", list);
        return "cart";
    }

    //商品数量更新
    @RequestMapping("/update/num/{itemId}/{num}")
    @ResponseBody
    public MsgResult updateCartNum(@PathVariable Long itemId,
                                   @PathVariable Integer num,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        List<TbItem> list = getCartListFromCookie(request);
        for (TbItem item:list) {
            if (item.getId() == itemId.longValue()) {
                item.setNum(num);
                break;
            }
        }
        CookieUtils.setCookie(request, response, "cart",
                JsonUtils.objectToJson(list), COOKIE_CART_TIMEOUT, true);
        return MsgResult.ok();
    }

    //从Cookie中获取购物车列表
    private List<TbItem> getCartListFromCookie(HttpServletRequest request) {
        String json = CookieUtils.getCookieValue(request, "cart", true);
        if (StringUtils.isBlank(json)) {
            return new ArrayList<>();
        }
        List<TbItem> list = JsonUtils.jsonToList(json, TbItem.class);
        return list;
    }
}
