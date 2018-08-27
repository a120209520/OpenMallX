package org.ppl.mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.ppl.mall.mapper.TbContentCategoryMapper;
import org.ppl.mall.model.TreeNode;
import org.ppl.mall.pojo.TbContentCategory;
import org.ppl.mall.pojo.TbContentCategoryExample;
import org.ppl.mall.pojo.TbContentCategoryExample.Criteria;
import org.ppl.mall.service.ContentCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentCatServiceImpl implements ContentCatService {

	@Autowired
	private TbContentCategoryMapper contentCatMapper;
	
	//查询parentId下的子节点
	@Override
	public List<TreeNode> getContentCatList(long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> result = contentCatMapper.selectByExample(example);
		List<TreeNode> nodeList = new ArrayList<>();
		for(TbContentCategory c:result) {
			TreeNode node = new TreeNode();
			node.setId(c.getId());
			node.setText(c.getName());
			node.setState(c.getIsParent()?"closed":"open");
			nodeList.add(node);
		}
		return nodeList;
	}
}
