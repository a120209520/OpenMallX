package org.ppl.mall.service.sso.impl;

import org.apache.commons.lang3.StringUtils;
import org.ppl.mall.jedis.JedisClient;
import org.ppl.mall.mapper.TbUserMapper;
import org.ppl.mall.pojo.TbUser;
import org.ppl.mall.pojo.TbUserExample;
import org.ppl.mall.service.sso.LoginService;
import org.ppl.mall.util.JsonUtils;
import org.ppl.mall.util.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;

/**
 * 登陆Service
 * @author PPL
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private TbUserMapper userMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${LOGIN_TIMEOUT}")
    private int LOGIN_TIMEOUT;

    //用户登陆
    @Override
    @Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
    public WebResult login(TbUser pUser) {
        //001.登陆校验
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(pUser.getUsername());
        List<TbUser> list = userMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            return WebResult.build(WebResult.REQUEST_ERROR, "用户名或密码错误！");
        }
        TbUser user = list.get(0);
        if (!DigestUtils.md5DigestAsHex(pUser.getPassword().getBytes()).equals(user.getPassword())) {
            return WebResult.build(WebResult.REQUEST_ERROR, "用户名或密码错误！");
        }
        //002.登陆成功——生成token
        String token = UUID.randomUUID().toString();
        //003.将token保存到redis
        user.setPassword(null); //安全考虑，不保存密码信息到redis
        jedisClient.set("LOGIN-SESSION:"+token, JsonUtils.objectToJson(user));
        //004.设置过期时间
        jedisClient.expire("LOGIN-SESSION:"+token, LOGIN_TIMEOUT);

        return WebResult.ok(token);
    }

    //获取登陆信息(根据Cookie中token信息返回用户信息)
    @Override
    @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
    public WebResult getUserByToken(String token) {
        //001.从redis获取登陆信息
        String json = jedisClient.get("LOGIN-SESSION:" + token);
        if (StringUtils.isBlank(json)) {
            return WebResult.build(WebResult.REQUEST_ERROR, "请重新登陆！");
        }
        TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
        //002.更新过期时间
        jedisClient.expire("LOGIN-SESSION:"+token, LOGIN_TIMEOUT);
        return WebResult.ok(user);
    }
}
