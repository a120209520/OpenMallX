package org.ppl.mall.sso.service;

import org.ppl.mall.pojo.TbUser;
import org.ppl.mall.util.MsgResult;

public interface LoginService {
    MsgResult login(TbUser user);
    MsgResult getUserByToken(String token);
}
