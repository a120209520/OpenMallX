package org.ppl.mall.controller.cart;

import com.alibaba.dubbo.config.annotation.Reference;
import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.pojo.TbUser;
import org.ppl.mall.service.ItemService;
import org.ppl.mall.service.cart.CartService;
import org.ppl.mall.util.CookieUtils;
import org.ppl.mall.util.JsonUtils;
import org.ppl.mall.util.MsgResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Value("${COOKIE_CART_TIMEOUT}")
    private int COOKIE_CART_TIMEOUT;

    //添加商品
    @RequestMapping("/add/{itemId}")
    public String addCart(@PathVariable Long itemId,
                          @RequestParam(defaultValue="1") Integer num,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        TbUser user = (TbUser) request.getAttribute("user");
        if (user != null) {
            //已登陆
            return addCartWithLogin(itemId, num, user, request, response);
        } else {
            //未登陆
            return addCartWithoutLogin(itemId, num, request, response);
        }
    }

    //添加商品——登陆状态
    private String addCartWithLogin(Long itemId, Integer num, TbUser user,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        return "cartSuccess";
    }

    //添加商品——非登录状态
    private String addCartWithoutLogin(Long itemId, Integer num,
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
        System.out.println("show cart");
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

    //商品删除
    @RequestMapping("/delete/{itemId}")
    public String deleteCartItem(@PathVariable Long itemId,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        List<TbItem> list = getCartListFromCookie(request);
        for (TbItem item:list) {
            if (item.getId() == itemId.longValue()) {
                list.remove(item);
                break;
            }
        }
        CookieUtils.setCookie(request, response, "cart",
                JsonUtils.objectToJson(list), COOKIE_CART_TIMEOUT, true);
        return "redirect:/cart/cart.html";
    }

    //从Cookie中获取购物车列表
    private List<TbItem> getCartListFromCookie(HttpServletRequest request) {
        String json = CookieUtils.getCookieValue(request, "cart", true);
        return cartService.getCartListFromCookie(json);
    }
}
