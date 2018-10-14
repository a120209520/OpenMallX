package org.ppl.mall.controller;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Reference;
import org.ppl.mall.model.DataGridResult;
import org.ppl.mall.model.TreeNode;
import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品分类Controller
 * @author PPL
 *
 */

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Reference
	private ItemCatService itemCatService;
	
	//获取商品分类列表
	@RequestMapping("/list")
	@ResponseBody
	public List<TreeNode> getItemCatList(@RequestParam(name="id", defaultValue="0") Long parentId) {
		return itemCatService.getItemCatList(parentId);
	}
	
}
