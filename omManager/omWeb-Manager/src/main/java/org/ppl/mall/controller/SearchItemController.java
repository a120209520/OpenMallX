package org.ppl.mall.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.ppl.mall.service.search.SearchItemService;
import org.ppl.mall.util.WebResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 搜索商品Controller
 * @author PPL
 */

@Controller
@RequestMapping("/search")
public class SearchItemController {

	@Reference
	private SearchItemService searchItemService;
	
	@RequestMapping("/index/import")
	@ResponseBody
	public WebResult importItemList() {
		return searchItemService.importAllItems();
	}
}
