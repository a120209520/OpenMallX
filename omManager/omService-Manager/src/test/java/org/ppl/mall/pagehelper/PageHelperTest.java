package org.ppl.mall.pagehelper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ppl.mall.config.DataSourceConfig;
import org.ppl.mall.config.RootConfig;
import org.ppl.mall.mapper.TbItemMapper;
import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.pojo.TbItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes={RootConfig.class})
public class PageHelperTest {

    @Autowired
    private TbItemMapper itemMapper;

	//@Test
	public void testPageHelper() throws Exception {
		//设置Page信息
		PageHelper.startPage(2, 10);
		//执行sql
		List<TbItem> list = itemMapper.selectByExample(new TbItemExample());
		System.out.println(list.getClass().getName());
		//取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		System.out.println(pageInfo.getTotal());
		System.out.println(pageInfo.getPageSize());
		System.out.println(pageInfo.getPageNum());
		List<TbItem> subList = pageInfo.getList();
		System.out.println(subList.size());
	}
}
