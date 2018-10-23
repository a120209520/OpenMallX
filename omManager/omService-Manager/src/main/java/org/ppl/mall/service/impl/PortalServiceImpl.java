package org.ppl.mall.service.impl;

import org.ppl.mall.mapper.MixViewMapper;
import org.ppl.mall.mapper.TbContentCategoryMapper;
import org.ppl.mall.mapper.TbItemCatMapper;
import org.ppl.mall.model.NewProduct;
import org.ppl.mall.pojo.TbItemCat;
import org.ppl.mall.pojo.TbItemCatExample;
import org.ppl.mall.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页Service
 * @author PPL
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class PortalServiceImpl implements PortalService {

    /*********************Field**********************/
    /*-------------------field-----------------------*/
    @Autowired
    private MixViewMapper mixViewMapper;
    @Autowired
    private TbItemCatMapper itemCatMapper;


    /*********************Method**********************/
    /*-----------------public method-----------------*/

    /**
     * 根据商品分类cid查询对应分类及子分类视图
     * @return List<NewProduct> "最新商品"视图
     */
    @Override
    public List<NewProduct> getNewProductByCid(Long cid) {
        if (cid == null) {
            return mixViewMapper.selectNewProduct();
        }
        List<NewProduct> res = new ArrayList<>();
        loopFindNewProduct(cid, res);
        return res;
    }

    /*-----------------private method-----------------*/
    //递归查找父节点下所有子节点的NewProduct对象
    private void loopFindNewProduct(Long cid, List<NewProduct> res) {
        res.addAll(mixViewMapper.selectNewProductByCid(cid));
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(cid);
        List<TbItemCat> itemCats = itemCatMapper.selectByExample(example);
        System.out.println(itemCats.size());
        if (itemCats.size() != 0) {
            for (TbItemCat itemCat : itemCats) {
                loopFindNewProduct(itemCat.getId(), res);
            }
        }
    }
}
