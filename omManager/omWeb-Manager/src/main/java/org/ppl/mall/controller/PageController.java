package org.ppl.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转Controller
 * @author PPL
 *
 */

@Controller
public class PageController {
	
	//主页
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	
	//页面跳转，url对应同名称.jsp，比如 /item-list ---> item-list.jsp
	@RequestMapping("/{pageName}")
	public String page(@PathVariable("pageName") String pageName) {
		return pageName;
	}
	
}
