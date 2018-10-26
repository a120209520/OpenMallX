package org.ppl.mall.interceptor.cart;

import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.commons.lang3.StringUtils;
import org.ppl.mall.pojo.TbUser;
import org.ppl.mall.service.sso.LoginService;
import org.ppl.mall.util.CookieUtils;
import org.ppl.mall.util.WebResult;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Reference
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //Controller执行之前
        //001. 从Cookie中获取token
        String token = CookieUtils.getCookieValue(request, "token");
        //002. 若没有token，进入非登陆状态
        if (StringUtils.isBlank(token)) {
            return true;
        }
        //003. 若有token，调用sso服务获取用户信息
        WebResult result = loginService.getUserByToken(token);
        //004. 若无用户信息，表示用户登陆过期，进入非登陆状态
        if (result.getStatus() != WebResult.SUCCESS) {
            return true;
        }
        //005. 若有用户信息，进入登陆状态
        TbUser user = (TbUser) result.getData();
        //006. 存储用户信息到request中，在Controller中判断用户信息即可
        request.setAttribute("user", user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
