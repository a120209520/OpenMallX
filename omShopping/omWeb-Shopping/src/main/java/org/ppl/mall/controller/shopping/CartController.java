package org.ppl.mall.controller.shopping;

import com.alibaba.dubbo.config.annotation.Reference;
import org.ppl.mall.model.CartItem;
import org.ppl.mall.pojo.TbCartItem;
import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.pojo.TbUser;
import org.ppl.mall.service.ItemService;
import org.ppl.mall.service.cart.CartService;
import org.ppl.mall.util.CookieUtils;
import org.ppl.mall.util.WebResult;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 购物车Controller
 * @author PPL
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Reference
    private ItemService itemService;
    @Reference
    private CartService cartService;

    //添加商品
    @RequestMapping(value="/add/{itemId}/{num}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Object addCart(@PathVariable Long itemId,
                          @PathVariable Integer num,
                          HttpServletRequest request,
                          String callback) {
        TbUser user = (TbUser) request.getAttribute("user");
        System.out.println(user);
        System.out.println(callback);
        WebResult result = null;
        if (user != null) {
            //已登陆
            result = addCartWithLogin(itemId, num, user);
        } else {
            //未登录
            result = WebResult.build(WebResult.REQUEST_ERROR, "请先登陆!");
        }
        MappingJacksonValue jValue = new MappingJacksonValue(result);
        jValue.setJsonpFunction(callback);
        return jValue;
    }

    //添加商品——登陆状态
    private WebResult addCartWithLogin(Long itemId, Integer num, TbUser user) {
        return cartService.addCartItem(user.getId(), itemId, num);
    }

    //购物车显示
    @RequestMapping(value="/list/{userId}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public MappingJacksonValue showCartList(@PathVariable Long userId, String callback) {
        List<CartItem> cartList = cartService.getCartList(userId);
        MappingJacksonValue jValue = new MappingJacksonValue(cartList);
        jValue.setJsonpFunction(callback);
        return jValue;
    }
}
