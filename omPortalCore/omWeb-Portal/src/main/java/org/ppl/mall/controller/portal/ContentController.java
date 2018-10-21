package org.ppl.mall.controller.portal;

import com.alibaba.dubbo.config.annotation.Reference;
import org.ppl.mall.model.DataGridResult;
import org.ppl.mall.pojo.TbContent;
import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.pojo.TbItemCat;
import org.ppl.mall.service.ContentService;
import org.ppl.mall.service.ItemCatService;
import org.ppl.mall.service.ItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 页面跳转Controller
 * @author PPL
 *
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Value("${DEFAULT_NEW_PRODUCT_CID}")
    private long DEFAULT_NEW_PRODUCT_CID;

    @Value("${NEW_PRODUCT_SIZE}")
    private int NEW_PRODUCT_SIZE;

    @Reference
    private ItemService itemService;

    @RequestMapping("/newpro/item/{cid}")
    @ResponseBody
    public List<TbItem> getNewProductByCat(Model model, @PathVariable Long cid) {
        System.out.println("cid="+cid);
        if ( DEFAULT_NEW_PRODUCT_CID == cid ) {
            return itemService.getItemList(1, NEW_PRODUCT_SIZE).getRows();
        }
        DataGridResult<TbItem> dataGridResult = itemService.getItemByCatId(cid, 1, NEW_PRODUCT_SIZE);
        return dataGridResult.getRows();
    }
}
