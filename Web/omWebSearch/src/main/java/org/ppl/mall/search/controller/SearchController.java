package org.ppl.mall.search.controller;

import java.io.UnsupportedEncodingException;

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
 * @author Smith
 *
 */

@Controller
public class SearchController {
	
	@Autowired
	private SearchItemService searchService;
	
	@Value("${SEARCH_ROWS}")
	private int SEARCH_ROWS;
	
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
}
