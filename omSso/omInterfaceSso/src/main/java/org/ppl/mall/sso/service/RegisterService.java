package org.ppl.mall.sso.service;

import org.ppl.mall.pojo.TbUser;
import org.ppl.mall.util.MsgResult;

public interface RegisterService {
    MsgResult checkParams(String param, String type);
    MsgResult register(TbUser user);
}
