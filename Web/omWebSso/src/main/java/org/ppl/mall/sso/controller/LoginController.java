package org.ppl.mall.sso.controller;

import org.ppl.mall.pojo.TbUser;
import org.ppl.mall.sso.service.LoginService;
import org.ppl.mall.sso.service.RegisterService;
import org.ppl.mall.util.MsgResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登陆Controller
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    //登陆页面
    @RequestMapping("/page/login")
    public String index() {
        return "login";
    }

    //用户登陆
    @RequestMapping(value="/user/login", method=RequestMethod.POST)
    @ResponseBody
    public MsgResult login(TbUser user) {
        MsgResult result = loginService.login(user);
        if(result.getStatus() == MsgResult.SUCCESS) {
            String token = result.getData().toString();
        }
    }
}
