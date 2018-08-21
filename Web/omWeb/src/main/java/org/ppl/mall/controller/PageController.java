package org.ppl.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转Controller
 * @author Smith
 *
 */

@Controller
public class PageController {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	/*
	 * 页面跳转方法
	 * url对应同名称.jsp，比如 /item-list ---> item-list.jsp
	 */
	@RequestMapping("/{pageName}")
	public String page(@PathVariable("pageName") String pageName) {
		return pageName;
	}
	
}
