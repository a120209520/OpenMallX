package org.ppl.mall.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.pojo.TbItemDesc;
import org.ppl.mall.service.ItemService;
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

    //商品详情
    @RequestMapping("/item/{itemId}")
    public String itemDetial(@PathVariable("itemId") Long id, Model model) {
        TbItem tbItem = itemService.getItemById(id);
        TbItemDesc tbItemDesc = itemService.getItemDescById(id);
        model.addAttribute("item", tbItem);
        model.addAttribute("itemDesc", tbItemDesc);
        return "item";
    }
}
