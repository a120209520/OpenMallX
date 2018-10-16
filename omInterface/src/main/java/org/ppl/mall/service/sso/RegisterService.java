package org.ppl.mall.service.sso;

import org.ppl.mall.pojo.TbUser;
import org.ppl.mall.util.MsgResult;

/**
 * 注册接口
 * @author PPL
 */
public interface RegisterService {
    MsgResult checkParams(String param, String type);
    MsgResult register(TbUser user);
}
