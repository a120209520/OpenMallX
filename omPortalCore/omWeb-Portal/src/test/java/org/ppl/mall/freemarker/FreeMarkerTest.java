package org.ppl.mall.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import org.ppl.mall.pojo.TbItem;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.*;

/**
 * FreeMarker模板测试
 */
public class FreeMarkerTest {

    //基本测试
    @Test
    public void testBasic() throws Exception {
        Configuration config = new Configuration(Configuration.getVersion());
        config.setDirectoryForTemplateLoading(new File("G:/OneDrive/Study/Java/Project/OpenMallX-Pro/omPortalCore/omWeb-Portal/src/main/webapp/WEB-INF/ftl/"));
        config.setDefaultEncoding("utf-8");
        Template template = config.getTemplate("test.ftl");
        Map data = new HashMap();

        //001.基本数据
        data.put("hello", "test freemarker");
        //002.对象数据
        TbItem item = new TbItem();
        item.setTitle("任天堂：Nintend Switch");
        item.setPrice(2399L);
        data.put("item", item);
        //003.Listsh数据
        List<TbItem> items = new ArrayList<>();
        TbItem item1 = new TbItem(); item1.setTitle("任天堂：Nintend 3DS"); item1.setPrice(1399L);
        TbItem item2 = new TbItem(); item2.setTitle("HHKB"); item2.setPrice(1699L);
        TbItem item3 = new TbItem(); item3.setTitle("macbook pro"); item3.setPrice(16999L);
        items.add(item1);
        items.add(item2);
        items.add(item3);
        data.put("itList", items);
        //004.日期数据
        data.put("curTime", new Date());
        //005.null值
        data.put("val", null);
        //006.引用其他ftl
        data.put("hi", "我是外来的");
        //007.数组容器对象
        List<TbItem>[] itemArray = new List[3];
        itemArray[0] = new ArrayList<>();
        itemArray[0].add(item1);
        itemArray[0].add(item1);
        itemArray[0].add(item1);
        itemArray[1] = new ArrayList<>();
        itemArray[1].add(item2);
        itemArray[1].add(item2);
        itemArray[1].add(item2);
        itemArray[2] = new ArrayList<>();
        itemArray[2].add(item3);
        itemArray[2].add(item3);
        itemArray[2].add(item3);
        data.put("itemArray", itemArray);

        Writer out = new FileWriter("G:/OneDrive/Study/Java/Project/OpenMallX-Pro/omPortalCore/omWeb-Portal/src/main/webapp/WEB-INF/ftl/test.html");
        template.process(data, out);
        out.close();
    }
}
