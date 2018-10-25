package org.ppl.mall.controller.sso;

import com.alibaba.dubbo.config.annotation.Reference;
import org.ppl.mall.pojo.TbUser;
import org.ppl.mall.service.sso.RegisterService;
import org.ppl.mall.util.WebResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 注册Controller
 * @author PPL
 */
@Controller
public class RegisterController {

    @Reference
    private RegisterService registerService;

    //注册页面
    @RequestMapping("/page/register")
    public String index() {
        return "register";
    }

    //注册页面
    @RequestMapping("/page/result")
    public String result() {
        return "result";
    }

    //参数校验
    @RequestMapping("/user/check/{type}/{param}")
    @ResponseBody
    public WebResult checkParams(@PathVariable String type, @PathVariable String param) {
        return registerService.checkParams(param, type);
    }

    //用户注册
    @RequestMapping(value="/user/register", method=RequestMethod.POST)
    @ResponseBody
    public WebResult register(TbUser user) {
        return registerService.register(user);
    }
}
