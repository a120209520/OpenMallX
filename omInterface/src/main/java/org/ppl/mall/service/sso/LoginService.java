package org.ppl.mall.service.sso;

import org.ppl.mall.pojo.TbUser;
import org.ppl.mall.util.WebResult;

/**
 * 登陆接口
 * @author PPL
 */
public interface LoginService {
    WebResult login(TbUser user);
    WebResult getUserByToken(String token);
}
