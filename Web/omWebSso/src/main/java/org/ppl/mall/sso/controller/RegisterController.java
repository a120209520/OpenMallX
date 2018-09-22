package org.ppl.mall.sso.controller;

import org.ppl.mall.pojo.TbUser;
import org.ppl.mall.sso.service.RegisterService;
import org.ppl.mall.util.MsgResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 注册Controller
 */
@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    //注册页面
    @RequestMapping("/page/register")
    public String index() {
        return "register";
    }

    //参数校验
    @RequestMapping("/user/check/{type}/{param}")
    @ResponseBody
    public MsgResult checkParams(@PathVariable String type, @PathVariable String param) {
        return registerService.checkParams(param, type);
    }

    //用户注册
    @RequestMapping(value="/user/register", method=RequestMethod.POST)
    @ResponseBody
    public MsgResult register(TbUser user) {
        return registerService.register(user);
    }
}
