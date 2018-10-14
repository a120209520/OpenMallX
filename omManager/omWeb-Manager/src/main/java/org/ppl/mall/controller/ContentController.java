package org.ppl.mall.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.ppl.mall.model.DataGridResult;
import org.ppl.mall.pojo.TbContent;
import org.ppl.mall.service.ContentService;
import org.ppl.mall.util.MsgResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 内容Controller
 * @author PPL
 *
 */

@Controller
@RequestMapping("/content/")
public class ContentController {

	@Reference
	private ContentService contentService;
	
	//获取内容列表
	@RequestMapping("/list")
	@ResponseBody
	public DataGridResult<TbContent> getContentList(
			@RequestParam("categoryId")Long catId, 
			@RequestParam("page") Integer pageNum, 
			@RequestParam("rows") Integer pageSize ) {
		return contentService.getContentList(catId, pageNum, pageSize);
	}
	
	//添加内容
	@RequestMapping("/add")
	@ResponseBody
	public MsgResult addContent(TbContent content) {
		return contentService.addContent(content);
	}
	
	//删除内容
	@RequestMapping("/delete")
	@ResponseBody
	public MsgResult deleteContents(String ids) {
		String[] idArray = ids.split(",");
		for(String id:idArray) {
			contentService.deleteContents(Long.parseLong(id));
		}
		return MsgResult.ok();
	}
	
	//编辑内容
	@RequestMapping("/edit")
	@ResponseBody
	public MsgResult editContent(TbContent content) {
		return contentService.editContent(content);
	}
}
