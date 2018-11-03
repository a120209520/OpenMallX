package org.ppl.mall.tools.item;

import com.alibaba.dubbo.config.annotation.Reference;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.ppl.mall.model.NewProduct;
import org.ppl.mall.pojo.TbContent;
import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.pojo.TbItemCat;
import org.ppl.mall.pojo.TbItemDesc;
import org.ppl.mall.service.ContentService;
import org.ppl.mall.service.ItemCatService;
import org.ppl.mall.service.ItemService;
import org.ppl.mall.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 静态页面生成器
 * @author PPL
 */
@Component
public class HtmlGenerator {

    @Reference
    private ItemService itemService;
    @Reference
    private ItemCatService itemCatService;
    @Reference
    private ContentService contentService;
    @Reference
    private PortalService portalService;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Value("${ITEM_STATIC_PATH}")
    private String ITEM_STATIC_PATH;
    @Value("${INDEX_STATIC_PATH}")
    private String INDEX_STATIC_PATH;
    @Value("${CONTENT_SHOW_NOW}")
    private Long CONTENT_SHOW_NOW;
    @Value("${DEFAULT_NEW_PRODUCT_CID}")
    private long DEFAULT_NEW_PRODUCT_CID;

    //生成商品详情页面
    public void genItemDetail(long id) {
        TbItem item = itemService.getItemById(id);
        TbItemDesc itemDesc = itemService.getItemDescById(id);
        Map data = new HashMap<>();
        data.put("item", item);
        data.put("itemDesc", itemDesc);
        Configuration config = freeMarkerConfigurer.getConfiguration();
        Writer out = null;
        try {
            Template template = config.getTemplate("item.ftl");
            out = new FileWriter(ITEM_STATIC_PATH+id+".html");
            template.process(data, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //生成主页
    public void genPortalIndex() {
        List<TbContent> showNowList = contentService.getContentList(CONTENT_SHOW_NOW);
        List<TbItemCat> newProductCat = itemCatService.getRootItemCatList();
        List<NewProduct>[] newProductArray = new List[newProductCat.size()];
        for (int i=0; i<newProductCat.size(); i++) {
            Long cid = newProductCat.get(0).getId();
            if (cid == DEFAULT_NEW_PRODUCT_CID) {
                newProductArray[i] = portalService.getNewProductByCid(null);
            } else {
                newProductArray[i] = portalService.getNewProductByCid(cid);
            }
        }
        Map data = new HashMap<>();
        data.put("showNowList", showNowList);
        data.put("newProductCat", newProductCat);
        data.put("newProductArray", newProductArray);
        Configuration config = freeMarkerConfigurer.getConfiguration();
        Writer out = null;
        try {
            Template template = config.getTemplate("index.ftl");
            out = new FileWriter(INDEX_STATIC_PATH+"index.html");
            template.process(data, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
