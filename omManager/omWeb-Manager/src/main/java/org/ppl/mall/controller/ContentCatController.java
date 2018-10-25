package org.ppl.mall.controller;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Reference;
import org.ppl.mall.model.TreeNode;
import org.ppl.mall.service.ContentCatService;
import org.ppl.mall.util.WebResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 内容分类Controller
 * @author PPL
 *
 */

@Controller
@RequestMapping("/content/cat")
public class ContentCatController {
	
	@Reference
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
	public WebResult addContentCat(Long parentId, String name) {
		return contentCatService.addContentCat(parentId, name);
	}
	
	//添加内容分类
	@RequestMapping("/update")
	@ResponseBody
	public WebResult updateContentCat(Long id, String name) {
		return contentCatService.updateContentCat(id, name);
	}
	
	//删除内容分类
	@RequestMapping("/delete")
	@ResponseBody
	public WebResult deleteContentCat(Long id) {
		return contentCatService.deleteContentCat(id);
	}
}
