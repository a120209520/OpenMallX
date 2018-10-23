package org.ppl.mall.service;

import org.ppl.mall.model.NewProduct;

import java.util.List;

/**
 * 首页Service接口
 * 用于Web端首页相关内容展示
 * @author PPL
 */
public interface PortalService {
    List<NewProduct> getNewProductByCid(Long cid);
}
