package org.ppl.mall.controller;

import org.ppl.mall.model.DataGridResult;
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
	
	//通过id查询单个商品
	@RequestMapping("/{id}")
	@ResponseBody
	public TbItem getItemById(@PathVariable("id") Long itemId) {
		return itemService.getItemById(itemId);
	}
	
	//获取商品列表
	@RequestMapping("/list")
	@ResponseBody
	public DataGridResult<TbItem> getItemList(Integer page, Integer rows) {
		return itemService.getItemList(page, rows);
	}
}
