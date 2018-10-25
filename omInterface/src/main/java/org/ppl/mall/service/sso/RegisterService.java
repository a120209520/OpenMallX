package org.ppl.mall.service.sso;

import org.ppl.mall.pojo.TbUser;
import org.ppl.mall.util.WebResult;

/**
 * 注册接口
 * @author PPL
 */
public interface RegisterService {
    WebResult checkParams(String param, String type);
    WebResult register(TbUser user);
}
