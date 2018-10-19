package org.ppl.mall.tools.item;

import com.alibaba.dubbo.config.annotation.Reference;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.pojo.TbItemDesc;
import org.ppl.mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 静态页面生成器
 * @author PPL
 */
@Component
public class HtmlGenerator {

    @Reference
    private ItemService itemService;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Value("${ITEM_STATIC_PATH}")
    private String ITEM_STATIC_PATH;

    public void itemAdd(long id) {
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
}
