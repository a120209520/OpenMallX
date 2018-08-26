package org.ppl.mall.portal.controller;

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
	//主页
	@RequestMapping("/index.html")
	public String index() {
		return "index";
	}
}
