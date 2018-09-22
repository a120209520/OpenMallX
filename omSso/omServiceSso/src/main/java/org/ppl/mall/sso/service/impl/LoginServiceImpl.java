package org.ppl.mall.sso.service.impl;

import org.ppl.mall.jedis.JedisClient;
import org.ppl.mall.mapper.TbUserMapper;
import org.ppl.mall.pojo.TbUser;
import org.ppl.mall.pojo.TbUserExample;
import org.ppl.mall.sso.service.LoginService;
import org.ppl.mall.util.JsonUtils;
import org.ppl.mall.util.MsgResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;

/**
 * 登陆Service
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private TbUserMapper userMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${LOGIN_TIMEOUT}")
    private int LOGIN_TIMEOUT;

    @Override
    public MsgResult login(TbUser pUser) {
        //001.登陆校验
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(pUser.getUsername());
        List<TbUser> list = userMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            return MsgResult.build(MsgResult.REQUEST_ERROR, "用户名或密码错误！");
        }
        TbUser user = list.get(0);
        if (!DigestUtils.md5DigestAsHex(pUser.getPassword().getBytes()).equals(user.getPassword())) {
            return MsgResult.build(MsgResult.REQUEST_ERROR, "用户名或密码错误！");
        }
        //002.登陆成功——生成token
        String token = UUID.randomUUID().toString();
        //003.将token保存到redis
        user.setPassword(null); //安全考虑，不能保存密码信息到客户端
        jedisClient.set("LOGIN-SESSION:"+token, JsonUtils.objectToJson(user));
        //004.设置过期时间
        jedisClient.expire("LOGIN-SESSION:"+token, LOGIN_TIMEOUT);

        return MsgResult.ok(token);
    }
}
