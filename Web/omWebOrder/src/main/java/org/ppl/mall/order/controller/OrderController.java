package org.ppl.mall.order.controller;

import org.ppl.mall.cart.service.CartService;
import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/order/order-cart")
    public String showOrderCart(HttpServletRequest request) {
        System.out.println("aaaaaaaa");
        String json = CookieUtils.getCookieValue(request, "cart", true);
        List<TbItem> list = cartService.getCartListFromCookie(json);
        request.setAttribute("cartList", list);
        return "order-cart";
    }
}
