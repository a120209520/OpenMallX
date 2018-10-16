package org.ppl.mall.service.sso;

import org.ppl.mall.pojo.TbUser;
import org.ppl.mall.util.MsgResult;

/**
 * 登陆接口
 * @author PPL
 */
public interface LoginService {
    MsgResult login(TbUser user);
    MsgResult getUserByToken(String token);
}
