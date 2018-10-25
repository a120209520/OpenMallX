package org.ppl.mall.service.sso.impl;

import org.apache.commons.lang3.StringUtils;
import org.ppl.mall.mapper.TbUserMapper;
import org.ppl.mall.pojo.TbUser;
import org.ppl.mall.pojo.TbUserExample;
import org.ppl.mall.service.sso.RegisterService;
import org.ppl.mall.util.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * 注册Service
 * @author PPL
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private TbUserMapper userMapper;

    //参数校验
    @Override
    @Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
    public WebResult checkParams(String param, String type) {
        if(StringUtils.isBlank(param)) {
            return WebResult.ok(false);
        }
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        switch (type) {
            case "username":
                criteria.andUsernameEqualTo(param);break;
            case "cellphone":
                criteria.andPhoneEqualTo(param);break;
            case "email":
                criteria.andEmailEqualTo(param);break;
        }
        List<TbUser> list = userMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            //非法，用户名已存在
            return WebResult.ok(false);
        } else {
            //合法
            return WebResult.ok(true);
        }
    }

    //用户注册
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public WebResult register(TbUser user) {
        //数据校验
        if(!(boolean)checkParams(user.getUsername(), "username").getData()) {
            return WebResult.build(WebResult.REQUEST_ERROR, "用户名非法或已经存在！");
        }
        if(!(boolean)checkParams(user.getPhone(), "cellphone").getData()) {
            return WebResult.build(WebResult.REQUEST_ERROR, "手机号非法或已经存在！");
        }
        //数据补全
        Date curTime = new Date();
        user.setCreated(curTime);
        user.setUpdated(curTime);
        //密码加密
        String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pass);
        userMapper.insert(user);
        return WebResult.ok();
    }
}
