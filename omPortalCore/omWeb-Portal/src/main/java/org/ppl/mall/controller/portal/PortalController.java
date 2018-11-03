package org.ppl.mall.controller.portal;

import com.alibaba.dubbo.config.annotation.Reference;
import org.ppl.mall.service.ItemService;
import org.ppl.mall.service.PortalService;
import org.ppl.mall.util.JsonpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 页面跳转Controller
 * @author PPL
 *
 */
@Controller
@RequestMapping("/content")
public class PortalController {

    @Value("${DEFAULT_NEW_PRODUCT_CID}")
    private long DEFAULT_NEW_PRODUCT_CID;

    @Value("${NEW_PRODUCT_SIZE}")
    private int NEW_PRODUCT_SIZE;

    @Reference
    private ItemService itemService;

    @Reference
    private PortalService portalService;

    //根据商品分类cid查询商品信息，显示到New-Product页面
    @RequestMapping(value="/newpro/item/{cid}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public MappingJacksonValue getNewProductItemByCid(Model model, @PathVariable Long cid, String callback) {
        if (DEFAULT_NEW_PRODUCT_CID == cid) {
            return JsonpUtils.jsonpResult(portalService.getNewProductByCid(null), callback);
        }
        return JsonpUtils.jsonpResult(portalService.getNewProductByCid(cid), callback);
    }

}
