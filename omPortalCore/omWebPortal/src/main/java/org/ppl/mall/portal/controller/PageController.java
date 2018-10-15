package org.ppl.mall.portal.controller;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Reference;
import org.ppl.mall.pojo.TbContent;
import org.ppl.mall.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转Controller
 * @author PPL
 *
 */
@Controller
public class PageController {
	
	@Reference
	private ContentService contentService;
	
	@Value("${CONTENT_ROLL_ID}")
	private Long CONTENT_ROLL_ID;

	public PageController(){
		System.out.println("new controller!");
	}
	
	//主页 
	@RequestMapping("/index.html")
	public String index(Model model) {
		List<TbContent> adList1 = contentService.getContentList(CONTENT_ROLL_ID);
		model.addAttribute("adList1", adList1);
		return "index";
	}
}
