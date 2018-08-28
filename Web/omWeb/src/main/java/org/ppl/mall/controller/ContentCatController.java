package org.ppl.mall.controller;

import java.util.List;

import org.ppl.mall.model.DataGridResult;
import org.ppl.mall.model.TreeNode;
import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.service.ContentCatService;
import org.ppl.mall.service.ItemCatService;
import org.ppl.mall.util.MsgResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 内容分类Controller
 * @author Smith
 *
 */

@Controller
@RequestMapping("/content/cat")
public class ContentCatController {
	
	@Autowired
	private ContentCatService contentCatService;
	
	//获取内容分类列表
	@RequestMapping("/list")
	@ResponseBody
	public List<TreeNode> getContentCatList(@RequestParam(name="id", defaultValue="0") Long parentId) {
		return contentCatService.getContentCatList(parentId);
	}
	
	//添加内容分类
	@RequestMapping("/add")
	@ResponseBody
	public MsgResult addContentCat(Long parentId, String name) {
		return contentCatService.addContentCat(parentId, name);
	}
	
	//添加内容分类
	@RequestMapping("/update")
	@ResponseBody
	public MsgResult updateContentCat(Long id, String name) {
		return contentCatService.updateContentCat(id, name);
	}
	
	//删除内容分类
	@RequestMapping("/delete")
	@ResponseBody
	public MsgResult deleteContentCat(Long id) {
		return contentCatService.deleteContentCat(id);
	}
}
