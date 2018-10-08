package org.ppl.mall.service;

import java.util.List;

import org.ppl.mall.model.TreeNode;

/**
 * 商品分类Service接口
 * @author PPL
 */
public interface ItemCatService {
	List<TreeNode> getItemCatList(long parentId);
}
