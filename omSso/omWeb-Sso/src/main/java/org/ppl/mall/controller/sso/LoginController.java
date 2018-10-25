package org.ppl.mall.controller.sso;

import com.alibaba.dubbo.config.annotation.Reference;
import org.ppl.mall.pojo.TbUser;
import org.ppl.mall.service.sso.LoginService;
import org.ppl.mall.util.CookieUtils;
import org.ppl.mall.util.WebResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆Controller
 * @author PPL
 */
@Controller
public class LoginController {

    @Reference
    private LoginService loginService;

    //登陆页面
    @RequestMapping("/page/login")
    public String index() {
        return "login";
    }

    //用户登陆
    @RequestMapping(value="/user/login", method=RequestMethod.POST)
    @ResponseBody
    public WebResult login(TbUser user, HttpServletRequest request, HttpServletResponse response) {
        WebResult result = loginService.login(user);
        if(result.getStatus() == WebResult.SUCCESS) {
            String token = result.getData().toString();
            CookieUtils.setCookie(request, response, "login", token);
        }
        return result;
    }

    //获取用户信息
    @RequestMapping("/user/token/{token}")
    @ResponseBody
    public WebResult getUserByToken(@PathVariable String token) {
        return loginService.getUserByToken(token);
    }
}
