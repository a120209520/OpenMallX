package org.pp.mall.trans;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ppl.mall.config.service.RootConfig;
import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class TransTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void test01() {
        TbItem item = new TbItem();
        item.setCid(1L);
        item.setNum(100);
        item.setPrice(100L);
        item.setTitle("测试事务");
        item.setBarcode("123421");
        itemService.addItem(item, "测试事务");
    }
}
