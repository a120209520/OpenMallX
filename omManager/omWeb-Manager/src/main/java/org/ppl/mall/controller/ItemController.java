package org.ppl.mall.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.ppl.mall.model.DataGridResult;
import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.service.ItemService;
import org.ppl.mall.util.WebResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品管理Controller
 * @author PPL
 *
 */

@Controller
@RequestMapping("/item")
public class ItemController {

	@Reference
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
	public DataGridResult<TbItem> getItemList(
			@RequestParam("page") Integer pageNum, 
			@RequestParam("rows") Integer pageSize) {
		return itemService.getItemList(pageNum, pageSize);
	}
	
	//添加商品
	@RequestMapping("/save")
	@ResponseBody
	public WebResult addItem(TbItem item, String desc) {
		return itemService.addItem(item, desc);
	}
	
	//编辑商品-基本信息回显
	@RequestMapping("/edit-query/item/{id}")
	@ResponseBody
	public WebResult editItemQuery(@PathVariable("id") Long itemId) {
		return WebResult.ok(itemService.getItemById(itemId));
	}
	
	//编辑商品-描述回显
	@RequestMapping("/edit-query/item-desc/{id}")
	@ResponseBody
	public WebResult editItemDescQuery(@PathVariable("id") Long itemId) {
		return WebResult.ok(itemService.getItemDescById(itemId));
	}
	
	//编辑商品-提交
	@RequestMapping("/edit-submit")
	@ResponseBody
	public WebResult editItem(TbItem item, String desc) {
		return itemService.editItem(item, desc);
	}
	
	//删除商品
	@RequestMapping("/delete")
	@ResponseBody
	public WebResult deleteItems(String ids) {
		String[] idArray = ids.split(",");
		for(String id:idArray) {
			itemService.deleteItem(Long.parseLong(id));
		}
		return WebResult.ok();
	}
	
	//下架商品
	@RequestMapping("/unshelve")
	@ResponseBody
	public WebResult unShelveItems(String ids) {
		String[] idArray = ids.split(",");
		for(String id:idArray) {
			itemService.unShelveItem(Long.parseLong(id));
		}
		return WebResult.ok();
	}
	
	//上架商品
	@RequestMapping("/reshelf")
	@ResponseBody
	public WebResult reShelfItems(String ids) {
		String[] idArray = ids.split(",");
		for(String id:idArray) {
			itemService.reShelfItem(Long.parseLong(id));
		}
		return WebResult.ok();
	}
}
