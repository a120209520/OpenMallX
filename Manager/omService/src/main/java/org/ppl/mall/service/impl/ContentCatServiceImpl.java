package org.ppl.mall.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ppl.mall.mapper.TbContentCategoryMapper;
import org.ppl.mall.model.TreeNode;
import org.ppl.mall.pojo.TbContentCategory;
import org.ppl.mall.pojo.TbContentCategoryExample;
import org.ppl.mall.pojo.TbContentCategoryExample.Criteria;
import org.ppl.mall.service.ContentCatService;
import org.ppl.mall.util.MsgResult;
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

	//添加分类
	@Override
	public MsgResult addContentCat(long parentId, String name) {
		TbContentCategory parentNode = contentCatMapper.selectByPrimaryKey(parentId);
		parentNode.setIsParent(true);
		contentCatMapper.updateByPrimaryKey(parentNode);
		
		Date curTime = new Date();
		TbContentCategory newNode = new TbContentCategory();
		newNode.setParentId(parentId);
		newNode.setName(name);
		newNode.setStatus(TbContentCategory.CONTENTCAT_NORMAL);
		newNode.setSortOrder(1);
		newNode.setIsParent(false);
		newNode.setCreated(curTime);
		newNode.setUpdated(curTime);
		contentCatMapper.insertSelective(newNode);
		
		return MsgResult.ok(newNode);
	}

	//更新分类
	@Override
	public MsgResult updateContentCat(Long id, String name) {
		TbContentCategory cat = new TbContentCategory();
		cat.setId(id);
		cat.setName(name);
		contentCatMapper.updateByPrimaryKeySelective(cat);
		return MsgResult.ok();
	}
	
	//删除分类(包括子节点)
	@Override
	public MsgResult deleteContentCat(Long id) {
		deleteContentCatLoop(id);
		return MsgResult.ok();
	}
	private void deleteContentCatLoop(Long id) {
		TbContentCategory parent = contentCatMapper.selectByPrimaryKey(id);
		contentCatMapper.deleteByPrimaryKey(id);
		if(!parent.getIsParent())
			return;
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(id);
		List<TbContentCategory> list = contentCatMapper.selectByExample(example);
		for(TbContentCategory c:list) {
			deleteContentCatLoop(c.getId());	
		}
	}
}
