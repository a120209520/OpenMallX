package org.ppl.mall.controller;

import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品管理Controller
 * @author Smith
 *
 */

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/{id}")
	@ResponseBody
	public TbItem getItemById(@PathVariable("id") Long itemId) {
		System.out.println("getItemById");
		return itemService.getItemById(itemId);
	}
}
