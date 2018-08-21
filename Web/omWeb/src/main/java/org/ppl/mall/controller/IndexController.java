package org.ppl.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页Controller
 * @author Smith
 *
 */

@Controller
public class IndexController {
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
