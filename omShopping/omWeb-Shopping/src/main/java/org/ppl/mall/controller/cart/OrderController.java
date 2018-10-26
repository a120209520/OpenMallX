package org.ppl.mall.controller.cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 订单Controller
 * @author PPL
 */
@Controller
public class OrderController {

    //添加商品
    @RequestMapping("/page/order")
    public String index() {
        return "order";
    }
}
