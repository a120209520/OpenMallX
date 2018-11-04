package org.ppl.mall.controller.portal;

import com.alibaba.dubbo.config.annotation.Reference;
import org.ppl.mall.model.DataGridResult;
import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.service.ContentService;
import org.ppl.mall.service.ItemCatService;
import org.ppl.mall.service.ItemService;
import org.ppl.mall.tools.item.HtmlGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 页面跳转Controller
 * @author PPL
 *
 */
@Controller
public class PageController {
	
	@Reference
	private ContentService contentService;
	@Reference
    private ItemCatService itemCatService;
	@Reference
    private ItemService itemService;
	@Autowired
	private HtmlGenerator htmlGenerator;
	
	@Value("${CONTENT_SHOW_NOW}")
	private Long CONTENT_SHOW_NOW;
	
	//主页 
	@RequestMapping("/index")
	public String index(Model model) {
	    //直接跳转到静态页面服务器
		return "redirect:http://localhost:8703/";
	}


	@RequestMapping("/blank")
	public String blank() {
		return "blank";
	}

	@RequestMapping("/refresh")
    @ResponseBody
	public String refresh() {
        htmlGenerator.genPortalIndex();
        DataGridResult<TbItem> itemList = itemService.getItemList(1, 100);
        itemList.getRows().forEach((item)->{
            htmlGenerator.genItemDetail(item.getId());
        });
		return "ok";
	}
}
