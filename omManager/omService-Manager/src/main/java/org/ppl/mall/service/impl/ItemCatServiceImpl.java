package org.ppl.mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.ppl.mall.jedis.JedisClient;
import org.ppl.mall.mapper.TbItemCatMapper;
import org.ppl.mall.model.TreeNode;
import org.ppl.mall.pojo.TbContent;
import org.ppl.mall.pojo.TbItemCat;
import org.ppl.mall.pojo.TbItemCatExample;
import org.ppl.mall.pojo.TbItemCatExample.Criteria;
import org.ppl.mall.service.ItemCatService;
import org.ppl.mall.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品分类Service
 * @author PPL
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class ItemCatServiceImpl implements ItemCatService {

    /*********************Field**********************/
    /*-------------------field-----------------------*/
	@Autowired
	private TbItemCatMapper itemCatMapper;
    @Autowired
    private JedisClient jedisClient;

	@Value("${redis.item_cat_list}")
    private String ITEM_CAT_LIST;


    /*********************Method**********************/
    /*-----------------public method-----------------*/

    /**
     * 查询当前父节点下的直接子节点商品分类列表
     * @param parentid 父节点ID
     * @return List<TreeNode>
     */
	@Override
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public List<TreeNode> getItemCatTreeList(Long parentid) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentid);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List<TreeNode> result = new ArrayList<>();
		for(TbItemCat cat:list) {
			TreeNode node = new TreeNode();
			node.setId(cat.getId());
			node.setText(cat.getName());
			node.setState(cat.getIsParent()?"closed":"open");
			result.add(node);
		}
		return result;
	}

    /**
     * 查询当前父节点下的直接子节点商品分类列表
     * @param parentid 父节点ID
     * @return List<TbItemCat>
     */
    @Override
    @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
    public List<TbItemCat> getItemCatList(Long parentid) {
        //取redis缓存
        String json = jedisClient.hget(ITEM_CAT_LIST, parentid.toString());
        if(StringUtils.isNotBlank(json)) {
            List<TbItemCat> list = JsonUtils.jsonToList(json, TbItemCat.class);
            return list;
        }
        TbItemCatExample example = new TbItemCatExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentid);
        List<TbItemCat> list = itemCatMapper.selectByExample(example);

        jedisClient.hset(ITEM_CAT_LIST, parentid.toString(), JsonUtils.objectToJson(list));
        return list;
    }

    /**
     * 查询所有根节点
     * @return List<TreeNode>
     */
    @Override
    @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
    public List<TreeNode> getRootItemCatTreeList() {
        return getItemCatTreeList(0L);
    }

    /**
     * 查询所有根节点
     * @return List<TreeNode>
     */
    @Override
    @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
    public List<TbItemCat> getRootItemCatList() {
        return getItemCatList(0L);
    }
}
