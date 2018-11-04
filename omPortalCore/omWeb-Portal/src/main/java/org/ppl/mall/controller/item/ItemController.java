package org.ppl.mall.controller.item;

import com.alibaba.dubbo.config.annotation.Reference;
import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.pojo.TbItemDesc;
import org.ppl.mall.service.ItemService;
import org.ppl.mall.tools.item.HtmlGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前台系统商品Controller
 */
@Controller
public class ItemController {

    @Reference
    private ItemService itemService;
    @Autowired
    private HtmlGenerator htmlGenerator;

    //商品详情
    @RequestMapping("/item/{itemId}")
    public String itemDetial(@PathVariable("itemId") Long id) {
        //用户一般不会访问到这里，万一URL泄露则直接跳转到静态页面服务器
        return "redirect:http://localhost:8703/items/"+id+".html";
    }
}
