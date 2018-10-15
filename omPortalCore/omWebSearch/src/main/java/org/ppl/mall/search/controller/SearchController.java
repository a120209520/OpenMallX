package org.ppl.mall.search.controller;

import java.io.UnsupportedEncodingException;

import com.alibaba.dubbo.config.annotation.Reference;
import org.ppl.mall.model.SearchResult;
import org.ppl.mall.service.search.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品搜索Controller
 * @author PPL
 *
 */
@Controller
public class SearchController {
	
	@Reference
	private SearchItemService searchService;
	
	@Value("${SEARCH_ROWS}")
	private int SEARCH_ROWS;
	
	//搜索商品
	@RequestMapping("search.html")
	public String searchItems(String keyword, 
			@RequestParam(defaultValue="1") int page,
			Model model) {
		
		try {
			keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		SearchResult result = searchService.searchItems(keyword, page, SEARCH_ROWS);
		model.addAttribute("query", keyword);
		model.addAttribute("totalPages", result.getPageCount());
		model.addAttribute("page", page);
		model.addAttribute("recordCount", result.getTotalCount());
		model.addAttribute("itemList", result.getItemList());
		
		return "search";
	}
	
	//异常页面测试
	@RequestMapping("exception.html")
	public String exception() {
		int test = 1/0;
		return null;
	}
}
