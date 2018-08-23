package org.ppl.mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.ppl.mall.mapper.TbItemCatMapper;
import org.ppl.mall.model.TreeNode;
import org.ppl.mall.pojo.TbItemCat;
import org.ppl.mall.pojo.TbItemCatExample;
import org.ppl.mall.pojo.TbItemCatExample.Criteria;
import org.ppl.mall.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	//查询当前父节点下的直接子节点
	@Override
	public List<TreeNode> getItemCatList(long parentid) {
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
}
